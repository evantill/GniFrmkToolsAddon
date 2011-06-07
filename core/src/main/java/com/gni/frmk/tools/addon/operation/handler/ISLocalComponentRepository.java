package com.gni.frmk.tools.addon.operation.handler;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.repository.ComponentRepository;
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
    public Set<ComponentType> getAvaibleTypes() {
        //TODO not yet implemented
        throw new IllegalStateException("not yet implemented");
//        Set<ComponentType> types = Sets.newHashSet();
//        types.addAll(Arrays.asList(ComponentType.values()));
//        return types;
    }

    @Override
    public <T extends Component> T loadComponent(ComponentType type, ComponentId id) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
