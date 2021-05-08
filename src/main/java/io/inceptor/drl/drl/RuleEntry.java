package io.inceptor.drl.drl;

import java.util.List;

public class RuleEntry {
    private Rule ruleHead;

    public void init(Rule rule) {
        this.ruleHead = rule;
    }

    public void accept(Object o) {
        ruleHead.accept(o);
    }

    public void accept(List<Object> os) {
        ruleHead.accept(os);
    }
}
