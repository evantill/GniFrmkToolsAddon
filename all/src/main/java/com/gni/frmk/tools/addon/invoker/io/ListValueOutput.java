package com.gni.frmk.tools.addon.invoker.io;

import com.gni.frmk.tools.addon.invoker.api.ServiceOutput;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 12:04
 *
 * @author: e03229
 */
public class ListValueOutput<T> implements ServiceOutput {
    private final List<T> values;

    private ListValueOutput(List<T> values) {
        this.values = unmodifiableList(values);
    }

    public List<T> getValues() {
        return values;
    }

    public static <T> ListValueOutput<T> newInstance(T... values) {
        return newInstance(asList(values));
    }

    public static <T> ListValueOutput<T> newInstance(Collection<T> values) {
        return new ListValueOutput<T>(unmodifiableList(newArrayList(values)));
    }
}
