package com.gni.frmk.tools.addon.data;

import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationElement<T extends ConfigurationElement> {
    void accept(ConfigurationVisitor visitor);
    int compareStatusTo(T element);
//    ConfigurationElementType getElementType();
//    String getElementStatus();
//    String getElementName();
}
