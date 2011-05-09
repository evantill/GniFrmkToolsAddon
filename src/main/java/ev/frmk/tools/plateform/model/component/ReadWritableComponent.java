package ev.frmk.tools.plateform.model.component;

import ev.frmk.tools.plateform.api.immutable.ImmutableObject;
import ev.frmk.tools.plateform.api.immutable.MutableObject;
import ev.frmk.tools.plateform.model.component.ReadableComponent.Details;
import ev.frmk.tools.plateform.model.component.ReadableComponent.Id;
import ev.frmk.tools.plateform.model.component.ReadableComponent.States;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 16:46
 *
 * @author: e03229
 */
public interface ReadWritableComponent<I extends ImmutableObject<I, M>, M extends MutableObject<I, M>, ID extends Id, D extends Details, S extends States>
        extends ReadableComponent<I, M, ID,D,S> {

    void setId(ID id);
    void setDetails(D details);
    void setStates(S states);

}
