package com.gni.frmk.tools.addon.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:57
 *
 * @author: e03229
 */
public class PartialParsingStrategy implements TConfigurationStrategy {


    @Override
    public TConfigurationStrategy execute(Operation o) {
        System.out.println("PartialParsingStrategy.execute");
        TConfigurationVisited cnf = o.getVisited();
        o.getVisitor().dispatchVisit(cnf.getComposant1());
        return this;
    }
}
