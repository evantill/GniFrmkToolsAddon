package com.gni.frmk.tools.addon.service.configuration;

import com.gni.frmk.tools.addon.command.action.wm.art.DisableConnection;
import com.gni.frmk.tools.addon.command.action.wm.art.DisableListener;
import com.gni.frmk.tools.addon.command.action.wm.art.DisableNotification;
import com.gni.frmk.tools.addon.command.action.wm.jms.DisableJmsAlias;
import com.gni.frmk.tools.addon.command.action.wm.jms.SuspendJmsTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.DisablePackage;
import com.gni.frmk.tools.addon.command.action.wm.root.DisablePortListener;
import com.gni.frmk.tools.addon.command.action.wm.root.SuspendTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.SuspendUserTask;
import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.Result;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTest;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTestRule;
import com.gni.frmk.tools.addon.service.configuration.strategy.CloseStrategy;
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
public class ClosePlateformTest {

    @Rule
    public ConfigurationTestRule testUtil = new ConfigurationTestRule();

    class CloseStrategyWithoutWait extends CloseStrategy {

        @Override
        protected void waitServicesEnd() {
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
        ClosePlateform service = new ClosePlateform(new CloseStrategyWithoutWait()) {
            @Override
            public <A extends Action<R>, R extends Result> R dispatch(A command) {
                realActions.add(command.getClass());
                return null;
            }

        };
        Configuration cnf = ConfigurationTestRule.loadConfiguration(ConfigurationTest.class, "full");
        service.execute(cnf);
        //check results
        Assert.assertEquals(expectedActions, realActions);
    }
}
