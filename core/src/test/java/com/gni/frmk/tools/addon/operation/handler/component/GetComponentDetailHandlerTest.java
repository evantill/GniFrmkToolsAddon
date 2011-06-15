package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.test.Component1Id;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.model.component.test.Component2Id;
import com.gni.frmk.tools.addon.model.component.test.Component2Type;
import com.gni.frmk.tools.addon.model.component.test.SimpleDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 14:25
 *
 * @author: e03229
 */
public class GetComponentDetailHandlerTest {

    public static final class TestStrategy1
            implements GetComponentDetailStrategy<Component1Type, Component1Id, SimpleDetail> {

        @Override
        public Component1Type getComponentType() {
            return Component1Type.TYPE;
        }

        @Override
        public SimpleDetail getDetail(Component1Id componentId, InvokeContext context) throws ServiceException {
            return detailType1;
        }
    }

    public static final class TestStrategy2
            implements GetComponentDetailStrategy<Component2Type, Component2Id, SimpleDetail> {

        @Override
        public Component2Type getComponentType() {
            return Component2Type.TYPE;
        }

        @Override
        public SimpleDetail getDetail(Component2Id componentId, InvokeContext context) throws ServiceException {
            return detailType2;
        }

    }

    private static Component1Id idType1 = Component1Id.build("component 1");
    private static SimpleDetail detailType1 = SimpleDetail.newInstance("description component 1");
    private static Component2Id idType2 = Component2Id.build(1);
    private static SimpleDetail detailType2 = SimpleDetail.newInstance("description component 2");

    @Test
    public void testExecuteStrategy1() throws Exception {
        ActionContext<GetComponentDetailStrategy<?, ?, ?>> context = ActionContext.newContext();
        context.registerStrategy(new TestStrategy1());
        context.registerStrategy(new TestStrategy2());
        GetComponentDetailHandler handler = new GetComponentDetailHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        GetComponentDetail<Component1Id, SimpleDetail> action1 = GetComponentDetail.newInstance(Component1Type.TYPE, idType1);
        SingleResult<SimpleDetail> result1 = handler.executeTypeSafe(action1, ctx);
        assertThat(result1).isNotNull();
        assertThat(result1.getValue()).isNotNull().isEqualTo(detailType1);
    }

    @Test
    public void testExecuteStrategy2() throws Exception {
        ActionContext<GetComponentDetailStrategy<?, ?, ?>> context = ActionContext.newContext();
        context.registerStrategy(new TestStrategy1());
        context.registerStrategy(new TestStrategy2());
        GetComponentDetailHandler handler = new GetComponentDetailHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        GetComponentDetail<Component2Id, SimpleDetail> action1 = GetComponentDetail.newInstance(Component2Type.TYPE, idType2);
        SingleResult<SimpleDetail> result2 = handler.executeTypeSafe(action1, ctx);
        assertThat(result2).isNotNull();
        assertThat(result2.getValue()).isNotNull().isEqualTo(detailType2);
    }

    @Test(expected = ActionException.class)
    public void testExecuteStrategyUnknown() throws Exception {
        ActionContext<GetComponentDetailStrategy<?, ?, ?>> context = ActionContext.newContext();
        context.registerStrategy(new TestStrategy2());
        GetComponentDetailHandler handler = new GetComponentDetailHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        GetComponentDetail<Component1Id, SimpleDetail> action1 = GetComponentDetail.newInstance(Component1Type.TYPE, idType1);
        SingleResult<SimpleDetail> result1 = handler.executeTypeSafe(action1, ctx);
        fail("exception must be thrown");
    }
}

