package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.ComponentTypeOrder;
import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 16:19
 *
 * @author: e03229
 */
@XmlRootElement
public class NativeTriggerType
        extends BaseComponentType<NativeTriggerType, NativeTrigger,
        StringId, NativeTriggerState, NoDetail> {

    public static final NativeTriggerType TYPE = new NativeTriggerType();

    private NativeTriggerType() {
        super(NativeTrigger.class, StringId.class, NativeTriggerState.class, NoDetail.class);
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

    @Override
    public NativeTrigger.Builder componentBuilder() {
        return NativeTrigger.builder();
    }

    @Override
    public StringId.Builder idBuilder() {
        return StringId.builder();
    }

    @Override
    public NativeTriggerState.Builder stateBuilder() {
        return NativeTriggerState.builder();
    }

    @Override
    public NoDetail.Builder detailBuilder() {
        return NoDetail.builder();
    }

    public static NativeTriggerType newInstance() {
        return TYPE;
    }
}
