package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 19:21
 *
 * @author: e03229
 */
public class ConfigurationTest {

    @Rule
    public ConfigurationTestRule util = new ConfigurationTestRule();

    @Before
    public void xmlUnitConfiguration() {
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
    }

    @Test
    public void testValidation() {
        AdapterConnection cnx = AdapterConnection.builder()
                                                 .alias("alias")
                                                 .adapterType("adapterType")
                                                 .packageName("packageName")
                                                 .defineState(new EnableState(EnableStatus.ENABLED))
                                                 .build();
        ComponentConfiguration<AdapterConnection, EnableState> cnxConf = ComponentConfiguration.builder(AdapterConnection.class)
                                                                                               .defineComponent(cnx)
                                                                                               .defineOpenState(new EnableState(EnableStatus.ENABLED))
                                                                                               .defineCloseState(new EnableState(EnableStatus.DISABLED))
                                                                                               .build();
        Configuration cnf = Configuration.builder()
                                         .create("WmRoot","id", util.now())
                                         .addAdapterConnection(cnxConf)
                                         .build();
    }
}
