package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
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
public class PortType
        extends BaseComponentType<PortType, Port, PackageAndStringId, ActivableState, PortDetail> {

    public static final PortType TYPE = new PortType();

    private PortType() {
        super(Port.class, PackageAndStringId.class, ActivableState.class, PortDetail.class);
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
    public Port.Builder componentBuilder() {
        return Port.builder();
    }

    @Override
    public PackageAndStringId.Builder idBuilder() {
        return PackageAndStringId.builder();
    }

    @Override
    public ActivableState.Builder stateBuilder() {
        return ActivableState.builder();
    }

    @Override
    public PortDetail.Builder detailBuilder() {
        return PortDetail.builder();
    }

    public static PortType newInstance() {
        return TYPE;
    }
}
