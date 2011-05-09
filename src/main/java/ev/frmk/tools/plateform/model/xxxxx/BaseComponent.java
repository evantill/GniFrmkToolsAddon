package ev.frmk.tools.plateform.model.xxxxx;

import ev.frmk.tools.plateform.model.xxxxx.Component.MutableComponent;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 19:08
 *
 * @author: e03229
 */
public abstract class BaseComponent<C extends BaseComponent<C, M, I>, M extends MutableComponent<C, M, I>, I extends ComponentId>
        implements Component<C, M, I> {
    private final I id;

    protected BaseComponent(Component<C, M, I> source) {
        id = source.getId();
    }

    public I getId() {
        return id;
    }

    public abstract static class MutableBaseComponent<C extends BaseComponent<C, M, I>, M extends MutableComponent<C, M, I>, I extends ComponentId>
            implements MutableComponent<C, M, I> {
        private I id;

        protected MutableBaseComponent() {

        }

        protected MutableBaseComponent(Component<C, M, I> source) {
            setId(source.getId());
        }

        @NotNull
        @Valid
        public I getId() {
            return id;
        }

        public void setId(I id) {
            this.id = id;
        }
    }

}