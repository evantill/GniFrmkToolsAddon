package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.action.configuration.ClosePlateform;
import com.gni.frmk.tools.addon.action.wm.root.service.WaitServicesEnd;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ActionException;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.configuration.visitor.ClosePlateformVisitor;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:38
 *
 * @author: e03229
 */
public class ClosePlateformHandler
        implements ActionHandler<ClosePlateform, ConfigurationResult, InvokeContext> {

    public ClosePlateformHandler() {
    }

    @Override
    public Class<ClosePlateform> getActionType() {
        return ClosePlateform.class;
    }

    @Override
    public ConfigurationResult execute(ClosePlateform action, InvokeContext context) throws ActionException {
        final Configuration cnf = action.getConfiguration();
        final long maxSecondsToWait = action.getMaxSecondsToWait();
        final long delayBetweenTests = action.getDelayBetweenTest();
        final String filter = action.getFilter();
        final WaitServicesEnd waitServiceEndAction = new WaitServicesEnd(filter, maxSecondsToWait, delayBetweenTests);
        final ClosePlateformVisitor visitor = new ClosePlateformVisitor(waitServiceEndAction);
        visitor.dispatchVisit(cnf);
        List<Action<?>> subActions = visitor.produce();
        for (Action<?> subAction : subActions) {
            try {
                context.getDispatcher().execute(subAction);
            } catch (DispatchException e) {
                throw new ActionException(action, e);
            }
        }
        return new ConfigurationResult(cnf);
    }
}
