package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 15:40
 *
 * @author: e03229
 */
public interface ComponentType<C extends Component<C, S>, I extends ComponentId<I>, S extends ComponentState<S>>
        extends Comparable<ComponentType> {

    C check(Component<?, ?> component);

    boolean accept(Component<?, ?> component);

    I check(ComponentId<?> id);

    boolean accept(ComponentId<?> id);

    S check(ComponentState<?> state);

    boolean accept(ComponentState<?> state);


}
