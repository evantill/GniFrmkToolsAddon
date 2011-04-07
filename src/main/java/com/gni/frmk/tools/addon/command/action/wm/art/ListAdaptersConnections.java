package com.gni.frmk.tools.addon.command.action.wm.art;

import com.gni.frmk.tools.addon.command.action.StringAction;
import com.gni.frmk.tools.addon.command.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class ListAdaptersConnections
        extends StringAction<ListResult<AdapterConnection>>
        implements UpdatableCollectionAction<AdapterConnection, List<AdapterConnection>, ListResult<AdapterConnection>> {

    private final List<AdapterConnection> connections = Lists.newArrayList();

    public ListAdaptersConnections(String adapterType) {
        super(adapterType);
    }

    @Override
    public void setCollection(Collection<AdapterConnection> collection) {
        connections.addAll(connections);
    }

    @Override
    public List<AdapterConnection> getCollection() {
        return Collections.unmodifiableList(connections);
    }

    @Override
    public boolean isUpdate() {
        return connections.size() > 0;
    }
}
