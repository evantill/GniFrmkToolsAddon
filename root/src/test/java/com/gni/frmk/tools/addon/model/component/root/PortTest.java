package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.base.AbstractComponentTest;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/06/11
 * Time: 10:13
 *
 * @author: e03229
 */
public class PortTest extends AbstractComponentTest<Port> {

    public PortTest() {
        super(Port.class);
    }

    @Override
    protected Port buildComponent() {
        return Port.builder()
                   .id(PackageAndStringId.newInstance("package 1", "port name 1"))
                   .state(ActivableState.build(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                   .detail(PortDetail.builder().primary(true).build())
                   .validate()
                   .build();
    }
}
