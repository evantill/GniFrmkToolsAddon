package com.gni.frmk.tools.addon.handler.component;

import com.gni.frmk.tools.addon.model.api.Component;
import com.gni.frmk.tools.addon.model.api.ComponentId;
import com.gni.frmk.tools.addon.model.api.ComponentType;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 13:05
 *
 * @author: e03229
 */
public interface ComponentRepository {
    Set<ComponentType> getAvaibleTypes();
    <T extends Component> T loadComponent(ComponentType type, ComponentId id);
}
