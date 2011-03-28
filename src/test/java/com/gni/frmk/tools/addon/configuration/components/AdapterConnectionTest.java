package com.gni.frmk.tools.addon.configuration.components;

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
        AdapterConnection cnx = AdapterConnection.builder().alias("alias").packageName("packageName").build();
        Assert.assertNotNull(cnx.getComponentId());
    }

    @Test
    public void testDetails() throws JAXBException {
        AdapterConnection cnx = AdapterConnection.builder().alias("alias").packageName("packageName").build();
        List<ComponentDetail> details = cnx.getDetails();
        for (ComponentDetail detail : details) {
            System.out.println("detail = " + detail);
        }
    }
}
