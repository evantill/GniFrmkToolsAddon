package com.gni.frmk.tools.addon.visitor.api;

import com.gni.frmk.tools.addon.model.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationVisitor extends com.gni.frmk.tools.addon.api.custom.visitor.ConfigurationVisitor {

    void clear();


}
