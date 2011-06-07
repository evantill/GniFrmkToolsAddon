package com.gni.frmk.tools.addon.visitor.api.jms;

import com.gni.frmk.tools.addon.visitor.api.ComponentVisitor;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:12
 *
 * @author: e03229
 */
public interface JmsComponentVisitor extends ComponentVisitor {

    void visitComponentJmsAlias(JmsAlias visited);

    void visitComponentJmsTrigger(JmsTrigger visited);
}
