package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.gni.frmk.tools.addon.tdd.impl.component.VisitorContext.VisitorStrategy;
import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponent;
import com.gni.frmk.tools.addon.tdd.impl.component.beta.BetaComponent;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/08/11
 * Time: 17:02
 *
 * @author: e03229
 */
public class StatefulComponentVisitorTest {

    private static class TestStatefulComponentVisitorFactory extends StatefulComponentVisitorFactory{
        @Override
        protected StatefulComponentVisitor newStatefulComponentVisitor() {
            return new TestStatefulComponentVisitor();
        }
    }

    private static class TestVisitorStrategy implements VisitorStrategy {
        @Override
        public Iterable<? extends Component<?, ?>> appplyVisitorStrategy(Iterable<? extends Component<?, ?>> components) {
            Iterable<? extends Component<?, ?>> alphaComponentOnly = Iterables.filter(components, AlphaComponent.class);
            return Ordering.natural().immutableSortedCopy(alphaComponentOnly);
        }
    }

    private static class TestStatefulComponentVisitor extends StatefulComponentVisitor {

        private List<Component<?, ?>> visitedComponents = Lists.newArrayList();

        private TestStatefulComponentVisitor() {
            super(new TestVisitorStrategy());
        }

        @Override
        public void visitComponentWithStrategy(Component<?, ?> visited) {
            visitedComponents.add(visited);
        }
    }

    private TestStatefulComponentVisitor visitor;

    @BeforeMethod
    public void setUp() throws Exception {
        visitor = new TestStatefulComponentVisitor();
    }

    @Test
    public void testVisit() throws Exception {
        final AlphaComponent a1 = AlphaComponent.newInstance(1, true);
        final AlphaComponent a2 = AlphaComponent.newInstance(2, true);
        final AlphaComponent a3 = AlphaComponent.newInstance(3, true);
        final BetaComponent b1 = BetaComponent.newInstance(1, true);
        final BetaComponent b2 = BetaComponent.newInstance(2, true);
        final BetaComponent b3 = BetaComponent.newInstance(3, true);

        Component mock = Mockito.mock(Component.class);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ComponentVisitor arg = (ComponentVisitor) invocation.getArguments()[0];
                b3.accept(arg);
                a3.accept(arg);
                b1.accept(arg);
                a1.accept(arg);
                a2.accept(arg);
                b2.accept(arg);
                return null;
            }
        }).when(mock).accept(Matchers.<ComponentVisitor>any());

        mock.accept(visitor.newVisitorContext());

        assertThat(visitor.visitedComponents).containsExactly(a1, a2, a3);
    }
}
