package com.gni.frmk.tools.addon.visitor.essai.impl;

import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationService;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:47
 *
 * @author: e03229
 */
public class TraceService extends AbstractService {

    public TraceService() {
        super(new FullParsingStrategy());
    }

    @Override
    public void visit(Composant1 visitable) {
        System.out.println("TraceService.visit 1");
    }

    @Override
    public void visit(Composant2 visitable) {
        System.out.println("TraceService.visit 2");
    }

    public static void main(String[] args) {
        ConfigurationService srv = new TraceService();
        Configuration cnf = new Configuration(new Composant1(), new Composant2());
        srv.execute(cnf);
    }
}
