package io.inceptor.drl.drl.agenda;

public interface Agenda {
    boolean addActivation(Activation activation);

    boolean addActivation(Runnable runnable);

    Activation getOneActivationByPriority();

    int remainSize();

    boolean hasReady();

    void notifyReady(Activation activation);
}
