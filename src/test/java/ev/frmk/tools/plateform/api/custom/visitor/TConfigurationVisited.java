package ev.frmk.tools.plateform.api.custom.visitor;

import ev.frmk.tools.plateform.api.custom.TComposant1;
import ev.frmk.tools.plateform.api.custom.TComposant2;
import ev.frmk.tools.plateform.api.visitor.Visitable;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:25
 *
 * @author: e03229
 */
public interface TConfigurationVisited extends Visitable<TConfigurationVisitor, TConfigurationVisited> {

    TComposant1 getComposant1();
    TComposant2 getComposant2();

}
