package ev.frmk.tools.plateform.api.custom.strategy;

import ev.frmk.tools.plateform.api.strategy.Strategy;
import ev.frmk.tools.plateform.api.custom.visitor.TComponentVisitor;
import ev.frmk.tools.plateform.api.custom.strategy.TConfigurationStrategy.Operation;
import ev.frmk.tools.plateform.api.custom.visitor.TConfigurationVisited;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:30
 *
 * @author: e03229
 */
public interface TConfigurationStrategy extends Strategy<TConfigurationStrategy, Operation> {

    public static interface Operation {
        TComponentVisitor getVisitor();

        TConfigurationVisited getVisited();
    }

    TConfigurationStrategy execute(Operation o);
}
