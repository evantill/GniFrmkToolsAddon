package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.BuilderWithValidation;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;

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

    BaseComponent.Builder<?, C, T, I, S, D> componentBuilder();

    BuilderWithValidation<?, I> idBuilder();

    BuilderWithValidation<?, S> stateBuilder();

    BuilderWithValidation<?, D> detailBuilder();

    C checkComponent(Component<?, ?, ?, ?, ?> component);

    I checkId(ComponentId id);

    S checkState(ComponentState state);

    D checkDetail(ComponentDetail detail);

    boolean isInput();

    boolean isOutput();

    ComponentTypeOrder getOpenSequenceOrder();

    ComponentTypeOrder getCloseSequenceOrder();
}
