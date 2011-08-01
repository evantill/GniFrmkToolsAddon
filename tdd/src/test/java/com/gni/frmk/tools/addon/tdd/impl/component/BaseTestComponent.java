package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.api.ComponentType;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.gni.frmk.tools.addon.tdd.util.UnimplementedMethodException;
import com.google.common.collect.ComparisonChain;

import static com.google.common.base.Objects.toStringHelper;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/08/11
 * Time: 15:00
 *
 * @author: e03229
 */
public abstract class BaseTestComponent<C extends Component<C, S>,
        T extends ComponentType<C, I, S>,
        I extends ComponentId<I>,
        S extends ComponentState<S>>
        implements Component<C, S> {

    private T type;
    private I id;
    private S state;
    private boolean opened = false;

    protected BaseTestComponent(T type, I id, S state, boolean opened) {
        this.type = type;
        this.id = id;
        this.state = state;
        this.opened = opened;
    }

    @Override
    public final void open(S state) {
        open();
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public final void open() {
        if (isOpened()) {
            throw new IllegalStateException("already opened");

        }
        opened = true;
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    public boolean isOpened() {
        return opened;
    }

    @Override
    public final void close(S state) {
        close();
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public final void close() {
        if (!isOpened()) {
            throw new IllegalStateException("already closed");

        }
        opened = true;
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public final int compareTo(C o) {
        return ComparisonChain.start()
                              .compare(this.hashCode(), o.hashCode())
                              .result();
    }

    public T getType() {
        return type;
    }

    public I getId() {
        return id;
    }

    @Override
    public final S getState() {
        return state;
    }

    @Override
    public S saveState() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void restoreState(S state) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("type", getType().getClass().getSimpleName())
                .add("id", getId().toString())
                .add("opened", opened)
                .toString();
    }
}
