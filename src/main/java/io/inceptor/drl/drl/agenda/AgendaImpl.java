package io.inceptor.drl.drl.agenda;

import java.util.Iterator;
import java.util.LinkedList;

public class AgendaImpl implements Agenda {
    LinkedList<Activation> waitinglist = new LinkedList<>();
    LinkedList<Activation> readylist = new LinkedList<>();

    @Override
    public boolean addActivation(Activation activation) {
        if (!activation.isFutureActivation())
            return readylist.add(activation);
        return waitinglist.add(activation);
    }

    @Override
    public boolean addActivation(Runnable runnable) {
        readylist.add(Activation.ofRunnable(this, runnable));
        return true;
    }

    @Override
    public Activation getOneActivationByPriority() {
        if (!readylist.isEmpty())
            return readylist.pop();

        searchReady();

        if (!readylist.isEmpty())
            return readylist.pop();

        return null;
    }

    @Override
    public int remainSize() {
        return waitinglist.size() + readylist.size();
    }

    @Override
    public boolean hasReady() {
        if (!readylist.isEmpty())
            return true;
        searchReady();
        return !readylist.isEmpty();
    }

    @Override
    public void notifyReady(Activation activation) {
        if (waitinglist.contains(activation))
            waitinglist.remove(activation);
        if (!readylist.contains(activation))
            readylist.add(activation);
    }

    private void searchReady() {
        Iterator<Activation> iterator = waitinglist.iterator();
        while (iterator.hasNext()) {
            Activation activation = iterator.next();
            if (activation.readyForInvoke()) {
                iterator.remove();
                readylist.add(activation);
            }
        }
    }
}
