package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.visitor.ComponentVisitor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:01
 *
 * @author: e03229
 */
@XmlType(propOrder = {"id",
                      "currentState",
                      "detail"})
public class BaseComponent<I extends Id, S extends State, D extends Detail>
        implements Component<I, S, D> {

    private Type type;
    private I id;
    private S currentState;
    private D detail;

    public BaseComponent() {
    }

    @XmlAttribute(required = true)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @XmlElement(required = true, type = AbstractId.class)
    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    @XmlElement(required = true, type = AbstractState.class)
    public S getCurrentState() {
        return currentState;
    }

    public void setCurrentState(S currentState) {
        this.currentState = currentState;
    }

    @XmlElement(required = true, type = AbstractDetail.class)
    public D getDetail() {
        return detail;
    }

    public void setDetail(D detail) {
        this.detail = detail;
    }

    public abstract static class AbstractDetail implements Detail {
    }

    public abstract static class AbstractId implements Id {
    }

    public abstract static class AbstractState implements State {
        private boolean exist;

        protected AbstractState(boolean exist) {
            this.exist = exist;
        }

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }

        @Override
        public boolean exist() {
            return isExist();
        }
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visitComponent(this);
    }

}
