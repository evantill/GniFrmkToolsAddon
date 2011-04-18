package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.Result;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor.VisitorException;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.api.strategy.TConfigurationStrategy;
import com.gni.frmk.tools.addon.api.visitor.TComponentVisited;
import com.gni.frmk.tools.addon.api.visitor.TComponentVisitor;
import com.gni.frmk.tools.addon.api.visitor.TConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.TConfigurationVisitor;
import com.gni.frmk.tools.addon.visitor.api.ComponentVisitorRaisingException.ComponentVisitorException;

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

    @Override
    public void dispatchVisit(TComponentVisited visitable) throws ComponentVisitorException, VisitorException, ConfigurationVisitor.VisitorException {
        visitable.accept(this);
    }

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
