package io.inceptor.drl.drl.fact;

public class Fact {
    Object c;

    public Fact(Object c) {
        this.c = c;
    }

    public Object get() {
        return c;
    }

    public static Fact of(Object c) {
        return new Fact(c);
    }

    public static Fact NullFact = new Fact(null);
}
