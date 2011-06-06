package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:16
 *
 * @author: e03229
 */
@XmlRootElement
public class Component1
        extends BaseComponent<Component1, Component1Type, Component1Id, Component1State, SimpleDetail> {

    private Component1() {
        super(Component1Type.TYPE);

    }

    public Component1(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponent.Builder<Builder, Component1, Component1Type, Component1Id, Component1State, SimpleDetail> {

        public Builder() {
            super(Component1Type.newInstance());
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Component1 build() {
            return new Component1(this);
        }
    }

}
