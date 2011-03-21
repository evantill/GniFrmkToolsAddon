package com.gni.frmk.tools.addon.dispatcher;

import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:50
 *
 * @author: e03229
 */
public class ListResult<T> implements CollectionResult<List<T>, T> {
    private final List<T> list;

    public ListResult(List<T> list) {this.list = list;}

    @Override
    public List<T> getCollection() {
        return unmodifiableList(list);
    }
}
