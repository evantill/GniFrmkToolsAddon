package com.gni.frmk.tools.addon.operation.result;

import com.gni.frmk.tools.addon.operation.api.CollectionResult;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:51
 *
 * @author: e03229
 */
public class SetResult<T>
        implements CollectionResult<Set<T>, T> {

    private final Set<T> set = Sets.newHashSet();

    public SetResult(Collection<T> collection) {
        set.addAll(collection);
    }

    @Override
    public Set<T> getCollection() {
        return unmodifiableSet(set);
    }

    public static <T> SetResult<T> newInstance(T... collection) {
        return new SetResult<T>(asList(collection));
    }

    public static <T> SetResult<T> newInstance(Collection<T> collection) {
        return new SetResult<T>(collection);
    }
}


