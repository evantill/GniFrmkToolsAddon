package com.gni.frmk.tools.addon.operation.dispatcher;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 17:45
 *
 * @author: e03229
 */
public class SimpleDispatcherTest {

    @Test
    public void testCasting() throws DispatchException {
        InvokeContext ic = Mockito.mock(InvokeContext.class);

//        GetJmsAliasDetailHandler handler = Mockito.mock(GetJmsAliasDetailHandler.class);
//        when(handler.getActionType()).thenReturn(GetJmsAliasDetail.class);
//        when(handler.execute(any(GetJmsAliasDetail.class), eq(ic)))
//                .thenAnswer(new Answer<Object>() {
//                    @Override
//                    public Object answer(InvocationOnMock invocation) throws Throwable {
//                        GetJmsAliasDetail action = (GetJmsAliasDetail) invocation.getArguments()[0];
//                        return new SingleResult<JmsAliasDetail>(new JmsAliasDetail(
//                                "description " + action.getId().getValue()));
//                    }
//                });
//
//        ActionHandlerRegistry<InvokeContext> registry = new ActionHandlerRegistry<InvokeContext>();
//        registry.register(handler);
//
//        SimpleDispatcher<InvokeContext> dispatcher = new SimpleDispatcher<InvokeContext>(registry, ic);
//        SingleResult<JmsAliasDetail> result = dispatcher.execute(new GetJmsAliasDetail(new StringId("essai")));
//        Assert.assertEquals("description essai", result.getValue().getDescription());


    }
}
