package com.gni.frmk.tools.addon.invoker.io;

import com.gni.frmk.tools.addon.invoker.api.ServiceOutput;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 12:04
 *
 * @author: e03229
 */
public class SetValueOutput<T> implements ServiceOutput {
    private final Set<T> values;

    private SetValueOutput(Set<T> values) {
        this.values = unmodifiableSet(values);
    }

    public Set<T> getValues() {
        return values;
    }

    public static <T> SetValueOutput<T> newInstance(T... values) {
        return newInstance(asList(values));
    }

    public static <T> SetValueOutput<T> newInstance(Collection<T> values) {
        return new SetValueOutput<T>(unmodifiableSet(Sets.newHashSet(values)));
    }
}
