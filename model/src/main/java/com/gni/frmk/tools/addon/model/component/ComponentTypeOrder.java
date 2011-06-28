package com.gni.frmk.tools.addon.model.component;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 10:30
 *
 * @author: e03229
 */
public enum ComponentTypeOrder {
    //close order FIRST Scheduler,NativeTrigger,Port
    //open order FIRST AdapterConnection
    FIRST(0),
    //close order SECOND JmsTrigger AdpaterListener AdapterNotification
    //open order SECOND JmsAlias,Port
    SECOND(1),
    ORDER1(1),
    ORDER2(2),
    ORDER3(3),
    MIDDLE(5),
    //close order LAST  AdapterConnection JmsAlias
//open order LAST Scheduler NativeTrigger JmsTrigger AdpaterListener AdapterNotification
    LAST(10);

    private final int order;

    private ComponentTypeOrder(int order) {
        this.order = order;
    }

    public int order() {
        return order;
    }
}




