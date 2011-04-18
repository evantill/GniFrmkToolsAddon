package com.gni.frmk.tools.addon.result;

import com.gni.frmk.tools.addon.api.action.ActionException;
import com.gni.frmk.tools.addon.api.action.Result;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 17:44
 *
 * @author: e03229
 */
public abstract class AbstractResult implements Result {
    List<ActionException> errors = Lists.newArrayList();

    @Override
    public boolean hasError() {
        return !errors.isEmpty();
    }

    @Override
    public List<ActionException> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    @Override
    public void addError(ActionException error) {
        errors.add(checkNotNull(error));
    }

    public void addAllErrors(List<ActionException> exceptions) {
        for (ActionException exception : exceptions) {
            addError(exception);
        }
    }
}
