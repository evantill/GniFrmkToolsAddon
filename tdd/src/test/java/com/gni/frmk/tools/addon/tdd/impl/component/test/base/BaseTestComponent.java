package com.gni.frmk.tools.addon.tdd.impl.component.test.base;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.api.ComponentType;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.gni.frmk.tools.addon.tdd.util.UnimplementedMethodException;
import com.google.common.base.Objects;
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
    public final void open() {
        if (isOpened()) {
            throw new IllegalStateException("already opened");

        }
        opened = true;
    }

    public boolean isOpened() {
        return opened;
    }

    @Override
    public final void close() {
        if (!isOpened()) {
            throw new IllegalStateException("already closed");

        }
        opened = false;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseTestComponent that = (BaseTestComponent) o;
        return Objects.equal(type, that.type)
               && Objects.equal(id, that.id)
               && Objects.equal(opened, that.opened);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, id);
    }
}
