package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.AbstractComponentTest;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/06/11
 * Time: 10:13
 *
 * @author: e03229
 */
public class JmsTriggerTest extends AbstractComponentTest<JmsTrigger> {

    public JmsTriggerTest() {
        super(JmsTrigger.class);
    }

    @Override
    protected JmsTrigger buildComponent() {
        return JmsTrigger.builder()
                         .id(StringId.build("jms trigger name 1"))
                         .state(ActivableState.build(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                         .detail(PackageDetail.build("package name 1"))
                         .validate()
                         .build();
    }
}
