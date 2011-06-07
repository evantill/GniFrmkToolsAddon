package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.SetValueOutput;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 18:52
 *
 * @author: e03229
 */
public class GetAllServiceStatsTest extends AbstractServiceTest {
    @Test
    public void testInvoke() throws Exception {

        GetAllServiceStats service = new GetAllServiceStats("COM.WM.ARTEXTDC");
        SetValueOutput<String> output = service.invoke(NoInput.singleton, getContext());
        Set<String> services = output.getValues();
        Assert.assertNotNull(services);
        Assert.assertEquals(1, services.size());
        Assert.assertEquals("BENCHPROCESS.UTILS.MONITOR:DECODEPROCESSMODELID",services.iterator().next());
    }
}
