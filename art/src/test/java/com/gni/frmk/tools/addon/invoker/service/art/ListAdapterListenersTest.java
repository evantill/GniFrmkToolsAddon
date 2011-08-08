package com.gni.frmk.tools.addon.invoker.service.art;

import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterListenerInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 14:01
 *
 * @author: e03229
 */
//TODO ajouter un test avec des donnees
public class ListAdapterListenersTest extends AbstractServiceTest {

    @Test
    public void testNoResultInvoke() throws Exception {
        ListAdapterListeners service = new ListAdapterListeners();
        ListValueOutput<AdapterListenerInfo> output = service.invoke(AdapterTypeInput.newInstance("JDBCAdapter"), getContext());
        Assert.assertNotNull(output);
        Assert.assertEquals(0, output.getValues().size());
    }
}
