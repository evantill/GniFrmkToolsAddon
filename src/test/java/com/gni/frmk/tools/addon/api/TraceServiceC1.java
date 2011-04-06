package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationService;
import com.gni.frmk.tools.addon.service.configuration.AbstractService;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:56
 *
 * @author: e03229
 */
public class TraceServiceC1 extends AbstractService {

    public TraceServiceC1() {
        super(new PartialParsingStrategy());
    }

    @Override
    public void visit(Composant1 visitable) {
        System.out.println("TraceServiceC1.visit 1");
    }

    @Override
    public void visit(Composant2 visitable) {
        System.out.println("TraceServiceC1.visit 2");
    }


    public static void main(String[] args) {
        ConfigurationService srv = new TraceServiceC1();
        Configuration cnf = new Configuration(new Composant1(), new Composant2());
        srv.execute(cnf);
    }
}
