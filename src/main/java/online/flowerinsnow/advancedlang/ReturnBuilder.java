package online.flowerinsnow.advancedlang;

import online.flowerinsnow.advancedlang.returnbuilder.CallableNoParam;
import online.flowerinsnow.advancedlang.returnbuilder.CallableOneDifferent;
import online.flowerinsnow.advancedlang.returnbuilder.CallableOneParam;

public class ReturnBuilder<T, R> {
    private T value;
    private R ret;
    public ReturnBuilder(T value) {
        this.value = value;
    }

    public ReturnBuilder<T, R> setValue(T value) {
        this.value = value;
        return this;
    }

    public ReturnBuilder<T, R> ifNullSet(CallableNoParam<T> ca) {
        if (this.value == null) {
            this.value = ca.call();
        }
        return this;
    }

    public ReturnBuilder<T, R> ifNullReturn(CallableNoParam<R> ca) {
        if (this.value == null) {
            ret = ca.call();
        }
        return this;
    }

    public ReturnBuilder<T, R> ifPreventReturn(CallableOneDifferent<T, R> ca) {
        if (this.value != null) {
            ret = ca.call(this.value);
        }
        return this;
    }

    public ReturnBuilder<T, R> ifPrevent(CallableOneParam<T> ca) {
        if (this.value != null) {
            this.value = ca.call(this.value);
        }
        return this;
    }

    public ReturnBuilder<T, R> action(CallableOneParam<T> ca) {
        this.value = ca.call(this.value);
        return this;
    }

    public static <T, R> ReturnBuilder<T, R> of(T value, Class<R> rType) {
        return new ReturnBuilder<>(value);
    }

    public R ret(R value) {
        return this.ret;
    }
}
