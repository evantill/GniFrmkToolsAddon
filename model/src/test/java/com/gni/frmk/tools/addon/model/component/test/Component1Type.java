package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 10:32
 *
 * @author: e03229
 */
@XmlRootElement
public class Component1Type
        extends BaseComponentType<Component1Type, Component1, Component1Id, Component1State, SimpleDetail> {

    @XmlTransient
    public static final Component1Type TYPE = new Component1Type();

    private Component1Type() {
        super(Component1.class, Component1Id.class, Component1State.class, SimpleDetail.class);
    }

    @Override
    public Component1.Builder componentBuilder() {
        return Component1.builder();
    }

    @Override
    public Component1Id.Builder idBuilder() {
        return Component1Id.builder();
    }

    @Override
    public Component1State.Builder stateBuilder() {
        return Component1State.builder();
    }

    @Override
    public SimpleDetail.Builder detailBuilder() {
        return SimpleDetail.builder();
    }

    @Override
    public boolean isInput() {
        return true;
    }

    @Override
    public boolean isOutput() {
        return false;
    }

    public static Component1Type newInstance() {
        return TYPE;
    }


}
