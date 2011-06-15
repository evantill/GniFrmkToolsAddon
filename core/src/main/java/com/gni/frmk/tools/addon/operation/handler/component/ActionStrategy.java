package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.ComponentType;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/06/11
 * Time: 14:39
 *
 * @author: e03229
 */
interface ActionStrategy<T extends ComponentType<?, ?, ?, ?, ?>> {
    T getComponentType();
}
