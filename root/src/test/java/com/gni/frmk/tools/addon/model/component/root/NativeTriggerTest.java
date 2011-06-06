package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.AbstractComponentTest;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/06/11
 * Time: 10:13
 *
 * @author: e03229
 */
public class NativeTriggerTest extends AbstractComponentTest<NativeTrigger> {

    public NativeTriggerTest() {
        super(NativeTrigger.class);
    }

    @Override
    protected NativeTrigger buildComponent() {
        return NativeTrigger.builder()
                            .id(StringId.build("native trigger name 1"))
                            .state(NativeTriggerState.builder()
                                                     .enabled(EnableState.build(EnableStatus.ENABLED))
                                                     .processingState(
                                                             TemporaryActivableState.builder()
                                                                                    .activable(ActivableStatus.ACTIVE)
                                                                                    .temporary(TemporaryStatus.PERMANENT)
                                                                                    .build())
                                                     .retrievalState(TemporaryActivableState.builder()
                                                                                            .activable(ActivableStatus.ACTIVE)
                                                                                            .temporary(TemporaryStatus.PERMANENT)
                                                                                            .build())
                                                     .build())
                            .detail(NoDetail.newInstance())
                            .validate()
                            .build();
    }
}
