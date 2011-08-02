package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponentId;
import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponentState;
import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponentType;
import com.gni.frmk.tools.addon.tdd.impl.component.beta.BetaComponentId;
import com.gni.frmk.tools.addon.tdd.impl.component.beta.BetaComponentState;
import com.gni.frmk.tools.addon.tdd.impl.component.beta.BetaComponentType;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 18:32
 *
 * @author: e03229
 */
public class BaseTestComponentStateTest {

    private AlphaComponentState state1;
    private AlphaComponentState state1bis;
    private AlphaComponentState state2;
    private BetaComponentState state2bis;

    @BeforeMethod
    public void setUp() throws Exception {
        AlphaComponentType alphaType = Mockito.mock(AlphaComponentType.class);
        BetaComponentType betaType = Mockito.mock(BetaComponentType.class);
        state1 = AlphaComponentState.newInstance(alphaType, AlphaComponentId.newInstance(1));
        state1bis = AlphaComponentState.newInstance(alphaType, AlphaComponentId.newInstance(1));
        state2 = AlphaComponentState.newInstance(alphaType, AlphaComponentId.newInstance(2));
        state2bis = BetaComponentState.newInstance(betaType, BetaComponentId.newInstance(2));
    }

    @Test
    public void testCompareTo() throws Exception {
        assertThat(state1.compareTo(state1bis)).isEqualTo(0);
        assertThat(state1.compareTo(state2)).isNotEqualTo(0);
        //compilation failure assertThat(id2.compareTo(id2bis)).isNotEqualTo(0);
    }


    @Test
    public void testEqualTo() throws Exception {
        assertThat(state1).isEqualTo(state1bis);
        assertThat(state1).isNotEqualTo(state2);
        assertThat(state2).isNotEqualTo(state2bis);
    }
}