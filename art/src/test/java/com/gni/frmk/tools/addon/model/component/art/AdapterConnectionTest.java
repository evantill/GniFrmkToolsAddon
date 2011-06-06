package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.EnableState;
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
public class AdapterConnectionTest extends AbstractComponentTest<AdapterConnection> {

    public AdapterConnectionTest() {
        super(AdapterConnection.class);
    }

    @Override
    protected AdapterConnection buildComponent() {
        return AdapterConnection.builder()
                                .id(AdapterId.build("JDBCAdapter", "adapter connection name 1"))
                                .state(EnableState.build(EnableStatus.ENABLED))
                                .detail(PackageDetail.build("package name 1"))
                                .validate()
                                .build();
    }
}
