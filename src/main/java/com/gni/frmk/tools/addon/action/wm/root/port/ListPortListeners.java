package com.gni.frmk.tools.addon.action.wm.root.port;

import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.result.ListResult;
import com.gni.frmk.tools.addon.model.component.Port;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:37
 *
 * @author: e03229
 */
public class ListPortListeners
        implements Action<ListResult<Port>>,
        UpdatableCollectionAction<Port, List<Port>, ListResult<Port>> {

    private final List<Port> ports = Lists.newArrayList();

    @Override
    public void setCollection(Collection<Port> collection) {
        this.ports.addAll(collection);
    }

    @Override
    public void addToCollection(Port element) {
        ports.add(element);
    }

    @Override
    public List<Port> getCollection() {
        return Collections.unmodifiableList(ports);
    }

    @Override
    public boolean isUpdate() {
        return ports.size() > 0;
    }
}
