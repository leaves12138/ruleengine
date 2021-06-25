package io.inceptor.drl.drl.agenda;

import io.vertx.core.Future;

public class FutureActivation implements Activation {

    private Agenda agenda;
    private Future future;
    private Runnable task;

    public FutureActivation(Agenda agenda, Future future, Runnable runnable) {
        this.agenda = agenda;
        this.future = future;
        this.task = runnable;

//        future.onComplete(result -> {
//            agenda.notifyReady(this);
//        });
    }

    @Override
    public boolean readyForInvoke() {
        return future.isComplete();
    }

    @Override
    public boolean isFutureActivation() {
        return true;
    }

    @Override
    public Agenda getAgenda() {
        return this.agenda;
    }

    @Override
    public void run() {
        task.run();
    }

    public static FutureActivation ofValue(Agenda agenda,Future future, Runnable runnable) {
        return new FutureActivation(agenda, future, runnable);
    }
}
