package com.gni.frmk.tools.addon.configuration;

import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.gni.frmk.tools.addon.configuration.components.AdapterConnection.AdapterConnectionBuilder;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 19:21
 *
 * @author: e03229
 */
public class ConfigurationTest {

    @Test
    public void testValidation(){
        AdapterConnection cnx=AdapterConnection.builder().alias("alias").packageName("packageName").build();
        Configuration cnf = Configuration.builder().addAdapterConnection(cnx).buildAndValidate();
    }
}
