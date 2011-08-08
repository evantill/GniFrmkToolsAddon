package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/08/11
 * Time: 18:13
 *
 * @author: e03229
 */
public interface RecoverableComponent<T extends Component<T>, S extends ComponentState> extends Component<T> {
    S saveState();

    void restoreState(S previousState);
}
