package com.gni.frmk.tools.addon.invoke.handler.wmart;

import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.invoke.action.wmart.ResumeListener;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ResumeListenerHandler extends AbstractInvokeHandler<ResumeListener, NoResult>
        implements ActionHandler<ResumeListener, NoResult, InvokeContext> {

    public ResumeListenerHandler() {
        super("wm.art.admin.listener:resumeListener");
    }

    @Override
    public Class<ResumeListener> getActionType() {
        return ResumeListener.class;
    }

    @Override
    protected IData prepareInput(ResumeListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName",
                 in.getParameter()}
        });
    }

    @Override
    protected NoResult parseOutput(ResumeListener action, IData output) {
        return NoResult.newInstance();
    }
}
