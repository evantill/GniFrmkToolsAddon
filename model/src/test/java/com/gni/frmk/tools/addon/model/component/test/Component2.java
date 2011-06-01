package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponent;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 12:31
 *
 * @author: e03229
 */
@XmlRootElement
public class Component2
        extends BaseComponent<Component2, Component2Type, Component2Id, Component2State, SimpleDetail> {

    private Component2() {
    }

    public Component2(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends BaseComponent.Builder<Builder, Component2, Component2Type, Component2Id, Component2State, SimpleDetail> {

        public Builder() {
            super(Component2Type.newInstance());
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Component2 build() {
            return new Component2(this);
        }
    }

}
