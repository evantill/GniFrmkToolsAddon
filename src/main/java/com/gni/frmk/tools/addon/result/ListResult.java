package com.gni.frmk.tools.addon.result;

import com.gni.frmk.tools.addon.api.action.CollectionResult;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:50
 *
 * @author: e03229
 */
public class ListResult<T>
        extends AbstractResult
        implements CollectionResult<List<T>, T> {
    private final List<T> list;

    public ListResult(List<T> list) {this.list = list;}

    public ListResult(Collection<T> collection) {
        list = Lists.newArrayList();
        list.addAll(collection);
    }

    @Override
    public List<T> getCollection() {
        return unmodifiableList(list);
    }
}
