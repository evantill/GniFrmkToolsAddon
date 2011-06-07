package com.gni.frmk.tools.addon.invoker.service.art;

import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 11:20
 *
 * @author: e03229
 */
public class RetrieveAdapterTypesTest extends AbstractServiceTest {
    @Test
    public void testInvoke() throws Exception {
        RetrieveAdapterTypes service = new RetrieveAdapterTypes();
        ListValueOutput<String> output = service.invoke(NoInput.singleton, getContext());
        Assert.assertEquals(1,output.getValues().size());
        Assert.assertEquals("JDBCAdapter",output.getValues().get(0));

    }
}
