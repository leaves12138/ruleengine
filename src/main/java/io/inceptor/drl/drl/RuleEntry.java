package io.inceptor.drl.drl;

import io.inceptor.drl.drl.agenda.Activation;
import io.inceptor.drl.drl.fact.Fact;

import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class RuleEntry extends BaseRuleEntry{

    @Override
    public void accept(Fact o) {
        Rule rule = ruleHead;

        while (rule != null) {
            rule.accept(o, agenda);
            rule = rule.next;

            while (agenda.hasReady()) {
                Activation activation = agenda.getOneActivationByPriority();
                activation.run();
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


    @Override
    public void accept(List<Fact> os) {
        Rule rule = ruleHead;
        while (rule != null) {
            rule.accept(os, agenda);
            rule = rule.next;
        }

        while (agenda.hasReady()) {
            Activation activation = agenda.getOneActivationByPriority();
            activation.run();
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
}
