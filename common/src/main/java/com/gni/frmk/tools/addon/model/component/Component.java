package com.gni.frmk.tools.addon.model.component;

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

    public static interface Id {
    }

    public static interface State {
        boolean exist();

        boolean unknown();
    }

    public static interface Detail {
    }

    public static enum Type {
        UNKNOWN(false, false),
        ADAPTER_NOTIFICATION(true, false),
        ADAPTER_LISTENER(true, false),
        JMS_ALIAS(false, true),
        PORT(true, false),
        NATIVE_TRIGGER(true, false),
        JMS_TRIGGER(true, false),
        SCHEDULER(true, false),
        ADAPTER_CONNECTION(false, true);

        private final boolean input;
        private final boolean output;

        Type(boolean input, boolean output) {
            this.input = input;
            this.output = output;
        }

        public final boolean isInput() {
            return input;
        }

        public final boolean isOutput() {
            return output;
        }
    }

    public static enum StateType {
        CLOSE, CURRENT, OPEN, LAST
    }

}
