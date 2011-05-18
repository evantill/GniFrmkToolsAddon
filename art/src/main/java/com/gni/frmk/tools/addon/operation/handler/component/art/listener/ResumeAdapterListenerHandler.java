package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.ResumeAdapterListener;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ResumeAdapterListenerHandler extends AbstractInvokeHandler<ResumeAdapterListener, NoResult>
        implements ActionHandler<ResumeAdapterListener, NoResult, InvokeContext> {

    public ResumeAdapterListenerHandler() {
        super("pub.art.listener:resumeListener");
    }

    @Override
    public Class<ResumeAdapterListener> getActionType() {
        return ResumeAdapterListener.class;
    }

    @Override
    protected IData prepareInput(ResumeAdapterListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName",in.getId().getName()}
        });
    }

    @Override
    protected NoResult parseOutput(ResumeAdapterListener action, IData output) {
        return NoResult.newInstance();
    }
}
