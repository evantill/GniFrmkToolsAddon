package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:36
 *
 * @author: e03229
 */
public interface ListComponentIdsStrategy<I extends ComponentId<I>> {

    Set<I> listIds(InvokeContext context) throws ServiceException;
}
