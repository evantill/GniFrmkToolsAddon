package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInfo;
import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInfo.NativeTriggerState;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 19:52
 *
 * @author: e03229
 */
public class GetTriggerReportTest extends AbstractServiceTest {


    @Test
    public void testInvoke() throws Exception {

        GetTriggerReport service = new GetTriggerReport();
        ListValueOutput<NativeTriggerInfo> output = service.invoke(NoInput.singleton, getContext());
        List<NativeTriggerInfo> triggers = output.getValues();
        Assert.assertNotNull(triggers);
        Assert.assertEquals(142, triggers.size());
        NativeTriggerInfo expected = NativeTriggerInfo.builder()
                                                      .name("BenchProcess.trigger:asyncTrigger")
                                                      .executeEnabled(true)
                                                      .processing(NativeTriggerState.PERMANENT_ACTIVE)
                                                      .retrieval(NativeTriggerState.PERMANENT_ACTIVE)
                                                      .build();
        NativeTriggerInfo value = output.getValues().get(0);
        assertEquals(expected, value);
    }

}
