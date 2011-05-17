package com.gni.frmk.tools.addon.operation.handler;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.repository.ComponentRepository;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 13:09
 *
 * @author: e03229
 */
public class ISLocalComponentRepository implements ComponentRepository {

    @Override
    public Set<Type> getAvaibleTypes() {
        Set<Type> types = Sets.newHashSet();
        types.addAll(Arrays.asList(Type.values()));
        return types;
    }

    @Override
    public <T extends Component> T loadComponent(Component.Type type, Component.Id id) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
