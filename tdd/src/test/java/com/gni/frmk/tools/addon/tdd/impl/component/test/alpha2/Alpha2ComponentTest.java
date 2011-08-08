package com.gni.frmk.tools.addon.tdd.impl.component.test.alpha2;

import com.gni.frmk.tools.addon.tdd.api.ComponentStatus;
import com.gni.frmk.tools.addon.tdd.impl.component.test.alpha2.Alpha2Component.Alpha2ComponentState;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/08/11
 * Time: 18:48
 *
 * @author: e03229
 */
public class Alpha2ComponentTest {

    @Test
    public void testComponent() throws Exception {
        Alpha2Component component = Alpha2Component.newInstance(1, ComponentStatus.CLOSED);
        assertThat(component.getType()).isNotNull().isEqualTo(Alpha2ComponentType.newInstance());
        assertThat(component.getId()).isNotNull().isEqualTo(Alpha2ComponentId.newInstance(1));
        assertThat(component.getStatus()).isNotNull().isEqualTo(ComponentStatus.UNKNOWN);
    }

    @Test
    public void testOpen() throws Exception {
        Alpha2Component component = Alpha2Component.newInstance(1, ComponentStatus.CLOSED);
        component.refreshStatus();
        assertThat(component).isNotNull();
        assertThat(component.getStatus().isOpened()).isFalse();
        assertThat(component.getStatus().isClosed()).isTrue();
        component.open();
        assertThat(component.getStatus().isOpened()).isTrue();
        assertThat(component.getStatus().isClosed()).isFalse();
    }

    @Test
    public void testRefreshStatus() throws Exception {
        Alpha2Component component = Alpha2Component.newInstance(1, ComponentStatus.OPENED);
        assertThat(component.getStatus()).isEqualTo(ComponentStatus.UNKNOWN);
        assertThat(component.getStatus().isDefined()).isFalse();
        assertThat(component.getStatus().isUnknown()).isTrue();
        component.refreshStatus();
        assertThat(component.getStatus()).isEqualTo(ComponentStatus.OPENED);
        assertThat(component.getStatus().isDefined()).isTrue();
        assertThat(component.getStatus().isUnknown()).isFalse();
        assertThat(component.getStatus().isOpened()).isTrue();
        assertThat(component.getStatus().isTransient()).isFalse();
        assertThat(component.getStatus().isClosed()).isFalse();
    }

    @Test
    public void testClose() throws Exception {
        Alpha2Component component = Alpha2Component.newInstance(1, ComponentStatus.OPENED);
        component.refreshStatus();
        assertThat(component).isNotNull();
        assertThat(component.getStatus().isOpened()).isTrue();
        assertThat(component.getStatus().isClosed()).isFalse();
        component.close();
        assertThat(component.getStatus().isOpened()).isFalse();
        assertThat(component.getStatus().isClosed()).isTrue();
    }

    @Test
    public void testSaveRestoreState() throws Exception {
        Alpha2Component component = Alpha2Component.newInstance(1, ComponentStatus.CLOSED);
        Alpha2ComponentState savedState = component.saveState();
        assertThat(savedState).isNotNull();
        assertThat(savedState.isFrom(component)).isTrue();
        assertThat(component.getStatus()).isNotNull().isEqualTo(ComponentStatus.UNKNOWN);
        assertThat(component.getStatus().isOpened()).isFalse();
        component.refreshStatus();
        component.open();
        assertThat(component.getStatus()).isNotNull().isEqualTo(ComponentStatus.OPENED);
        assertThat(component.getStatus().isOpened()).isTrue();
        component.restoreState(savedState);
        assertThat(component.getStatus()).isNotNull().isEqualTo(ComponentStatus.UNKNOWN);
        assertThat(component.getStatus().isOpened()).isFalse();
    }

}
