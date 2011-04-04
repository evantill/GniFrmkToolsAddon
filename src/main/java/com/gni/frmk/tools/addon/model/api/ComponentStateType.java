package com.gni.frmk.tools.addon.model.api;

import com.gni.frmk.tools.addon.model.component.state.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 13:51
 *
 * @author: e03229
 */
//TODO api ne peut pas avoir de dependances vers les implementations
public enum ComponentStateType {
    ACTIAVABLE_STATE(ActivableState.class),
    CONNECTABLE_STATE(ConnectableState.class),
    SCHEDULER_STATE(SchedulerState.class),
    TEMPORARY_ACTIVABLE_STATE(TemporaryActivableState.class),
    NATIVE_TRIGGER_STATE(NativeTriggerState.class),
    ENABLE_STATE(EnableState.class);

    private final Class<? extends ComponentState> concreteClass;

    ComponentStateType(Class<? extends ComponentState> concreteClass) {this.concreteClass = concreteClass;}

//    public abstract Builder stateBuilder()

}
