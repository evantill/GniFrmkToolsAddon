package com.gni.frmk.tools.addon.invoker.service.art;

import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterPollingNotificationInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import org.junit.Test;

import static com.gni.frmk.tools.addon.invoker.io.art.AdapterPollingNotificationInfo.builder;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 14:01
 *
 * @author: e03229
 */
public class ListAdapterPollingNotificationsTest extends AbstractServiceTest {

    @Test
    public void testInvoke() throws Exception {
        ListAdapterPollingNotifications service = new ListAdapterPollingNotifications();
        ListValueOutput<AdapterPollingNotificationInfo> output = service.invoke(AdapterTypeInput.newInstance("JDBCAdapter"), getContext());
        assertNotNull(output);

        AdapterPollingNotificationInfo expected = builder()
                .adapterType("JDBCAdapter")
                .notificationNodeName("pub.gni.error.actions.rejeu:rejeuNotif")
                .packageName("GniFrmkErrorEngine")
                .notificationState("yes")
                .build();

        assertEquals(1, output.getValues().size());
        assertEquals(expected, output.getValues().get(0));

    }
}
