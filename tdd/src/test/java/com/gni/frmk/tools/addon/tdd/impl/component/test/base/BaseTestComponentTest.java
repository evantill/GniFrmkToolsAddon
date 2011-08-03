package com.gni.frmk.tools.addon.tdd.impl.component.test.base;

import com.gni.frmk.tools.addon.tdd.impl.component.test.alpha.AlphaComponent;
import com.gni.frmk.tools.addon.tdd.impl.component.test.alpha.AlphaComponentType;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 18:51
 *
 * @author: e03229
 */
public class BaseTestComponentTest {

    @Test
    public void testOpen() throws Exception {
        AlphaComponentType alphaComponentType = new AlphaComponentType();
        AlphaComponent a1 = AlphaComponent.newInstance(1, false);
        assertThat(a1.isOpened()).isFalse();
        a1.open();
        assertThat(a1.isOpened()).isTrue();
    }


    @Test(expectedExceptions = IllegalStateException.class)
    public void testReOpen() throws Exception {
        AlphaComponentType alphaComponentType = new AlphaComponentType();
        AlphaComponent a1 = AlphaComponent.newInstance(1, true);
        a1.open();
    }


    @Test
    public void testClose() throws Exception {
        AlphaComponentType alphaComponentType = new AlphaComponentType();
        AlphaComponent a1 = AlphaComponent.newInstance(1, true);
        assertThat(a1.isOpened()).isTrue();
        a1.close();
        assertThat(a1.isOpened()).isFalse();
    }


    @Test(expectedExceptions = IllegalStateException.class)
    public void testCloseAlreadyClosed() throws Exception {
        AlphaComponentType alphaComponentType = new AlphaComponentType();
        AlphaComponent a1 = AlphaComponent.newInstance(1, false);
        a1.close();
    }
}
