package ev.frmk.tools.plateform.model.component;


import ev.frmk.tools.plateform.api.immutable.ImmutableObject;
import ev.frmk.tools.plateform.api.immutable.MutableObject;
import ev.frmk.tools.plateform.model.component.ReadableComponent.Details;
import ev.frmk.tools.plateform.model.component.ReadableComponent.Id;
import ev.frmk.tools.plateform.model.component.ReadableComponent.States;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 17:14
 *
 * @author: e03229
 */
public abstract class BaseComponent<I extends ImmutableObject<I, M>, M extends MutableObject<I, M>, ID extends Id, D extends Details, S extends States>
        implements ReadableComponent<I, M, ID,D,S> {

    private Type type;
    private ID id;
    private D details;
    private S states;

    protected BaseComponent() {

    }

    protected BaseComponent(ReadableComponent<I, M, ID,D,S> source) {
        type = source.getType();
        setId(source.getId());
    }

    @Override
    public Type getType() {
        return type;
    }

    protected void setType(Type type) {
        this.type = type;
    }

    @Override
    public ID getId() {
        return id;
    }

    protected void setId(ID id) {
        this.id = id;
    }

    public D getDetails() {
        return details;
    }

    protected void setDetails(D details) {
        this.details = details;
    }

    public S getStates() {
        return states;
    }

    protected void setStates(S states) {
        this.states = states;
    }
}
