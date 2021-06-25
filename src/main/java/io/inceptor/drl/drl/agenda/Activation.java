package io.inceptor.drl.drl.agenda;

import io.vertx.core.Future;

public interface Activation extends Runnable {

    boolean readyForInvoke();

    boolean isFutureActivation();

    Agenda getAgenda();

    static Activation ofRunnable(Agenda agenda, Runnable runnable) {
        return RunnableActivation.ofValue(agenda, runnable);
    }

    static Activation ofFuture(Agenda agenda, Future future, Runnable runnable) {
        return FutureActivation.ofValue(agenda, future, runnable);
    }
}
