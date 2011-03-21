package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.dispatcher.Action;
import com.gni.frmk.tools.addon.dispatcher.ActionException;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.dispatcher.Result;
import com.gni.frmk.tools.addon.invoke.utils.PipelineTestUtils;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 16:40
 *
 * @author: e03229
 */
public abstract class AbstractInvokerTest<A extends Action<R>, R extends Result> {

    protected final PipelineTestUtils utils;
    protected final InvokeContextReplay replay;

    protected AbstractInvokerTest() {
        utils = new PipelineTestUtils(getClass());
        replay = new InvokeContextReplay(utils);
    }

    protected R execute() throws ActionException, InvokeException {
        return getActionHandler().execute(getAction(), replay);
    }

    public void record(RemoteInvokeContext ctx) throws ActionException, InvokeException {
        PipelineTestUtils utils = new PipelineTestUtils(getClass());
        InvokeContextRecord recorder = new InvokeContextRecord(ctx, utils);

        getActionHandler().execute(getAction(), recorder);
    }

    protected abstract ActionHandler<A, R, InvokeContext> getActionHandler();

    protected abstract A getAction();
}
