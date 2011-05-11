package com.gni.frmk.tools.addon.handler;

import com.gni.frmk.tools.addon.model.Component;
import com.gni.frmk.tools.addon.model.Component.Type;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 13:05
 *
 * @author: e03229
 */
public interface ComponentRepository {
    Set<Type> getAvaibleTypes();
    <T extends Component> T loadComponent(Type type, Component.Id id);
}
