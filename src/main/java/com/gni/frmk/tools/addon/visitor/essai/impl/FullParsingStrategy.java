package com.gni.frmk.tools.addon.visitor.essai.impl;

import com.gni.frmk.tools.addon.visitor.essai.api.component.ComponentVisited;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationVisited;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 18:27
 *
 * @author: e03229
 */
public class FullParsingStrategy implements ConfigurationProcessingStrategy {

    @Override
    public ConfigurationProcessingStrategy execute(Operation o) {
        System.out.println("FullParsingStrategy.execute");
        ConfigurationVisited cnf = o.getVisited();
        o.getVisitor().dispatchVisit(cnf.getCompoqsant1());
        o.getVisitor().dispatchVisit(cnf.getCompoqsant2());
        return this;
    }
}
