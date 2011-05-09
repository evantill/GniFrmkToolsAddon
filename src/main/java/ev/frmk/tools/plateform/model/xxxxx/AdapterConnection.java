package ev.frmk.tools.plateform.model.xxxxx;

import ev.frmk.tools.plateform.model.xxxxx.AdapterConnection.MutableAdapterConnection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 19:09
 *
 * @author: e03229
 */
public class AdapterConnection extends BaseComponent<AdapterConnection, MutableAdapterConnection, StringId> {


    public AdapterConnection(Component<AdapterConnection, MutableAdapterConnection, StringId> source) {
        super(source);
    }

    @Override
    public AdapterConnection asImmutable() {
        return this;
    }

    @Override
    public MutableAdapterConnection asMutable() {
        return new MutableAdapterConnection(this);
    }

    @XmlRootElement(name = "adapterConnection")
    public static class MutableAdapterConnection extends MutableBaseComponent<AdapterConnection, MutableAdapterConnection, StringId> {

        public MutableAdapterConnection() {
        }

        public MutableAdapterConnection(Component<AdapterConnection, MutableAdapterConnection, StringId> source) {
            super(source);
        }

        @Override
        @XmlElement(name = "id")
        public StringId getId() {
            return super.getId();
        }

        @Override
        public AdapterConnection asImmutable() {
            return new AdapterConnection(this);
        }

        @Override
        public MutableAdapterConnection asMutable() {
            return new MutableAdapterConnection(this);
        }
    }
}
