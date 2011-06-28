package com.gni.frmk.tools.addon.api.visitor.component.art;

import com.gni.frmk.tools.addon.api.visitor.component.TypedComponentVisitor;
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
public interface ArtComponentVisitor extends TypedComponentVisitor {

    void visitComponentAdapterConnection(AdapterConnection visited);

    void visitComponentAdapterListener(AdapterListener visited);

    void visitComponentAdapterNotification(AdapterNotification visited);
}
