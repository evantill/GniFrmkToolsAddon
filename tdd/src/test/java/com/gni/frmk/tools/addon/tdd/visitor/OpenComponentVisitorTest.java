package com.gni.frmk.tools.addon.tdd.visitor;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.gni.frmk.tools.addon.tdd.impl.component.test.alpha.AlphaComponent;
import com.gni.frmk.tools.addon.tdd.impl.component.test.beta.BetaComponent;
import com.google.common.collect.Lists;
import org.fest.assertions.Assertions;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 11:10
 *
 * @author: e03229
 */
public class OpenComponentVisitorTest {
    private List<Component<?, ?>> visitedComponents;
    private Component a1, a2, a3, b1, b2, b3;
    private Component composite;
    private MockUtil mockUtil = new MockUtil();

    @BeforeMethod
    public void setUp() throws Exception {
        visitedComponents = Lists.newArrayList();
        boolean opened = false;
        a1 = decorateComponent(AlphaComponent.newInstance(1, opened));
        a2 = decorateComponent(AlphaComponent.newInstance(2, opened));
        a3 = decorateComponent(AlphaComponent.newInstance(3, opened));
        b1 = decorateComponent(BetaComponent.newInstance(1, opened));
        b2 = decorateComponent(BetaComponent.newInstance(2, opened));
        b3 = decorateComponent(BetaComponent.newInstance(3, opened));
        composite = defineCompositeComponent(b3, a3, b1, a1, a2, b2);
    }

    private <C extends Component<C, S>, S extends ComponentState<S>> Component<C, S>
    decorateComponent(Component<C, S> decorated) {
        final Component<C, S> mocked = Mockito.spy(decorated);
        doAnswer(interceptAcceptMethod(mocked)).when(mocked).accept(Matchers.<ComponentVisitor>any());
        doAnswer(interceptOpenMethod(mocked, decorated, visitedComponents)).when(mocked).open();
        return mocked;
    }

    private Answer<Void> interceptAcceptMethod(final Component<?, ?> mocked) {
        return new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ComponentVisitor visitor = (ComponentVisitor) invocation.getArguments()[0];
                visitor.visitComponent(mocked);
                return null;
            }
        };
    }

    private Answer<Void> interceptOpenMethod(final Component<?, ?> mocked, final Component<?, ?> decorated, final List<Component<?, ?>> spyVisitedComponents) {
        return new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                spyVisitedComponents.add(mocked);
                if (decorated != null) {
                    invocation.callRealMethod();
                }
                return null;
            }
        };
    }

    private Component defineCompositeComponent(final Component<?, ?>... childrenInOrder) {
        final int OPEN_FIRST = 1;
        Component mocked = mock(Component.class);
        doAnswer(interceptCompositeAcceptMethod(mocked, childrenInOrder)).when(mocked)
                .accept(Matchers.<ComponentVisitor>any());
        doAnswer(interceptOpenMethod(mocked, null, visitedComponents)).when(mocked).open();
        doAnswer(interceptGetOpenOrderMethod(OPEN_FIRST)).when(mocked).getOpenOrder();
        return mocked;
    }

    private Answer<Integer> interceptGetOpenOrderMethod(final int openPosition) {
        return new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return openPosition;
            }
        };
    }

    private Answer<Void> interceptCompositeAcceptMethod(final Component composite, final Component<?, ?>[] childrenInOrder) {
        return new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ComponentVisitor arg = (ComponentVisitor) invocation.getArguments()[0];
                for (Component<?, ?> child : childrenInOrder) {
                    child.accept(arg);
                }
                arg.visitComponent(composite);
                return null;
            }
        };
    }

    @Test
    public void testVisitComponent() throws Exception {
        OpenComponentVisitor visitor = new OpenComponentVisitor();

        visitor.startVisit();
        composite.accept(visitor);
        visitor.endVisit();

        Assertions.assertThat(visitedComponents).containsExactly(composite, a1, a2, a3, b1, b2, b3);

    }
}
