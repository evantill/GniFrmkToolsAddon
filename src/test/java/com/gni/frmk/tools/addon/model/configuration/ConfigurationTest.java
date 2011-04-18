package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.ImmutableAdapterConnection;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.model.configuration.component.AdapterConnectionConfiguration;
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
        ImmutableAdapterConnection cnx = ImmutableAdapterConnection.builder()
                                                 .alias("alias")
                                                 .adapterType("adapterType")
                                                 .packageName("packageName")
                                                 .defineState(new EnableState(EnableStatus.ENABLED))
                                                 .build();
        EnableState closeState = new EnableState(EnableStatus.DISABLED);
        EnableState openState = new EnableState(EnableStatus.ENABLED);
        AdapterConnectionConfiguration cnxConf = AdapterConnectionConfiguration.builder()
                                                                               .defineComponent(cnx)
                                                                               .defineOpenState(openState)
                                                                               .defineCloseState(closeState)
                                                                               .select(true)
                                                                               .exist(false)
                                                                               .build();
        ImmutableConfiguration cnf = ImmutableConfiguration.builder()
                                         .create("WmRoot", "id", util.now())
                                         .addAdapterConnection(cnxConf)
                                         .build();
    }
}
