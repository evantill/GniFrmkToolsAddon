package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponent;
import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponentId;
import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponentState;
import com.gni.frmk.tools.addon.tdd.impl.component.beta.BetaComponent;
import org.fest.assertions.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    private AlphaComponentTypeToTest alphaChecker;
    private BaseTestComponent alphaComponent;
    private BaseTestComponent betaComponent;

    @BeforeMethod
    public void initChecker() {
        alphaChecker = new AlphaComponentTypeToTest();
        alphaComponent = AlphaComponent.newInstance(1,false);
        betaComponent = BetaComponent.newInstance(1,false);
    }

    @Test
    public void shouldCheckComponent() throws Exception {
        AlphaComponent checked = alphaChecker.check(alphaComponent);
        Assertions.assertThat(checked).isNotNull().isEqualTo(alphaComponent);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void shouldNotCheckComponent() throws Exception {
        AlphaComponent checked = alphaChecker.check(betaComponent);
    }

    @Test
    public void shouldAcceptComponent() throws Exception {
        boolean accepted = alphaChecker.accept(alphaComponent);
        Assertions.assertThat(accepted).isTrue();
    }

    @Test
    public void shouldCheckId() throws Exception {
        AlphaComponentId checked = alphaChecker.check(alphaComponent.getId());
        Assertions.assertThat(checked).isNotNull().isEqualTo(alphaComponent.getId());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void shouldNotCheckId() throws Exception {
        AlphaComponentId checked = alphaChecker.check(betaComponent.getId());
    }

    @Test
    public void shouldAcceptId() throws Exception {
        boolean accepted = alphaChecker.accept(alphaComponent.getId());
        Assertions.assertThat(accepted).isTrue();
    }

    @Test
    public void shouldNotAcceptId() throws Exception {
        boolean accepted = alphaChecker.accept(betaComponent.getId());
        Assertions.assertThat(accepted).isFalse();
    }

    @Test
    public void shouldCheckState() throws Exception {
        AlphaComponentState checked = alphaChecker.check(alphaComponent.getState());
        Assertions.assertThat(checked).isNotNull().isEqualTo(alphaComponent.getState());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void shouldNotCheckState() throws Exception {
        AlphaComponentState checked = alphaChecker.check(betaComponent.getState());
    }

    @Test
    public void shouldAcceptState() throws Exception {
        boolean accepted = alphaChecker.accept(alphaComponent.getState());
        Assertions.assertThat(accepted).isTrue();
    }

    @Test
    public void shouldNotAcceptState() throws Exception {
        boolean accepted = alphaChecker.accept(betaComponent.getState());
        Assertions.assertThat(accepted).isFalse();
    }
}
