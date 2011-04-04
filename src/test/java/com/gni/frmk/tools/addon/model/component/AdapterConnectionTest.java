package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import junit.framework.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
public class AdapterConnectionTest {

    @Test
    public void testBuilder() {
        AdapterConnection cnx = AdapterConnection.builder()
                                                 .alias("alias")
                                                 .adapterType("jdbc")
                                                 .packageName("packageName")
                                                 .defineState(new EnableState(EnableStatus.ENABLED))
                                                 .build();
        Assert.assertNotNull(cnx.getComponentId());
    }

    @Test
    public void testDetails() throws JAXBException {
        AdapterConnection cnx = AdapterConnection.builder()
                                                 .alias("alias")
                                                 .adapterType("jdbc")
                                                 .packageName("packageName")
                                                 .defineState(new EnableState(EnableStatus.ENABLED))
                                                 .build();
        List<ComponentDetail> details = cnx.getDetails();
        for (ComponentDetail detail : details) {
            System.out.println("detail = " + detail);
        }
    }
}
