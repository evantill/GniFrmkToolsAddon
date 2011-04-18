package com.gni.frmk.tools.addon.action.wm.art.listener;

import com.gni.frmk.tools.addon.action.StringAction;
import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterListener.MutableAdapterListener;
import com.gni.frmk.tools.addon.result.ListResult;
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
        extends StringAction<ListResult<MutableAdapterListener>>
        implements UpdatableCollectionAction<MutableAdapterListener, List<MutableAdapterListener>, ListResult<MutableAdapterListener>> {

    private final List<MutableAdapterListener> listeners = Lists.newArrayList();

    public ListListeners(String adapterType) {
        super(adapterType);
    }

    @Override
    public void setCollection(Collection<MutableAdapterListener> collection) {
        listeners.addAll(collection);
    }

    @Override
    public void addToCollection(MutableAdapterListener element) {
        listeners.add(element);
    }

    @Override
    public List<MutableAdapterListener> getCollection() {
        return Collections.unmodifiableList(listeners);
    }

    @Override
    public boolean isUpdate() {
        return listeners.size() > 0;
    }
}
