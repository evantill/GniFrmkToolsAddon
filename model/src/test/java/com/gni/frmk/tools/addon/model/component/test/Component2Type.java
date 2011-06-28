package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.ComponentTypeOrder;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 12:31
 *
 * @author: e03229
 */
@XmlRootElement
public class Component2Type extends BaseComponentType<Component2Type, Component2, Component2Id, Component2State, SimpleDetail> {

    public static final Component2Type TYPE = new Component2Type();

    private Component2Type() {
        super(Component2.class, Component2Id.class, Component2State.class, SimpleDetail.class);
    }

    @Override
    public Component2.Builder componentBuilder() {
        return Component2.builder();
    }

    @Override
    public Component2Id.Builder idBuilder() {
        return Component2Id.builder();
    }

    @Override
    public Component2State.Builder stateBuilder() {
        return Component2State.builder();
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

    @Override
    public ComponentTypeOrder getOpenSequenceOrder() {
        return ComponentTypeOrder.LAST;
    }

    @Override
    public ComponentTypeOrder getCloseSequenceOrder() {
        return ComponentTypeOrder.FIRST;
    }

    public static Component2Type newInstance() {
        return TYPE;
    }


}
