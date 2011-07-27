package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 19:32
 *
 * @author: e03229
 */
public abstract class SingletonComponentType
        <C extends Component<C, S>, I extends ComponentId<I>, S extends ComponentState<S>>
        implements ComponentType<C, I, S> {

    /**
     * Compare class. ComponentType are supposed to be singletons
     *
     * @param o the componentType to compare.
     * @return 0 if instances are of the same class
     */
    @Override
    public final int compareTo(ComponentType o) {
        String thisClassName = getClass().getName();
        String otherClassName = o.getClass().getName();
        return thisClassName.compareTo(otherClassName);
    }
}
