package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 13:55
 *
 * @author: e03229
 */
public interface Component<T extends Component> extends Comparable<T> {

    ComponentType getType();

    ComponentId getId();

    ComponentStatus getStatus();

    void open();

    void close();

    void refreshStatus();
}
