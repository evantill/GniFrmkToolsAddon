package com.gni.frmk.tools.addon.tdd.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentStatus;
import com.gni.frmk.tools.addon.tdd.api.ComponentType.IOType;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.gni.frmk.tools.addon.tdd.api.CompositeComponent;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 14:23
 *
 * @author: e03229
 */
public class BetaComponent implements CompositeComponent<SimpleBooleanState> {

    private final ClassComponentType<BetaComponent> type = ClassComponentType.createForComponent(this, IOType.CORE);
    private final IntegerId id;
    private final Set<Component<?>> children;
    private Boolean opened;

    public BetaComponent(IntegerId id, Component<?>... children) {
        this.id = id;
        this.children = CompositeComponents.createChildrenSet(children);
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        CompositeComponents.accept(this, visitor);
    }

    @Override
    public Iterator<Component<?>> iterator() {
        return CompositeComponents.iterateOnComposite(children.iterator());
    }

    @Override
    public void open() {
        opened = true;
    }

    @Override
    public void close() {
        opened = false;
    }

    @Override
    public void refreshStatus() {
        if (opened == null) {
            opened = true;
        }
    }

    @Override
    public ComponentStatus getStatus() {
        if (opened == null) {
            return ComponentStatus.UNKNOWN;
        } else {
            return opened ? ComponentStatus.OPENED : ComponentStatus.CLOSED;
        }
    }

    public ClassComponentType<BetaComponent> getType() {
        return type;
    }

    public IntegerId getId() {
        return id;
    }


    @Override
    public SimpleBooleanState saveState() {
        return new SimpleBooleanState(opened);
    }

    @Override
    public void restoreState(SimpleBooleanState state) {
        opened = state.getOpened();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("BetaComponent");
        sb.append("{type=").append(type);
        sb.append(", id=").append(id);
        sb.append(", opened=").append(opened);
        sb.append('}');
        return sb.toString();
    }
}
