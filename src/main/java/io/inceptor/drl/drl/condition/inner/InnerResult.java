package io.inceptor.drl.drl.condition.inner;

import io.inceptor.drl.exceptions.FutureNotCompleteException;
import io.vertx.core.Future;

public interface InnerResult {

    InnerResult trueResult = InnerResult.valueof(true);

    InnerResult falseResult = InnerResult.valueof(false);

    boolean isFuture();

    boolean pass();

    Future<Boolean> getFuture();

    boolean isComplete();

    default InnerResult and(InnerResult a) {
        if (!(this.isFuture() || a.isFuture())) {
            return valueof(this.pass() && a.pass());
        }

        if (!this.isFuture()) {
            if (!this.pass())
                return falseResult;
            return a;
        }

        if (!a.isFuture()) {
            if (!a.pass())
                return falseResult;
            return this;
        }

        return new AndFutureResult(this, a);
    }

    default InnerResult or(InnerResult a) {
        if (!(this.isFuture() || a.isFuture())) {
            return valueof(this.pass() || a.pass());
        }

        if (!this.isFuture()) {
            if (this.pass())
                return trueResult;
            return a;
        }

        if (!a.isFuture()) {
            if (a.pass())
                return trueResult;
            return this;
        }

        return new OrFutureResult(this, a);
    }

    static InnerResult valueof(boolean b) {
        return new BooleanResult(b);
    }

    static InnerResult valueof(Future<Boolean> f) {
        return new FutureResult(f);
    }

    class BooleanResult implements InnerResult {
        boolean b;
        public BooleanResult(boolean b) {
            this.b = b;
        }

        @Override
        public boolean pass() {
            return b;
        }

        @Override
        public Future getFuture() {
            return null;
        }

        @Override
        public boolean isComplete() {
            return true;
        }

        @Override
        public boolean isFuture(){
            return false;
        }
    }

    class FutureResult implements InnerResult {

        private Future<Boolean> future;

        public FutureResult(Future<Boolean> future) {
            this.future = future;
        }

        @Override
        public boolean isFuture() {
            return true;
        }

        @Override
        public boolean pass() throws FutureNotCompleteException {
            if (future.isComplete())
                return future.result();
            throw new FutureNotCompleteException();
        }

        @Override
        public Future getFuture() {
            return future;
        }

        @Override
        public boolean isComplete() {
            return future.isComplete();
        }

        @Override
        public InnerResult and(InnerResult a1) {
            return null;
        }

        @Override
        public InnerResult or(InnerResult a1) {
            return null;
        }
    }

    abstract class CombinedResult implements InnerResult {


        protected InnerResult a1;
        protected InnerResult a2;

        public CombinedResult(InnerResult a1, InnerResult a2) {
            this.a1 = a1;
            this.a2 = a2;
        }

        @Override
        public boolean isFuture() {
            return true;
        }

        @Override
        public Future getFuture() {
            return null;
        }

        @Override
        public boolean isComplete() {
            return (a1.isComplete() && a2.isComplete());
        }

        public abstract boolean pass();
    }

    class AndFutureResult extends CombinedResult {

        public AndFutureResult(InnerResult a1, InnerResult a2) {
            super(a1, a2);
        }

        @Override
        public boolean pass() {
            if (a1.isComplete() && a2.isComplete())
                return a1.pass() && a2.pass();
            throw new FutureNotCompleteException();
        }

    }

    class OrFutureResult extends CombinedResult {

        public OrFutureResult(InnerResult a1, InnerResult a2) {
            super(a1, a2);
        }

        @Override
        public boolean pass() {
            if (a1.isComplete() && a2.isComplete())
                return a1.pass() || a2.pass();
            throw new FutureNotCompleteException();
        }
    }
}
