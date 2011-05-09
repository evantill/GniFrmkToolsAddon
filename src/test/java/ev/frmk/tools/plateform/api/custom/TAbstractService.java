package ev.frmk.tools.plateform.api.custom;

import ev.frmk.tools.plateform.api.action.Action;
import ev.frmk.tools.plateform.api.action.Result;
import ev.frmk.tools.plateform.api.custom.strategy.TConfigurationStrategy;
//import ev.frmk.tools.plateform.api.custom.visitor.ConfigurationVisitor.VisitorException;
import ev.frmk.tools.plateform.api.custom.visitor.ConfigurationVisitor;
import ev.frmk.tools.plateform.api.custom.visitor.TComponentVisitor;
import ev.frmk.tools.plateform.api.custom.visitor.TConfigurationVisited;
import ev.frmk.tools.plateform.api.custom.visitor.TConfigurationVisitor;
import ev.frmk.tools.plateform.api.custom.visitor.TComponentVisited;
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
