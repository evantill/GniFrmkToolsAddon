package ev.frmk.tools.plateform.api.custom;

import ev.frmk.tools.plateform.api.custom.visitor.TComponentVisited;
import ev.frmk.tools.plateform.api.custom.visitor.TComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:41
 *
 * @author: e03229
 */
public class TComposant2 implements TComponentVisited {
    @Override
    public void accept(TComponentVisitor visitor) {
        System.out.println("Composant2.accept");
        visitor.visit(this);
    }
}
