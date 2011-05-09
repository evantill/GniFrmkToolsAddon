package ev.frmk.tools.plateform.api.custom.visitor;

import ev.frmk.tools.plateform.api.custom.TComposant1;
import ev.frmk.tools.plateform.api.custom.TComposant2;
import ev.frmk.tools.plateform.api.visitor.Visitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:24
 *
 * @author: e03229
 */
public interface TComponentVisitor extends Visitor<TComponentVisitor, TComponentVisited> {

    void visit(TComposant1 visited);

    void visit(TComposant2 visited);
}
