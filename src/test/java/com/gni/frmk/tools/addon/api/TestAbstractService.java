package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:34
 *
 * @author: e03229
 */
public abstract class TestAbstractService extends TestConfigurationContext implements TestComponentVisitor, TestConfigurationService {

    private class TestConfigurationVisitorAdapter implements TestConfigurationVisitor {
        @Override
        public void dispatchVisit(final TestConfigurationVisited visitable) {
            TestAbstractService.this.executeStrategy(new com.gni.frmk.tools.addon.api.TestConfigurationStrategy.Operation() {
                @Override
                public TestComponentVisitor getVisitor() {
                    return TestAbstractService.this;
                }

                @Override
                public TestConfigurationVisited getVisited() {
                    return visitable;
                }
            });
        }
    }

    private final TestConfigurationVisitorAdapter cnfVisitorAdapter = new TestConfigurationVisitorAdapter();
    private final TestConfigurationStrategy strategy;

    protected TestAbstractService(TestConfigurationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void dispatchVisit(TestComponentVisited visitable) {
        visitable.accept(this);
    }

    @Override
    public TestConfigurationStrategy executeStrategy(com.gni.frmk.tools.addon.api.TestConfigurationStrategy.Operation target) {
        return strategy.execute(target);
    }

    @Override
    public void execute(TestConfigurationVisited cnf) {
        cnfVisitorAdapter.dispatchVisit(cnf);
    }

    public abstract <A extends Action<R>, R extends Result> R dispatch(A command);
}
