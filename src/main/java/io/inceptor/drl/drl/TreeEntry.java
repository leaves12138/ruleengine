package io.inceptor.drl.drl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.inceptor.drl.drl.agenda.Activation;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.fact.Fact;
import io.inceptor.drl.util.DrlSession;
import org.apache.commons.lang3.StringUtils;
import org.mvel2.integration.VariableResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class TreeEntry extends RuleEntry {
    static Logger log = LoggerFactory.getLogger(TreeEntry.class);

    static private ThreadLocal<ObjectReader> local = new ThreadLocal<>();

    protected List<RuleTree> trees = new LinkedList<>();

    boolean useMutipleThread = true;

    ExecutorService executorService;

    {
        if (useMutipleThread)
            executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()*2);
    }



//    @Override
    public TreeEntry init(Rule rule, String packageName, Set<DefinedFunction> definedFunctions, Set<GlobalImport> globalImports , List<DeclaredClass> declaredClasses , Set<JavaImportClass> javaImportClasses, Set<String> staticImports, Map<String, Datasource> dataSources, ParsedDrlFile parsedDrlFile, DrlSession session, VariableResolverFactory global, Map<String, String> treeStruct) {
        super.init(rule, packageName, definedFunctions, globalImports, declaredClasses, javaImportClasses, staticImports, dataSources, parsedDrlFile, session, global);

        Map<String, Rule> temp = new HashMap<>();

        Rule rulet = ruleHead;
        while (rulet != null) {
            temp.put(rulet.getName(), rulet);
            rulet = rulet.next;
        }

        for (Map.Entry<String, String> entry : treeStruct.entrySet()) {
            JsonNode jsonNode;
            try {
                jsonNode = getObjectReader().readValue(entry.getValue());
            } catch (Exception e) {
                log.error("parse json failed", e);
                log.error(entry.getValue());
                throw new RuntimeException("parse json failed. " + e.getMessage(), e);
            }

            RuleTree ruleTree = constructRuleTree(entry.getKey(), temp, jsonNode);
            if (ruleTree != null)
                trees.add(ruleTree);
        }

        return this;
    }

    @Override
    public void accept(Fact o) {
//        Rule rule = ruleHead;
//        rule.accept(o, agenda, );
    }

    @Override
    public void accept(List<Fact> os) {
        for (RuleTree ruleTree : trees) {
            Rule rule = ruleTree.peak;
            if (rule != null) {
                rule.accept(os, agenda, ruleTree);
                while (agenda.hasReady()) {
                    Activation activation = agenda.getOneActivationByPriority();
                    activation.run();
                }
            }
        }

        while (agenda.remainSize() > 0) {
            while (!agenda.hasReady()) {
                LockSupport.parkNanos(10);
            }

            Activation activation = agenda.getOneActivationByPriority();
            if (activation != null)
                activation.run();
            else throw new RuntimeException("activation is null");
        }
    }

    private RuleTree constructRuleTree(String treeName, Map<String, Rule> temp, JsonNode jsonNode) {
        RuleTree ruleTree = new RuleTree(treeName);
        String id = jsonNode.get("id").textValue();
        if (StringUtils.isBlank(id))
            throw new RuntimeException("construct rule tree failed cause no rule id in json");
        Rule rule = temp.get(id);
        if (rule == null)
            throw new RuntimeException("construct rule tree failed cause no rule found by rule id");
        ruleTree.peak = rule;

        JsonNode son = jsonNode.get("son");
        if (son != null) {
            constructTreeNode(temp, son, ruleTree);
        }

        return ruleTree;
    }

    private void constructTreeNode(Map<String, Rule> temp, JsonNode jsonNodeSon, TreeNode treeNodeParent) {
        if (!(jsonNodeSon instanceof ArrayNode)) {
            throw new RuntimeException("construct rule tree failed cause tree json son must be array");
        }

        for (JsonNode jsonNode : jsonNodeSon) {
            String id = jsonNode.get("id").textValue();
            if (StringUtils.isBlank(id))
                throw new RuntimeException("construct rule tree failed cause no rule id in json");
            Rule rule = temp.get(id);
            if (rule == null)
                throw new RuntimeException("construct rule tree failed cause no rule found by rule id");
            TreeNode treeNode = new TreeNode();
            treeNode.peak = rule;
            JsonNode son = jsonNode.get("son");
            if (son != null) {
                constructTreeNode(temp, son, treeNode);
            }

            treeNodeParent.children.add(treeNode);
        }
    }


    public static ObjectReader getObjectReader() {
        ObjectReader reader = local.get();
        if (reader != null)
            return reader;
        reader = new ObjectMapper().reader(JsonNode.class);
        local.set(reader);
        return reader;
    }



    class RuleTree extends TreeNode {
        String treeName;

        public RuleTree(String treeName) {
            this.treeName = treeName;
        }

        public void addChild(TreeNode treeNode) {
            children.add(treeNode);
        }

    }

    class TreeNode {
        Rule peak;
        Set<TreeNode> children = new HashSet();
    }
}
