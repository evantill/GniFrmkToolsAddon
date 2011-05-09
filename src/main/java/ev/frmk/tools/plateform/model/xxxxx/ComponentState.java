package ev.frmk.tools.plateform.model.xxxxx;

import ev.frmk.tools.plateform.model.xxxxx.ComponentState.MutableComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/05/11
 * Time: 08:56
 *
 * @author: e03229
 */
public interface ComponentState<C extends ComponentState<C, M>, M extends MutableComponentState<C, M>>
        extends ImmutableObject2<C, M> {

    public static interface MutableComponentState<C extends ComponentState<C, M>, M extends MutableComponentState<C, M>>
            extends ComponentState<C, M>,
            MutableObject2<C, M> {

    }
}
