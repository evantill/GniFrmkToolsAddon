package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.EnableState;
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
public class AdapterConnectionType
        extends BaseComponentType<AdapterConnectionType, AdapterConnection,
        AdapterId, EnableState, PackageDetail> {

    public static final AdapterConnectionType TYPE = new AdapterConnectionType();

    private AdapterConnectionType() {
        super(AdapterConnection.class, AdapterId.class, EnableState.class, PackageDetail.class);
    }

    @Override
    public boolean isInput() {
        return false;
    }

    @Override
    public boolean isOutput() {
        return true;
    }

    @Override
    public AdapterConnection.Builder componentBuilder() {
        return AdapterConnection.builder();
    }

    @Override
    public AdapterId.Builder idBuilder() {
        return AdapterId.builder();
    }

    @Override
    public EnableState.Builder stateBuilder() {
        return EnableState.builder();
    }

    @Override
    public PackageDetail.Builder detailBuilder() {
        return PackageDetail.builder();
    }

    public static AdapterConnectionType newInstance() {
        return TYPE;
    }
}
