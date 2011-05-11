package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.detail.NoDetail;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.visitor.TypedComponentVisitor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
@XmlRootElement
public class NativeTrigger
        extends BaseComponent<StringId, NativeTriggerState, NoDetail> {

    @Override
    public void accept(TypedComponentVisitor visitor) {
        visitor.visitNativeTriger(this);
    }
}
