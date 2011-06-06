package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.AbstractComponentTest;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 14:57
 *
 * @author: e03229
 */
public class Component1Test extends AbstractComponentTest<Component1> {

    public Component1Test() {
        super(Component1.class);
    }

    @Override
    protected Component1 buildComponent() {
        return Component1.builder()
                         .id(Component1Id.builder().value("component id").build())
                         .state(Component1State.builder().enable(true).validate().build())
                         .detail(SimpleDetail.builder().description("description of component 1").validate().build())
                         .validate()
                         .build();

    }
}
