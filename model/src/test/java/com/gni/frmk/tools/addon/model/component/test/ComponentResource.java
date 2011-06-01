package com.gni.frmk.tools.addon.model.component.test;

import org.junit.rules.ExternalResource;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 14:58
 *
 * @author: e03229
 */
public class ComponentResource extends ExternalResource {

    public Component1 createComponent1() {
        return Component1.builder()
                         .id(Component1Id.builder().value("component id").build())
                         .state(Component1State.builder().enable(true).validate().build())
                         .detail(SimpleDetail.builder().description("description of component 1").validate().build())
                         .validate()
                         .build();
    }

    public Component2 createComponent2() {
        return Component2.builder()
                         .id(Component2Id.builder().value(666).build())
                         .state(Component2State.builder().enable(true).active(true).validate().build())
                         .detail(SimpleDetail.builder().description("description of component 2").validate().build())
                         .validate()
                         .build();
    }

    public Component1State createComponent1OpenState() {
        return Component1State.builder().enable(true).validate().build();
    }

    public Component1State createComponent1CloseState() {
        return Component1State.builder().enable(false).validate().build();
    }

    public Component2State createComponent2OpenState() {
        return Component2State.builder().enable(true).active(true).validate().build();
    }

    public Component2State createComponent2CloseState() {
        return Component2State.builder().enable(false).active(false).validate().build();
    }
}
