package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 13:55
 *
 * @author: e03229
 */
public interface Component<C extends Component<C, S>, S extends ComponentState<S>> extends Comparable<C> {

    void open();

    void open(S state);

    void close();

    void close(S state);

    S getState();

    ComponentType<?,?,?> getType();

    ComponentId<?> getId();
}
