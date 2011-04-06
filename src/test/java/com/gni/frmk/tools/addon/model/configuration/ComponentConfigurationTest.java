package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 16:11
 *
 * @author: e03229
 */
public class ComponentConfigurationTest {
    @Test
    public void testBuilder() throws Exception {
        AdapterConnection cnx = AdapterConnection.builder()
                                                 .alias("eeee")
                                                 .adapterType("jdbc")
                                                 .packageName("pck")
                                                 .defineState(new EnableState(EnableStatus.ENABLED))
                                                 .build();
        ComponentConfiguration<AdapterConnection, EnableState> cc;
        cc = ComponentConfiguration.builder(AdapterConnection.class)
                                   .exist(false)
                                   .select(true)
                                   .defineComponent(cnx)
                                   .defineOpenState(new EnableState(EnableStatus.ENABLED))
                                   .defineCloseState(new EnableState(EnableStatus.DISABLED))
                                   .build();
        Assert.assertNotNull(cc);
    }

}
