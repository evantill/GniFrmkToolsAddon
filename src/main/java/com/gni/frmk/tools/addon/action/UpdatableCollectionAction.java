package com.gni.frmk.tools.addon.action;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.CollectionResult;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 10:12
 *
 * @author: e03229
 */
public interface UpdatableCollectionAction<T, C extends Collection<T>, R extends CollectionResult<C, T>> extends Action<R> {
    void setCollection(Collection<T> collection);

    void addToCollection(T element);

    C getCollection();

    boolean isUpdate();
}
