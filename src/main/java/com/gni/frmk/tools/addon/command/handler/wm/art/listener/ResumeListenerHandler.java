package com.gni.frmk.tools.addon.command.handler.wm.art.listener;

import com.gni.frmk.tools.addon.command.action.wm.art.listener.ResumeListener;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.NoResult;
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
        super("pub.art.listener:resumeListener");
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
