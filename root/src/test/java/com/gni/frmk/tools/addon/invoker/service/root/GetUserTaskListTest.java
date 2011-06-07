package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.SchedulerInfo;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import com.wm.data.*;
import com.wm.util.Values;
import junit.framework.Assert;
import org.junit.Test;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 18:20
 *
 * @author: e03229
 */
public class GetUserTaskListTest extends AbstractServiceTest {
    @Test
    public void testInvoke() throws Exception {
        GetUserTaskList service = new GetUserTaskList();
        ListValueOutput<SchedulerInfo> output = service.invoke(NoInput.singleton, getContext());
        List<SchedulerInfo> values = output.getValues();

        SchedulerInfo expected=SchedulerInfo.builder()
                .description("GniFrmkToolsStats.jdbc:schedulingStatistics")
                .expired(false)
                .name("GniFrmkToolsStats.jdbc:schedulingStatistics")
                .neverRun(true)
                .oid("3e75d780-ca57-11df-ac89-8ee26cc7631d")
                .service("GniFrmkToolsStats.jdbc:schedulingStatistics")
                .suspended(true)
                .type("repeat")
                .build();

        SchedulerInfo actual = values.get(0);
        Assert.assertEquals(expected,actual);
    }
}
