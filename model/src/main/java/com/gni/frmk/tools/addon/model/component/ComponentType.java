package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.BuilderWithValidation;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 10:30
 *
 * @author: e03229
 */
public interface ComponentType
        <T extends ComponentType<T, C, I, S, D>,
                C extends Component<C, T, I, S, D>,
                I extends ComponentId,
                S extends ComponentState,
                D extends ComponentDetail>
        extends Comparable<T> {

    Class<C> getTypeComponent();

    Class<I> getTypeId();

    Class<S> getTypeState();

    Class<D> getTypeDetail();

    BuilderWithValidation<?, C> componentBuilder();

    BuilderWithValidation<?, I> idBuilder();

    BuilderWithValidation<?, S> stateBuilder();

    BuilderWithValidation<?, D> detailBuilder();

    boolean isInput();

    boolean isOutput();

    C checkComponent(Component<?, ?, ?, ?, ?> component);

    I checkId(ComponentId id);

    S checkState(ComponentState state);

    D checkDetail(ComponentDetail detail);

/*
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

    ComponentType(boolean input, boolean output) {
        this.input = input;
        this.output = output;
    }

    public final boolean isInput() {
        return input;
    }

    public final boolean isOutput() {
        return output;
    }
*/
}
