package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.action.configuration.RefreshConfiguration;
import com.gni.frmk.tools.addon.api.action.*;
import com.gni.frmk.tools.addon.handler.configuration.visitor.RefreshConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.Component;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 13:40
 *
 * @author: e03229
 */
public class RefreshConfigurationHandler
        implements ActionHandler<RefreshConfiguration,ConfigurationResult,ExecutionContext{

    @Override
    public Class<RefreshConfiguration> getActionType() {
        return RefreshConfiguration.class;
    }

    @Override
    public ConfigurationResult execute(RefreshConfiguration action, ExecutionContext context) throws ActionException {
        Configuration configuration = action.getConfiguration();
        Configuration.Builder builder = Configuration.builder();
        builder.from(configuration);

        RefreshConfigurationVisitor visitor = new RefreshConfigurationVisitor();
        visitor.dispatchVisit(configuration);
        for (Action<? extends CollectionResult<? extends Collection<? extends Component>, ? extends Component>> subAction : visitor.produce()){
            try{
                CollectionResult<? extends Collection<? extends Component>, ? extends Component> result =context.getDispatcher().execute(subAction);
                for (Component updatedComponent : result.getCollection()) {
                    //TODO update a new Configuration
                }
            } catch (DispatchException e) {
                throw new ActionException(action,e);
            }
        }

        new ConfigurationResult()
    }
}
