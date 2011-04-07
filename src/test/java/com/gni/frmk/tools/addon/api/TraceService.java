package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:47
 *
 * @author: e03229
 */
public class TraceService extends TestAbstractService {

    public TraceService() {
        super(new FullParsingStrategy());
    }

    @Override
    public void visit(TestComposant1 visitable) {
        System.out.println("TraceService.visit 1");
    }

    @Override
    public void visit(TestComposant2 visitable) {
        System.out.println("TraceService.visit 2");
    }

    @Override
    public <A extends Action<R>, R extends Result> R dispatch(A command) {
        System.out.println("TraceService.dispatch");
        return null;
    }

    public static void main(String[] args) {
        TestConfigurationService srv = new TraceService();
        TestConfiguration cnf = new TestConfiguration(new TestComposant1(), new TestComposant2());
        srv.execute(cnf);
    }


}
