package com.gni.frmk.tools.addon.api.custom;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.Result;
import com.gni.frmk.tools.addon.api.custom.strategy.TConfigurationStrategy;
//import ev.frmk.tools.plateform.api.custom.visitor.ConfigurationVisitor.VisitorException;
import com.gni.frmk.tools.addon.api.custom.visitor.TComponentVisitor;
import com.gni.frmk.tools.addon.api.custom.visitor.TConfigurationVisited;
import com.gni.frmk.tools.addon.api.custom.visitor.TConfigurationVisitor;
//import com.gni.frmk.tools.addon.visitor.api.ComponentVisitorRaisingException.ComponentVisitorException;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:34
 *
 * @author: e03229
 */
public abstract class TAbstractService extends TConfigurationContext implements TComponentVisitor, TConfigurationService {

    private class TestConfigurationVisitorAdapter implements TConfigurationVisitor {
        @Override
        public void dispatchVisit(final TConfigurationVisited visitable) {
            TAbstractService.this.executeStrategy(new TConfigurationStrategy.Operation() {
                @Override
                public TComponentVisitor getVisitor() {
                    return TAbstractService.this;
                }

                @Override
                public TConfigurationVisited getVisited() {
                    return visitable;
                }
            });
        }
    }

    private final TestConfigurationVisitorAdapter cnfVisitorAdapter = new TestConfigurationVisitorAdapter();
    private final TConfigurationStrategy strategy;

    protected TAbstractService(TConfigurationStrategy strategy) {
        this.strategy = strategy;
    }

//    @Override
//    public void dispatchVisit(TComponentVisited visitable) throws ComponentVisitorException, VisitorException, ConfigurationVisitor.VisitorException {
//        visitable.accept(this);
//    }

    @Override
    public TConfigurationStrategy executeStrategy(TConfigurationStrategy.Operation target) {
        return strategy.execute(target);
    }

    @Override
    public void execute(TConfigurationVisited cnf) {
        cnfVisitorAdapter.dispatchVisit(cnf);
    }

    public abstract <A extends Action<R>, R extends Result> R dispatch(A command);
}
