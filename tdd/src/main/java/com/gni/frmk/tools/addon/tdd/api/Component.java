package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 13:55
 *
 * @author: e03229
 */
public interface Component {
    void open();

    void close();

    void refreshStatus();

    ComponentStatus getStatus();

    void accept(ComponentVisitor visitor);

    ComponentType getType();

    ComponentId getId();
}
