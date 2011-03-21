package com.gni.frmk.tools.addon.dispatcher;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:51
 *
 * @author: e03229
 */
public class SetResult<T> implements CollectionResult<Set<T>, T> {
    private final Set<T> set;

    public SetResult(Set<T> set) {this.set = set;}

    @Override
    public Set<T> getCollection() {
        return unmodifiableSet(set);
    }
}
