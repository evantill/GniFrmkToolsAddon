package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.base.AbstractComponentTest;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/06/11
 * Time: 10:13
 *
 * @author: e03229
 */
public class AdapterListenerTest extends AbstractComponentTest<AdapterListener> {

    public AdapterListenerTest() {
        super(AdapterListener.class);
    }

    @Override
    protected AdapterListener buildComponent() {
        return AdapterListener.builder()
                              .id(AdapterId.build("JDBCAdapter", "adapter listener name 1"))
                              .state(ActivableState.build(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                              .detail(PackageDetail.build("package name 1"))
                              .validate()
                              .build();
    }
}
