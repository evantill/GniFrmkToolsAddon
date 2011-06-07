package com.gni.frmk.tools.addon.operation.handler.component.art;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.action.component.art.ListAdapterTypes;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInvokeException;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 17:13
 *
 * @author: e03229
 */
public abstract class ListAdapterTypeAwareIdsHandler
        <A extends Action<R> & ListAdapterTypeAwareIdsAction,R extends ListResult<I>, I extends ComponentId>
        extends AbstractInvokeHandler<A, R> {

    private final ListAdapterTypesHandler getAllAdapterType = new ListAdapterTypesHandler();

    protected ListAdapterTypeAwareIdsHandler(String serviceName) {
        super(serviceName);
    }

    @Override
    public R execute(A action, InvokeContext context) throws ServiceInvokeException {
        if(action.isFiltered()){
            return super.execute(action,context);
        }else{
             List<I> union = Lists.newArrayList();
            Set<String> adapterTypes = getAllAdapterType.execute(new ListAdapterTypes(), context)
                                                        .getCollection();
            for (String adapterType : adapterTypes) {
                union.addAll(execute(newFilteredAction(adapterType), context).getCollection());
            }
            return newListResult(union);
        }
    }

    protected abstract A newFilteredAction(String adapterType);
    protected abstract  R newListResult(List<I> idList);
}
