package com.gni.frmk.tools.addon.api.action;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 13:58
 *
 * @author: e03229
 */
public interface Result {
    boolean hasError();

    List<ActionException> getErrors();

    void addError(ActionException error);

    void addAllErrors(List<ActionException> exceptions);
}
