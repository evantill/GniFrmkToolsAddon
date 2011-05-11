package com.gni.frmk.tools.addon.model;

import com.gni.frmk.tools.addon.visitor.ComponentVisitor;
import com.gni.frmk.tools.addon.visitor.TypedComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:01
 *
 * @author: e03229
 */
public interface Component<I extends Component.Id, S extends Component.State, D extends Component.Detail> {

    Type getType();

    I getId();

    D getDetail();

    S getCurrentState();

    void accept(ComponentVisitor visitor);

    void accept(TypedComponentVisitor visitor);

    public static interface Id {
    }

    public static interface State {
        boolean exist();
    }

    public static interface Detail {
    }

    public static enum Type {
        UNKNOWN, ADAPTER_NOTIFICATION, ADAPTER_LISTENER, JMS_ALIAS, PORT, NATIVE_TRIGGER, JMS_TRIGGER, SCHEDULER, ADAPTER_CONNECTION
    }

    public static enum StateType {
        CLOSE, CURRENT, OPEN
    }

}