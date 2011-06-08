package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.test.Component1Id;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.google.common.collect.Sets;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 14:25
 *
 * @author: e03229
 */
public class ListComponentIdsHandlerTest {

    public static final class TestStrategy
            extends ListComponentIdsStrategy<Component1Type, Component1Id> {

        @Override
        public Component1Type getType() {
            return Component1Type.TYPE;
        }

        @Override
        protected Set<Component1Id> listIds(InvokeContext context) throws ServiceException {
            Set<Component1Id> result = Sets.newHashSet();
            result.add(Component1Id.builder().value("component 1").build());
            result.add(Component1Id.builder().value("component 2").build());
            result.add(Component1Id.builder().value("component 3").build());
            result.add(Component1Id.builder().value("component 4").build());
            return result;
        }

    }

    @Test
    public void testExecute() throws Exception {
        ListComponentIdsContext context = new ListComponentIdsContext();
        context.registerStrategy(new TestStrategy());
        ListComponentIdsHandler handler = new ListComponentIdsHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        {
            ListComponentIds<? extends ComponentId<?>> action = ListComponentIds.build(Component1Type.TYPE);
            SetResult<? extends ComponentId<?>> result = handler.execute(action, ctx);
            assertThat(result).isNotNull();
            assertThat(result.getCollection()).isNotNull().hasSize(4);
        }
    }

}

