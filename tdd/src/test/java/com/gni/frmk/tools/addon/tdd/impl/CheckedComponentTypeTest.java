package com.gni.frmk.tools.addon.tdd.impl;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.impl.alpha.AlphaComponent;
import com.gni.frmk.tools.addon.tdd.impl.alpha.AlphaComponentId;
import com.gni.frmk.tools.addon.tdd.impl.alpha.AlphaComponentState;
import com.gni.frmk.tools.addon.tdd.impl.beta.BetaComponent;
import com.gni.frmk.tools.addon.tdd.impl.beta.BetaComponentId;
import com.gni.frmk.tools.addon.tdd.impl.beta.BetaComponentState;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:20
 *
 * @author: e03229
 */
public class CheckedComponentTypeTest {

    private class AlphaComponentTypeToTest
            extends CheckedComponentType<AlphaComponent, AlphaComponentId, AlphaComponentState> {
        private AlphaComponentTypeToTest() {
            super(AlphaComponent.class, AlphaComponentId.class, AlphaComponentState.class);
        }
    }

    private class BetaComponentTypeToTest
            extends CheckedComponentType<BetaComponent, BetaComponentId, BetaComponentState> {
        private BetaComponentTypeToTest() {
            super(BetaComponent.class, BetaComponentId.class, BetaComponentState.class);
        }
    }

    private AlphaComponentTypeToTest checker;
    private Component alpha;
    private Component beta;

    @Before
    public void initChecker() {
        checker = new AlphaComponentTypeToTest();
        alpha = new AlphaComponent();
        beta = new BetaComponent();
    }

    @Test
    public void shouldCheckComponent() throws Exception {
        AlphaComponent checked = checker.check(alpha);
        Assertions.assertThat(checked).isNotNull().isEqualTo(alpha);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotCheckComponent() throws Exception {
        AlphaComponent checked = checker.check(beta);
        fail();
    }

    @Test
    public void shouldAcceptComponent() throws Exception {
        boolean accepted = checker.accept(alpha);
        Assertions.assertThat(accepted).isTrue();
    }

    @Test
    public void shouldCheckId() throws Exception {
        AlphaComponentId checked = checker.check(alpha.getId());
        Assertions.assertThat(checked).isNotNull().isEqualTo(alpha.getId());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotCheckId() throws Exception {
        AlphaComponentId checked = checker.check(beta.getId());
        fail();
    }

    @Test
    public void shouldAcceptId() throws Exception {
        boolean accepted = checker.accept(alpha.getId());
        Assertions.assertThat(accepted).isTrue();
    }

    @Test
    public void shouldNotAcceptId() throws Exception {
        boolean accepted = checker.accept(beta.getId());
        Assertions.assertThat(accepted).isFalse();
    }

    @Test
    public void shouldCheckState() throws Exception {
        AlphaComponentState checked = checker.check(alpha.getState());
        Assertions.assertThat(checked).isNotNull().isEqualTo(alpha.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotCheckState() throws Exception {
        AlphaComponentState checked = checker.check(beta.getState());
        fail();
    }

    @Test
    public void shouldAcceptState() throws Exception {
        boolean accepted = checker.accept(alpha.getState());
        Assertions.assertThat(accepted).isTrue();
    }

    @Test
    public void shouldNotAcceptState() throws Exception {
        boolean accepted = checker.accept(beta.getState());
        Assertions.assertThat(accepted).isFalse();
    }
}
