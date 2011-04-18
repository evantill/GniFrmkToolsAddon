package com.gni.frmk.tools.addon.action.wm.root.port;

import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.model.component.ImmutablePort;
import com.gni.frmk.tools.addon.model.component.ImmutablePort.MutablePort;
import com.gni.frmk.tools.addon.result.ListResult;
import com.gni.frmk.tools.addon.model.component.ImmutablePort;
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
        implements Action<ListResult<MutablePort>>,
        UpdatableCollectionAction<MutablePort, List<MutablePort>, ListResult<MutablePort>> {

    private final List<MutablePort> ports = Lists.newArrayList();

    @Override
    public void setCollection(Collection<MutablePort> collection) {
        this.ports.addAll(collection);
    }

    @Override
    public void addToCollection(MutablePort element) {
        ports.add(element);
    }

    @Override
    public List<MutablePort> getCollection() {
        return Collections.unmodifiableList(ports);
    }

    @Override
    public boolean isUpdate() {
        return ports.size() > 0;
    }
}
