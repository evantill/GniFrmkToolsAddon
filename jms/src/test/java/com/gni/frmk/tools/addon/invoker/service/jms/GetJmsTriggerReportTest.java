package com.gni.frmk.tools.addon.invoker.service.jms;

import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsTriggerInfo;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 16:59
 *
 * @author: e03229
 */
public class GetJmsTriggerReportTest extends com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest {
    @Test
    public void testInvoke() throws Exception {
        GetJmsTriggerReport service = new GetJmsTriggerReport();
        ListValueOutput<JmsTriggerInfo> output = service.invoke(NoInput.singleton, getContext());
        Assert.assertNotNull(output.getValues());
        Assert.assertEquals(17, output.getValues().size());
        JmsTriggerInfo expected = JmsTriggerInfo.builder()
                                                .nodeName("GniCMMT_EvenementProduction_JMS.notification:Not_Q_QCFReceiver")
                                                .packageName("GniCMMT_EvenementProduction_JMS")
                                                .enabled(true)
                                                .running(true)
                                                .state(0)
                                                .build();
        JmsTriggerInfo value = output.getValues().get(0);
        assertEquals(expected, value);
    }
}
