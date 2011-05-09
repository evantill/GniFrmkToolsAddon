package ev.frmk.tools.plateform.model.component;

import ev.frmk.tools.plateform.api.immutable.ImmutableObject;
import ev.frmk.tools.plateform.api.immutable.MutableObject;
import ev.frmk.tools.plateform.api.immutable.ReadableObject;
import ev.frmk.tools.plateform.model.component.ReadableComponent.Details;
import ev.frmk.tools.plateform.model.component.ReadableComponent.Id;
import ev.frmk.tools.plateform.model.component.ReadableComponent.States;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 17:15
 *
 * @author: e03229
 */
public interface ReadableComponent<I extends ImmutableObject<I,M>,M extends MutableObject<I,M>, ID extends Id, D extends Details, S extends States>
        extends ReadableObject<I,M> {

    @XmlElement
    ID getId();

    @XmlElement
    Type getType();

    @XmlElement
    D getDetails();

    @XmlElement
    S getStates();

    public static enum Type {
        ADAPTER_CONNECTION
    }

    public static interface Id {

    }

    public static interface Details {

    }

    public static interface States {

    }


}
