package com.gni.frmk.tools.addon.operation.action.component.art.connection;

import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:05
 *
 * @author: e03229
 */
public class GetAdapterConnection extends GetComponent<AdapterConnection, AdapterId> {
    public GetAdapterConnection(AdapterId id) {
        super(id);
    }
}
