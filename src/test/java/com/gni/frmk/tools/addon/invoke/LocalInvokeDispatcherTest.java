package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.action.wmart.DisableConnection;
import com.wm.data.*;
import com.wm.lang.ns.NSName;
import org.junit.Test;
import org.mockito.Matchers;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/03/11
 * Time: 18:48
 *
 * @author: e03229
 */
public class LocalInvokeDispatcherTest {

    @Test
    public void testDisableConnection() throws Exception {
        InvokeServiceRegistry registry= new InvokeServiceRegistryBuilder().defineServices().build();
        InvokeContext contextMock= mock(InvokeContext.class);
        String aliasName = "aliasForTestConnection";
        DisableConnection action = new DisableConnection(aliasName);
        LocalInvokeDispatcher dispatcher = new LocalInvokeDispatcher(registry,contextMock);
        NoResult result = dispatcher.execute(action);
        verify(contextMock).invoke(same(action), eq(NSName.create("wm.art.admin.connection:disableConnection")), Matchers.<IData>any());
    }
}
