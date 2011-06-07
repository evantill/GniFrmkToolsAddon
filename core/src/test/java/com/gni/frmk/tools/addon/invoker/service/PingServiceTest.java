package com.gni.frmk.tools.addon.invoker.service;

import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.SingleValueOutput;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:41
 *
 * @author: e03229
 */
public class PingServiceTest extends AbstractServiceTest {
    @Test
    public void testInvoke() throws Exception {
        PingService service = new PingService();
        SingleValueOutput<String> output = service.invoke(NoInput.singleton, getContext());
        Assert.assertEquals("Wed May 25 14:45:41 CEST 2011", output.getValue());
    }
}
