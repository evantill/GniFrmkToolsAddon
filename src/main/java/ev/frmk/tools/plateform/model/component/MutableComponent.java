package ev.frmk.tools.plateform.model.component;

import ev.frmk.tools.plateform.api.immutable.ImmutableObject;
import ev.frmk.tools.plateform.api.immutable.MutableObject;
import ev.frmk.tools.plateform.model.component.ReadableComponent.Details;
import ev.frmk.tools.plateform.model.component.ReadableComponent.Id;
import ev.frmk.tools.plateform.model.component.ReadableComponent.States;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 17:20
 *
 * @author: e03229
 */
public abstract class MutableComponent<I extends ImmutableObject<I, M>, M extends MutableObject<I, M>, ID extends Id, D extends Details, S extends States>
        extends BaseComponent<I, M, ID, D, S>
        implements ReadWritableComponent<I, M, ID, D, S>, MutableObject<I, M> {

    public MutableComponent() {
    }

    public MutableComponent(ReadableComponent<I, M, ID, D, S> source) {
        super(source);
    }

    @Override
    public void setId(ID id) {
        super.setId(id);
    }

    @Override
    public void setStates(S states) {
        super.setStates(states);
    }

    @Override
    public void setDetails(D details) {
        super.setDetails(details);
    }
}
