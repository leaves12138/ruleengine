package io.inceptor.drl.drl.agenda;

import io.inceptor.drl.drl.condition.ClassResult;
import io.vertx.core.Future;

public class ResultFutureActivation implements Activation {
    private Agenda agenda;
    private ClassResult futureResult;
    private Runnable task;

    public ResultFutureActivation(Agenda agenda, ClassResult futureResult, Runnable runnable) {
        this.agenda = agenda;
        this.futureResult = futureResult;
        this.task = runnable;

//        futureResult.onComplete(result -> {
//            agenda.notifyReady(this);
//        });
    }

    @Override
    public boolean readyForInvoke() {
        return futureResult.isComplete();
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

    public static ResultFutureActivation ofValue(Agenda agenda,ClassResult futureResult, Runnable runnable) {
        return new ResultFutureActivation(agenda, futureResult, runnable);
    }
}
