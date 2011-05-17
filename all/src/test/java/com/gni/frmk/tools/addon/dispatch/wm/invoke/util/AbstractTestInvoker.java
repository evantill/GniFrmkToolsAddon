package com.gni.frmk.tools.addon.dispatch.wm.invoke.util;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.api.Result;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.context.InvokeContextRecord;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.context.InvokeContextRemote;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.context.InvokeContextReplay;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 16:40
 *
 * @author: e03229
 */
public abstract class AbstractTestInvoker<A extends Action<R>, R extends Result> {

    protected final PipelineTestUtils utils;
    protected final InvokeContextReplay replay;

    protected AbstractTestInvoker() {
        utils = new PipelineTestUtils(getClass());
        replay = new InvokeContextReplay(utils);
    }

    protected R execute() throws DispatchException {
        return getActionHandler().execute(getAction(), replay);
    }

    public R record(InvokeContextRemote ctx) throws DispatchException {
        PipelineTestUtils utils = new PipelineTestUtils(getClass());
        InvokeContextRecord recorder = new InvokeContextRecord(ctx, utils);

        return getActionHandler().execute(getAction(), recorder);
    }

    protected abstract ActionHandler<A, R, InvokeContext> getActionHandler();

    protected abstract A getAction();
}
