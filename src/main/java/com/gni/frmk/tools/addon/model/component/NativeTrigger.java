package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.BaseComponent;
import com.gni.frmk.tools.addon.model.NativeTriggerState;
import com.gni.frmk.tools.addon.model.NoDetail;
import com.gni.frmk.tools.addon.model.StringId;
import com.gni.frmk.tools.addon.model.TypedComponentVisitor;

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
