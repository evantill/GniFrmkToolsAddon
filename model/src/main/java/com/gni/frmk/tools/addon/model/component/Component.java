package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.component.ComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:01
 *
 * @author: e03229
 */
public interface Component
        <C extends Component<C, T, I, S, D>,
                T extends ComponentType<T, C, I, S, D>,
                I extends ComponentId<I>,
                S extends ComponentState<S>,
                D extends ComponentDetail<D>>
        extends Comparable<C> {

    T getType();

    I getId();

    D getDetail();

    S getCurrentState();

    void accept(ComponentVisitor visitor);

}
