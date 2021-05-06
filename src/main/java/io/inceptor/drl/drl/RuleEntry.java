package io.inceptor.drl.drl;

public class RuleEntry {
    private Rule ruleHead;

    public void init(Rule rule) {
        this.ruleHead = rule;
    }

    public void accept(Object o) {
        ruleHead.accept(o);
    }
}
