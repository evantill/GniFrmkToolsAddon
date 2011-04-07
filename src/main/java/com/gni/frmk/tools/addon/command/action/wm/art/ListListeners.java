package com.gni.frmk.tools.addon.command.action.wm.art;

import com.gni.frmk.tools.addon.command.action.StringAction;
import com.gni.frmk.tools.addon.command.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterListener;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:17
 *
 * @author: e03229
 */
public class ListListeners
        extends StringAction<ListResult<AdapterListener>>
        implements UpdatableCollectionAction<AdapterListener, List<AdapterListener>, ListResult<AdapterListener>> {

    private final List<AdapterListener> listeners = Lists.newArrayList();

    public ListListeners(String adapterType) {
        super(adapterType);
    }

    @Override
    public void setCollection(Collection<AdapterListener> collection) {
        listeners.addAll(collection);
    }

    @Override
    public List<AdapterListener> getCollection() {
        return Collections.unmodifiableList(listeners);
    }

    @Override
    public boolean isUpdate() {
        return listeners.size() > 0;
    }
}
