package com.gni.frmk.tools.addon.tdd.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentStatus;
import com.gni.frmk.tools.addon.tdd.api.ComponentType.IOType;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 14:04
 *
 * @author: e03229
 */
public class AlphaComponent implements Component<SimpleBooleanState> {
    private final ClassComponentType<AlphaComponent> type = ClassComponentType.createForComponent(this, IOType.INPUT);
    private final IntegerId id;
    private Boolean opened;

    public AlphaComponent(IntegerId id) {
        this.id = id;
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

    public ClassComponentType<AlphaComponent> getType() {
        return type;
    }

    public IntegerId getId() {
        return id;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visitComponent(this);
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
        sb.append("AlphaComponent");
        sb.append("{type=").append(type);
        sb.append(", id=").append(id);
        sb.append(", opened=").append(opened);
        sb.append('}');
        return sb.toString();
    }
}
