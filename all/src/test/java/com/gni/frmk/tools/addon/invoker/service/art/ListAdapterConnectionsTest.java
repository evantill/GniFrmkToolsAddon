package com.gni.frmk.tools.addon.invoker.service.art;

import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterConnectionInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

import static com.gni.frmk.tools.addon.invoker.io.art.AdapterConnectionInfo.builder;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 11:53
 *
 * @author: e03229
 */
public class ListAdapterConnectionsTest extends AbstractServiceTest {

    @Test
    public void testInvoke() throws Exception {
        RetrieveAdapterTypes retrieveAdapterTypes = new RetrieveAdapterTypes();
        List<String> adapterTypes = retrieveAdapterTypes.invoke(NoInput.singleton, getContext()).getValues();
        for (String adapterType : adapterTypes) {
            ListAdapterConnections listAdapterConnections = new ListAdapterConnections();
            ListValueOutput<AdapterConnectionInfo> output = listAdapterConnections.invoke(AdapterTypeInput.newInstance(adapterType), getContext());
            List<AdapterConnectionInfo> values = output.getValues();
            Assert.assertNotNull(values);
            Assert.assertTrue(values.size() > 0);
            AdapterConnectionInfo singleValue = values.get(0);
            AdapterConnectionInfo expected = builder()
                    .adapterType("JDBCAdapter")
                    .alias("GniCMMT_EvenementProduction_Commun.connecteur:cnxTransco")
                    .packageName("GniCMMT_EvenementProduction_Commun")
                    .connectionState("enabled")
                    .build();
            Assert.assertEquals(expected, singleValue);

        }
    }

}

