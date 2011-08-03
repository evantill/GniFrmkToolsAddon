package com.gni.frmk.tools.addon.tdd.impl.component.test.base;

import com.gni.frmk.tools.addon.tdd.impl.component.test.alpha.AlphaComponentId;
import com.gni.frmk.tools.addon.tdd.impl.component.test.beta.BetaComponentId;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 18:13
 *
 * @author: e03229
 */
public class BaseTestComponentIdTest {

    private AlphaComponentId id1;
    private AlphaComponentId id1bis;
    private AlphaComponentId id2;
    private BetaComponentId id2bis;

    @BeforeMethod
    public void setUp() throws Exception {
        id1 = AlphaComponentId.newInstance(1);
        id1bis = AlphaComponentId.newInstance(1);
        id2 = AlphaComponentId.newInstance(2);
        id2bis = BetaComponentId.newInstance(2);
    }

    @Test
    public void testCompareTo() throws Exception {
        assertThat(id1.compareTo(id1bis)).isEqualTo(0);
        assertThat(id1.compareTo(id2)).isNotEqualTo(0);
        //compilation failure assertThat(id2.compareTo(id2bis)).isNotEqualTo(0);
    }


    @Test
    public void testEqualTo() throws Exception {
        assertThat(id1).isEqualTo(id1bis);
        assertThat(id1).isNotEqualTo(id2);
        assertThat(id2).isNotEqualTo(id2bis);
    }
}
