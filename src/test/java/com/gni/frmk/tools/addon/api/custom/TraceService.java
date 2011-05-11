package com.gni.frmk.tools.addon.api.custom;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:47
 *
 * @author: e03229
 */
public class TraceService /*extends TAbstractService*/ {

    public TraceService() {
//        super(new FullParsingStrategy());
    }

//    @Override
    public void visit(TComposant1 visitable) {
        System.out.println("TraceService.visit 1");
    }

//    @Override
    public void visit(TComposant2 visitable) {
        System.out.println("TraceService.visit 2");
    }

//    @Override
    public <A extends Action<R>, R extends Result> R dispatch(A command) {
        System.out.println("TraceService.dispatch");
        return null;
    }

    public static void main(String[] args) {
//        TConfigurationService srv = new TraceService();
//        TConfiguration cnf = new TConfiguration(new TComposant1(), new TComposant2());
//        srv.execute(cnf);
    }


}
