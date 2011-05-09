package ev.frmk.tools.plateform.model.configuration;

import ev.frmk.tools.plateform.api.immutable.MutableObject;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 13:35
 *
 * @author: e03229
 */
public class MutableConfiguration
        extends BaseConfiguration
        implements MutableObject<Configuration, MutableConfiguration>,
        ReadWritableConfiguration {

    public MutableConfiguration() {
    }

    public MutableConfiguration(ReadableConfiguration source) {
        super(source);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setPackageName(String packageName) {
        super.setPackageName(packageName);
    }

    @Override
    public void setCreation(Date creation) {
        super.setCreation(creation);
    }

    @Override
    public void setModification(Date modification) {
        super.setModification(modification);
    }

    @Override
    public Configuration toImmutable() {
        return new Configuration(this);
    }
}
