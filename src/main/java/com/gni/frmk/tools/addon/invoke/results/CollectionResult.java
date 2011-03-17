package com.gni.frmk.tools.addon.invoke.results;

import com.gni.frmk.tools.addon.invoke.ActionPattern.Result;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:54
 *
 * @author: e03229
 */
public interface CollectionResult<C extends Collection<? extends T>, T> extends Result {

    C getCollection();

}
