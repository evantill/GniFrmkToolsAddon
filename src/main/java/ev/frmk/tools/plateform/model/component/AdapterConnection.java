package ev.frmk.tools.plateform.model.component;

import ev.frmk.tools.plateform.model.component.AdapterConnection.Details;
import ev.frmk.tools.plateform.model.component.AdapterConnection.MutableAdapterConnection;
import ev.frmk.tools.plateform.model.component.AdapterConnection.States;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 18:00
 *
 * @author: e03229
 */
public class AdapterConnection
        extends Component<AdapterConnection, MutableAdapterConnection, StringId, Details, States> {

    public AdapterConnection(ReadableComponent<AdapterConnection, MutableAdapterConnection, StringId, Details, States> source) {
        super(source);
    }

    @Override
    public AdapterConnection toImmutable() {
        return this;
    }

    @Override
    public MutableAdapterConnection toMutable() {
        return new MutableAdapterConnection(this);
    }

    public static class Details implements ReadableComponent.Details {
        private final String alias;

        public Details(String alias) {
            this.alias = alias;
        }

        public String getAlias() {
            return alias;
        }
    }

    public static class States implements ReadableComponent.States {
        private final boolean enabled;

        public States(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }
    }

    @XmlRootElement(name = "AdapterConnection")
    public static class MutableAdapterConnection
            extends MutableComponent<AdapterConnection, MutableAdapterConnection, StringId, Details, States> {

        public MutableAdapterConnection() {
            setType(Type.ADAPTER_CONNECTION);
        }

        public MutableAdapterConnection(ReadableComponent<AdapterConnection, MutableAdapterConnection, StringId, AdapterConnection.Details, AdapterConnection.States> source) {
            super(source);
        }

        @Override
        public AdapterConnection toImmutable() {
            return new AdapterConnection(this);
        }

        @Override
        public MutableAdapterConnection toMutable() {
            return new MutableAdapterConnection(this);
        }
    }
}
