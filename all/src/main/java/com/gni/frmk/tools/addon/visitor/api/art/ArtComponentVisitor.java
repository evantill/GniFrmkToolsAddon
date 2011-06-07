package com.gni.frmk.tools.addon.visitor.api.art;

import com.gni.frmk.tools.addon.visitor.api.ComponentVisitor;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:12
 *
 * @author: e03229
 */
public interface ArtComponentVisitor extends ComponentVisitor {

    void visitComponentAdapterConnection(AdapterConnection visited);

    void visitComponentAdapterListener(AdapterListener visited);

    void visitComponentAdapterNotification(AdapterNotification visited);
}
