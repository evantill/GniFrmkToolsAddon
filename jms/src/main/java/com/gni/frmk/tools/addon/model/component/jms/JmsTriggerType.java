package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentType;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 16:19
 *
 * @author: e03229
 */
public class JmsTriggerType
        extends BaseComponentType<JmsTriggerType, JmsTrigger,
        StringId, ActivableState, PackageDetail> {

    public static final JmsTriggerType TYPE = new JmsTriggerType();

    private JmsTriggerType() {
        super(JmsTrigger.class, StringId.class, ActivableState.class, PackageDetail.class);
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
    public JmsTrigger.Builder componentBuilder() {
        return JmsTrigger.builder();
    }

    @Override
    public StringId.Builder idBuilder() {
        return StringId.builder();
    }

    @Override
    public ActivableState.Builder stateBuilder() {
        return ActivableState.builder();
    }

    @Override
    public PackageDetail.Builder detailBuilder() {
        return PackageDetail.builder();
    }

    public static JmsTriggerType newInstance() {
        return TYPE;
    }
}
