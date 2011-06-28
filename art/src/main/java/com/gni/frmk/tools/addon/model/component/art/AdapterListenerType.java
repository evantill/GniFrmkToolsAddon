package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ComponentTypeOrder;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
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
public class AdapterListenerType
        extends BaseComponentType<AdapterListenerType, AdapterListener,
        AdapterId, ActivableState, PackageDetail> {

    public static final AdapterListenerType TYPE = new AdapterListenerType();

    private AdapterListenerType() {
        super(AdapterListener.class, AdapterId.class, ActivableState.class, PackageDetail.class);
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
        return ComponentTypeOrder.SECOND;
    }

    @Override
    public AdapterListener.Builder componentBuilder() {
        return AdapterListener.builder();
    }

    @Override
    public AdapterId.Builder idBuilder() {
        return AdapterId.builder();
    }

    @Override
    public ActivableState.Builder stateBuilder() {
        return ActivableState.builder();
    }

    @Override
    public PackageDetail.Builder detailBuilder() {
        return PackageDetail.builder();
    }

    public static AdapterListenerType newInstance() {
        return TYPE;
    }
}
