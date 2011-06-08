package com.gni.frmk.tools.addon.operation.handler.component.bytype;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.model.component.test.Component2Type;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/06/11
 * Time: 17:20
 *
 * @author: e03229
 */
public class ActionOnComponentTypeHandlerTest {

    public static final class Action extends ActionOnComponentType<ComponentType<?, ?, ?, ?, ?>, SetResult<String>> {
        public Action(ComponentType<?, ?, ?, ?, ?> type) {
            super(type);
        }
    }

    public static final class ActionStrategy1
            implements ActionOnComponentTypeStrategy<SetResult<String>, Action, ExecutionContext> {

        @Override
        public SetResult<String> execute(Action action, ExecutionContext context) throws ActionException {
            return SetResult.newInstance("string 11", "string 12");
        }
    }

    public static final class ActionStrategy2
            implements ActionOnComponentTypeStrategy<SetResult<String>, Action, ExecutionContext> {

        @Override
        public SetResult<String> execute(Action action, ExecutionContext context) throws ActionException {
            return SetResult.newInstance("string 21", "string 22");
        }
    }

    public static final class ActionContext
            extends ActionOnComponentTypeContext<ComponentType<?, ?, ?, ?, ?>, ActionOnComponentTypeStrategy<SetResult<String>, Action, ExecutionContext>, SetResult<String>, Action, ExecutionContext> {
    }

    public static final class ActionHandler
            extends ActionOnComponentTypeHandler<ActionOnComponentTypeStrategy<SetResult<String>, Action, ExecutionContext>, ComponentType<?, ?, ?, ?, ?>, Action, SetResult<String>, ExecutionContext> {

        @Override
        public Class<?> getActionType() {
            return Action.class;
        }

        public ActionHandler(ActionContext strategyContext) {
            super(strategyContext);
        }
    }

    @Test
    public void testExecute() throws Exception {
        ActionContext ctx = new ActionContext();
        ctx.register(Component1Type.TYPE, new ActionStrategy1());
        ctx.register(Component2Type.TYPE, new ActionStrategy2());
        ActionHandler handler = new ActionHandler(ctx);

        Action action = new Action(Component1Type.TYPE);
        ExecutionContext mock = Mockito.mock(ExecutionContext.class);
        SetResult<String> result = handler.execute(action, mock);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getCollection()).containsOnly("string 11", "string 12");

    }
}
