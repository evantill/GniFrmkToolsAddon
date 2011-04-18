package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.action.configuration.OpenPlateform;
import com.gni.frmk.tools.addon.api.Producer;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ActionException;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.api.action.ExecutionContext;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.handler.configuration.visitor.OpenPlateformFullVisitor;
import com.gni.frmk.tools.addon.handler.configuration.visitor.OpenPlateformVisitor;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 15:20
 *
 * @author: e03229
 */
public class OpenPlateformHandler implements ActionHandler<OpenPlateform, ConfigurationResult, ExecutionContext> {

    @Override
    public Class<OpenPlateform> getActionType() {
        return OpenPlateform.class;
    }

    @Override
    public ConfigurationResult execute(OpenPlateform action, ExecutionContext context) throws ActionException {
        final Configuration cnf = action.getConfiguration();
        ConfigurationVisitor visitor;
        Producer<List<Action<?>>> producer;
        if(action.isFull()){
            OpenPlateformFullVisitor visitorImpl = new OpenPlateformFullVisitor();
            visitor=visitorImpl;
            producer=visitorImpl;
        }else{
            OpenPlateformVisitor visitorImpl = new OpenPlateformVisitor();
            visitor=visitorImpl;
            producer=visitorImpl;
        }
        visitor.dispatchVisit(cnf);
        List<Action<?>> subActions = producer.produce();
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
