package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.AbstractComponentTest;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/06/11
 * Time: 10:13
 *
 * @author: e03229
 */
public class SchedulerTest extends AbstractComponentTest<Scheduler> {

    public SchedulerTest() {
        super(Scheduler.class);
    }

    @Override
    protected Scheduler buildComponent() {
        return Scheduler.builder()
                        .id(StringId.build("scheduler name 1"))
                        .state(SchedulerState.build(SuspendedStatus.READY, SchedulerStatus.UNEXPIRED))
                        .detail(SchedulerDetail.builder()
                                               .name("scheduler name")
                                               .schedulerType("scheduler type")
                                               .service("service name")
                                               .description("description of scheduler")
                                               .build())
                        .validate()
                        .build();
    }
}
