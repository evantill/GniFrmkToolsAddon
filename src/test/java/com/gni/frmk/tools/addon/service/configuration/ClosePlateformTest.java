package com.gni.frmk.tools.addon.service.configuration;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.Result;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTest;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTestRule;
import com.gni.frmk.tools.addon.service.configuration.strategy.CloseStrategy;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

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
        ClosePlateform service = new ClosePlateform(new CloseStrategyWithoutWait()) {
            @Override
            public <A extends Action<R>, R extends Result> R dispatch(A command) {
                System.out.printf("ClosePlateformTest.dispatch %s%n", command.toString());
                return null;
            }

        };

        Configuration cnf = ConfigurationTestRule.loadConfiguration(ConfigurationTest.class, "full");
        service.execute(cnf);
    }
}
