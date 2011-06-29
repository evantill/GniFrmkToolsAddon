package com.gni.frmk.tools.addon.operation.handler.configuration;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.action.configuration.OpenPlateform;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.ConfigurationResult;

import javax.enterprise.util.TypeLiteral;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 15:20
 *
 * @author: e03229
 */
public class OpenPlateformHandler implements ActionHandler<OpenPlateform, ConfigurationResult, ExecutionContext> {


    private static final TypeLiteral<OpenPlateform> TYPE_LITERAL = new TypeLiteral<OpenPlateform>() {};

    @Override
    public TypeLiteral<OpenPlateform> getActionType() {
        return TYPE_LITERAL;
    }

    @Override
    //TODO implementer
    public ConfigurationResult execute(OpenPlateform action, ExecutionContext context) throws ActionException {
        final Configuration cnf = action.getConfiguration();
//        ConfigurationVisitor visitor;
//        Producer<List<Action<?>>> producer;
        if(action.isFull()){
//            OpenPlateformFullVisitor visitorImpl = new OpenPlateformFullVisitor();
//            visitor=visitorImpl;
//            producer=visitorImpl;
        }else{
//            OpenPlateformVisitor visitorImpl = new OpenPlateformVisitor();
//            visitor=visitorImpl;
//            producer=visitorImpl;
        }
        //visitor.dispatchVisit(cnf);
//        List<Action<?>> subActions = producer.produce();
//        for (Action<?> subAction : subActions) {
//            try {
//                context.getDispatcher().execute(subAction);
//            } catch (DispatchException e) {
//                throw new ActionException(action, e);
//            }
//        }
        return new ConfigurationResult(cnf);
    }
}
