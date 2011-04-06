package com.gni.frmk.tools.addon.visitor.essai.api.configuration;

import com.gni.frmk.tools.addon.visitor.essai.api.Visitable;
import com.gni.frmk.tools.addon.visitor.essai.api.component.ComponentVisitor;
import com.gni.frmk.tools.addon.visitor.essai.impl.Composant1;
import com.gni.frmk.tools.addon.visitor.essai.impl.Composant2;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:39
 *
 * @author: e03229
 */
public interface ConfigurationVisited extends Visitable<ConfigurationVisitor,ConfigurationVisited>{
    Composant1 getCompoqsant1();
    Composant2 getCompoqsant2();
}
