package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 14:14
 *
 * @author: e03229
 */
public interface CompositeComponent<S extends ComponentState> extends Component<S>, Iterable<Component<?>> {
}
