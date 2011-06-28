package com.gni.frmk.tools.addon.api.visitor.component.root;

import com.gni.frmk.tools.addon.api.visitor.component.TypedComponentVisitor;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:12
 *
 * @author: e03229
 */
public interface RootComponentVisitor extends TypedComponentVisitor {

    void visitComponentNativeTrigger(NativeTrigger visited);

    void visitComponentPort(Port visited);

    void visitComponentScheduler(Scheduler visited);
}
