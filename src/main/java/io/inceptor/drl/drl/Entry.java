package io.inceptor.drl.drl;

import io.inceptor.drl.drl.bean.RuleStatus;
import io.inceptor.drl.drl.fact.Fact;

import java.util.List;

public interface Entry {
    void accept(Fact o);

    void accept(List<Fact> os);

    void clear();

    boolean removeRule(String ruleId);

    boolean initAndInsertToHead(Rule rule);

    List<RuleStatus> listRunningRules();
}
