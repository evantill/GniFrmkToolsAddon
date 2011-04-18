package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.action.configuration.ClosePlateform;
import com.gni.frmk.tools.addon.action.wm.art.connection.DisableConnection;
import com.gni.frmk.tools.addon.action.wm.art.listener.DisableListener;
import com.gni.frmk.tools.addon.action.wm.art.notifications.DisableNotification;
import com.gni.frmk.tools.addon.action.wm.jms.alias.DisableJmsAlias;
import com.gni.frmk.tools.addon.action.wm.jms.trigger.SuspendJmsTriggers;
import com.gni.frmk.tools.addon.action.wm.root.ispackage.DisablePackage;
import com.gni.frmk.tools.addon.action.wm.root.port.DisablePortListener;
import com.gni.frmk.tools.addon.action.wm.root.scheduler.SuspendUserTask;
import com.gni.frmk.tools.addon.action.wm.root.trigger.SuspendTriggers;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeDispatcher;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeDispatcherDecorator;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeServiceRegistry;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.context.InvokeContextReplay;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.util.PipelineTestUtils;
import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationSerializer;
import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationRepository;
import com.gni.frmk.tools.addon.handler.configuration.strategy.CloseStrategy;
import com.gni.frmk.tools.addon.handler.configuration.wm.ISLocalConfigurationRepository;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTest;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTestRule;
import com.gni.frmk.tools.addon.result.ConfigurationResult;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:14
 *
 * @author: e03229
 */
public class ClosePlateformHandlerTest {

    @Rule
    public ConfigurationTestRule testUtil = new ConfigurationTestRule();

    class CloseStrategyWithoutWait extends CloseStrategy {

        @Override
        protected void waitServicesEnd(Operation o) {
        }
    }

    @Test
    public void testExecute() throws Exception {
        final List<Class<?>> realActions = Lists.newArrayList();
        //init expected results
        final List<Class<?>> expectedActions = Lists.newArrayList();
        Class[] actionClasses = new Class[]{
                DisableListener.class,
                DisableNotification.class,
                SuspendTriggers.class,
                SuspendJmsTriggers.class,
                SuspendUserTask.class,
                DisablePortListener.class,
                DisableConnection.class,
                DisableJmsAlias.class,
                DisablePackage.class
        };
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                expectedActions.add(actionClasses[i]);
            }
        }
        //execute service
        IntegrationServerUtil util = new IntegrationServerUtil("GniFrmkToolsAddon");
        ConfigurationSerializer serializer = new ConfigurationSerializer();
        ConfigurationRepository repository = new ISLocalConfigurationRepository(util, serializer);
        InvokeServiceRegistry registry = new InvokeServiceRegistry();
        PipelineTestUtils utils = new PipelineTestUtils(ClosePlateformHandlerTest.class);
        InvokeContext invokeContext = new InvokeContextReplay(utils);
        InvokeDispatcher dispatcher = new InvokeDispatcher(registry, invokeContext);
        InvokeDispatcherDecorator dispatcherDecorator = new InvokeDispatcherDecorator(dispatcher);
        ClosePlateformHandler service = new ClosePlateformHandler(repository, dispatcherDecorator);

        Configuration cnf = ConfigurationTestRule.loadConfiguration(ConfigurationTest.class, "full");
        ConfigurationResult result = dispatcher.execute(new ClosePlateform(cnf, 0));
        //check results
        Assert.assertEquals(expectedActions, realActions);
    }
}
