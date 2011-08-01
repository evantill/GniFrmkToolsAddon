package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 13:55
 *
 * @author: e03229
 */
public interface Component<C extends Component<C, S>, S extends ComponentState<S>> extends Comparable<C> {

    void open(S state);

    void open();

    void close(S state);

    void close();

    S getState();

    S saveState();

    void restoreState(S state);

    void accept(ComponentVisitor visitor);

//    ComponentType<?, ?, ?> getType();
//    ComponentId<?> getId();

}
