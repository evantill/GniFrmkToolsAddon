package com.gni.frmk.tools.addon.operation.handler.component.art;

import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.operation.action.component.art.RetrieveAdapterTypesList;
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
public abstract class AdapterTypeAwareHandler
        <A extends Action<R> & AdapterTypeAwareAction,R extends ListResult<I>, I extends Component.Id>
        extends AbstractInvokeHandler<A, R> {

    private final RetrieveAdapterTypesListHandler getAllAdapterType = new RetrieveAdapterTypesListHandler();

    protected AdapterTypeAwareHandler(String serviceName) {
        super(serviceName);
    }

    @Override
    public R execute(A action, InvokeContext context) throws ServiceInvokeException {
        if(action.isFiltered()){
            return super.execute(action,context);
        }else{
             List<I> union = Lists.newArrayList();
            Set<String> adapterTypes = getAllAdapterType.execute(new RetrieveAdapterTypesList(), context)
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
