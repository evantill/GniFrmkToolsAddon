package com.gni.frmk.tools.addon.action.wm.art.connection;

import com.gni.frmk.tools.addon.action.StringAction;
import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterConnection;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterConnection.MutableAdapterConnection;
import com.gni.frmk.tools.addon.result.ListResult;
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
        extends StringAction<ListResult<MutableAdapterConnection>>
        implements UpdatableCollectionAction<MutableAdapterConnection, List<MutableAdapterConnection>, ListResult<MutableAdapterConnection>> {

    private final List<MutableAdapterConnection> connections = Lists.newArrayList();

    public ListAdaptersConnections(String adapterType) {
        super(adapterType);
    }

    @Override
    public void setCollection(Collection<MutableAdapterConnection> collection) {
        connections.addAll(connections);
    }

    @Override
    public void addToCollection(MutableAdapterConnection element) {
        connections.add(element);
    }

    @Override
    public List<MutableAdapterConnection> getCollection() {
        return Collections.unmodifiableList(connections);
    }

    @Override
    public boolean isUpdate() {
        return connections.size() > 0;
    }
}
