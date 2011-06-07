package com.gni.frmk.tools.addon.oldies;

import com.gni.frmk.tools.addon.ComponentRepository;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 13:56
 *
 * @author: e03229
 */
public class AdapterConnectionRepository
        implements ComponentRepository<AdapterConnection, AdapterId, EnableState, PackageDetail> {


    @Override
    public ComponentType getType() {
        return ComponentType.ADAPTER_CONNECTION;
    }

    @Override
    public ListId<AdapterId> getListIdAction() {
        return new ListId<AdapterId>();
    }

    @Override
    public GetComponentDetail<PackageDetail, AdapterId> getDetailAction(AdapterId id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StateAction<AdapterId, EnableState> getStateAction(AdapterId id) {
        return new StateAction<AdapterId, EnableState>(id);
    }
}