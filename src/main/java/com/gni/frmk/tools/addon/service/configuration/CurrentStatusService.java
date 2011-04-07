package com.gni.frmk.tools.addon.service.configuration;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.Result;
import com.gni.frmk.tools.addon.model.component.AbstractComponent;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.service.configuration.strategy.ParseStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 09:47
 *
 * @author: e03229
 */
public class CurrentStatusService extends AbstractService{

    public CurrentStatusService() {
        super(new ParseStrategy());
    }

    @Override
    public <A extends Action<R>, R extends Result> R dispatch(A command) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(AdapterConnectionConfiguration visited) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(AdapterListenerConfiguration visited) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(AdapterNotificationConfiguration visited) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(PortConfiguration visited) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(SchedulerConfiguration visited) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(NativeTriggerConfiguration visited) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(JmsTriggerConfiguration visited) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(JmsAliasConfiguration visited) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visit(IntegrationServerPackageConfiguration visited) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
