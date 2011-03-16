package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.NoResult;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
 class ResumeListenerInvoker extends AbstractInvoker<ResumeListener, NoResult>
        implements ActionHandler<ResumeListener, NoResult, InvokeContext> {

    public ResumeListenerInvoker() {
        super("wm.art.admin.listener:resumeListener");
    }

    @Override
    public Class<ResumeListener> getActionType() {
        return ResumeListener.class;
    }

    @Override
    protected IData prepareInput(ResumeListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName", in.getParameter()}
        });
    }

    @Override
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }
}
