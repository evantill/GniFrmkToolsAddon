package com.gni.frmk.tools.addon.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 18:27
 *
 * @author: e03229
 */
public class FullParsingStrategy implements TConfigurationStrategy {

    @Override
    public TConfigurationStrategy execute(Operation o) {
        System.out.println("FullParsingStrategy.execute");
        TConfigurationVisited cnf = o.getVisited();
        o.getVisitor().dispatchVisit(cnf.getComposant1());
        o.getVisitor().dispatchVisit(cnf.getComposant2());
        return this;
    }
}
