package io.inceptor.drl.drl.agenda;

public class RunnableActivation implements Activation {
    private Agenda agenda;
    private Runnable task;

    public RunnableActivation(Agenda agenda, Runnable runnable) {
        this.agenda = agenda;
        this.task = runnable;
    }

    @Override
    public boolean readyForInvoke() {
        return true;
    }

    @Override
    public boolean isFutureActivation() {
        return false;
    }

    @Override
    public Agenda getAgenda() {
        return agenda;
    }

    @Override
    public void run() {
        task.run();
    }

    public static RunnableActivation ofValue(Agenda agenda, Runnable runnable) {
        return new RunnableActivation(agenda, runnable);
    }
}
