package com.gni.frmk.tools.addon.api.visitor;

import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Set;


/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 18:35
 *
 * @author: e03229
 */
public class ActionHandlerVisitorContext<T> implements VisitorContext {
    private final InvokeContext invokeContext;

    private final List<T> values = Lists.newArrayList();

    public ActionHandlerVisitorContext(InvokeContext invokeContext) {
        this.invokeContext = invokeContext;
    }

    public InvokeContext getInvokeContext() {
        return invokeContext;
    }

    public void add(T value) {
        if (value != null) {
            values.add(value);
        }
    }

    public T getSingleValue() {
        return values.get(0);
    }

    public List<T> getListValues() {
        return Collections.unmodifiableList(values);
    }

    public Set<T> getSetValues() {
        Set<T> set = Sets.newHashSet();
        set.addAll(values);
        return set;
    }
}
