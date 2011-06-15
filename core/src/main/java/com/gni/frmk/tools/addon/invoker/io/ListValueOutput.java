package com.gni.frmk.tools.addon.invoker.io;

import com.gni.frmk.tools.addon.invoker.api.ServiceOutput;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

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

    /**
     * @param filter to select a single value
     * @return the first element matching the filter
     * @throws NoSuchElementException if element is not found.
     */
    public T getSingleValue(Predicate<? super T> filter) throws NoSuchElementException {
        return Collections2.filter(values, filter).iterator().next();
    }

    /**
     * @param filter to select a single value
     * @return the first element matching the filter or null if not found
     */
    public T getOptionalSingleValue(Predicate<? super T> filter) {
        Collection<T> filtered = Collections2.filter(values, filter);
        if (filtered.size() > 0) {
            return filtered.iterator().next();
        } else {
            return null;
        }
    }


    public static <T> ListValueOutput<T> newInstance(T... values) {
        return newInstance(asList(values));
    }

    public static <T> ListValueOutput<T> newInstance(Collection<T> values) {
        return new ListValueOutput<T>(unmodifiableList(newArrayList(values)));
    }
}
