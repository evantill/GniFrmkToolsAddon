package com.gni.frmk.tools.addon.model.component.state;

import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.api.ComponentState;
import com.gni.frmk.tools.addon.model.api.ComponentStateType;
import com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:54
 *
 * @author: e03229
 */
@XmlRootElement
public class TemporaryActivableState extends AbstractComponentState implements ComponentState {

    public enum TemporaryStatus {
        TEMPORARY {
            @Override
            public boolean isPermanent() {
                return false;
            }
        }, PERMANENT {
            @Override
            public boolean isPermanent() {
                return true;
            }
        };

        public abstract boolean isPermanent();
    }

    @XmlAttribute
    private final TemporaryStatus temporary;
    @XmlElement
    private final ActivableStatus activable;

    public TemporaryActivableState(TemporaryStatus temporary, ActivableStatus activable) {
        this.temporary = temporary;
        this.activable = activable;
    }

    private TemporaryActivableState(){
        temporary=null;
        activable=null;
    }

    public TemporaryStatus getTemporary() {
        return temporary;
    }

    public ActivableStatus getActivable() {
        return activable;
    }

    @Override
    public ComponentStateStatus getComponentStatus() {
        switch (activable) {
            case ACTIVE:
                return ComponentStateStatus.ON;
            case INACTIVE:
                return ComponentStateStatus.OFF;
            default:
                return ComponentStateStatus.UNKNOWN;
        }
    }

    @Override
    public ComponentStateType getType() {
        return ComponentStateType.TEMPORARY_ACTIVABLE_STATE;
    }
}
