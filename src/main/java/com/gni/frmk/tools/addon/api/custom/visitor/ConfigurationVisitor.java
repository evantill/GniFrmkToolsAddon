package com.gni.frmk.tools.addon.api.custom.visitor;

import com.gni.frmk.tools.addon.api.visitor.Visitor;
import com.gni.frmk.tools.addon.model.*;
import com.gni.frmk.tools.addon.model.Configuration;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:39
 *
 * @author: e03229
 */
public interface ConfigurationVisitor
        extends Visitor<ConfigurationVisitor, ConfigurationVisited> {

    void visitComponent(AdapterConnection visited);

    void visitComponent(AdapterListener visited);

    void visitComponent(AdapterNotification visited);

    void visitComponent(Port visited);

    void visitComponent(Scheduler visited);

    void visitComponent(NativeTrigger visited);

    void visitComponent(JmsTrigger visited);

    void visitComponent(JmsAlias visited);

    void visitConfiguration(Configuration configuration);

    void visitComponentConfiguration(ComponentConfiguration visited);
}
