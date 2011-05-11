package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:03
 *
 * @author: e03229
 */
public interface ConfigurationVisitor extends ComponentVisitor {

    void visitConfiguration(Configuration visited);

    void visitComponentConfiguration(ComponentConfiguration<?, ?> visited);

}
