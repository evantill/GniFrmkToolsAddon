package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationVisited;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:57
 *
 * @author: e03229
 */
public class PartialParsingStrategy implements ConfigurationProcessingStrategy{

    @Override
    public ConfigurationProcessingStrategy execute(Operation o) {
        System.out.println("PartialParsingStrategy.execute");
        ConfigurationVisited cnf = o.getVisited();
        o.getVisitor().dispatchVisit(cnf.getCompoqsant1());
        return this;
    }
}
