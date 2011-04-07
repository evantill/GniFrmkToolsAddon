package com.gni.frmk.tools.addon.command.dispatch.wm.invoke.util;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.ActionException;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.api.Result;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeException;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.context.InvokeContextRecord;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.context.InvokeContextRemote;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.context.InvokeContextReplay;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.util.PipelineTestUtils;

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

    protected R execute() throws ActionException, InvokeException {
        return getActionHandler().execute(getAction(), replay);
    }

    public R record(InvokeContextRemote ctx) throws ActionException, InvokeException {
        PipelineTestUtils utils = new PipelineTestUtils(getClass());
        InvokeContextRecord recorder = new InvokeContextRecord(ctx, utils);

        return getActionHandler().execute(getAction(), recorder);
    }

    protected abstract ActionHandler<A, R, InvokeContext> getActionHandler();

    protected abstract A getAction();
}
