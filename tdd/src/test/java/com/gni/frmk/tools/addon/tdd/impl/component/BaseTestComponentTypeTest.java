package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponent;
import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponentType;
import com.gni.frmk.tools.addon.tdd.impl.component.beta.BetaComponent;
import com.gni.frmk.tools.addon.tdd.impl.component.beta.BetaComponentType;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 17:49
 *
 * @author: e03229
 */
public class BaseTestComponentTypeTest {

    private AlphaComponentType alphaComponentType = new AlphaComponentType();
    private BetaComponentType betaComponentType = new BetaComponentType();
    private AlphaComponent a1 = AlphaComponent.newInstance(1, true);
    private BetaComponent b1 = BetaComponent.newInstance(1, true);

    @Test
    public void testAcceptComponent() throws Exception {
        assertThat(alphaComponentType.accept(a1)).isTrue();
        assertThat(alphaComponentType.accept(a1.getId())).isTrue();
        assertThat(alphaComponentType.accept(a1.getState())).isTrue();
        assertThat(alphaComponentType.accept(b1)).isFalse();
        assertThat(alphaComponentType.accept(b1.getId())).isFalse();
        assertThat(alphaComponentType.accept(b1.getState())).isFalse();
    }

    @Test
    public void testCheckComponent() throws Exception {
        alphaComponentType.check(a1);
        alphaComponentType.check(a1.getId());
        alphaComponentType.check(a1.getState());
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void testCheckInvalidComponent() {
        alphaComponentType.check(b1);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void testCheckInvalidComponentId() {
        alphaComponentType.check(b1.getId());
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void testCheckInvalidComponentState() {
        alphaComponentType.check(b1.getState());
    }

    @Test
    public void testCompareTo() throws Exception {
        assertThat(alphaComponentType.compareTo(betaComponentType)).isNotEqualTo(0);
        assertThat(alphaComponentType.compareTo(alphaComponentType)).isEqualTo(0);
    }
}
