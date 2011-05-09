package ev.frmk.tools.plateform.model.xxxxx;

import ev.frmk.tools.plateform.model.xxxxx.Component.MutableComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 18:54
 *
 * @author: e03229
 */
public interface Component<C extends Component<C, M, I>, M extends MutableComponent<C, M, I>, I extends ComponentId>
        extends ImmutableObject2<C, M> {
    I getId();

    //ComponentState

    public static interface MutableComponent<C extends Component<C, M, I>, M extends MutableComponent<C, M, I>, I extends ComponentId>
            extends Component<C, M, I>,
            MutableObject2<C, M> {
        void setId(I id);
    }
}
