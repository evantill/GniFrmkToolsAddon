package ev.frmk.tools.plateform.model.configuration;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 13:48
 *
 * @author: e03229
 */
public abstract class BaseConfiguration
        implements ReadableConfiguration {

    private String id;
    private String name;
    private String packageName;
    private Date creation;
    private Date modification;

    protected BaseConfiguration() {
    }

    protected BaseConfiguration(ReadableConfiguration source) {
        id = source.getId();
        name = source.getName();
        creation = new Date(source.getCreation().getTime());
        modification = new Date(source.getModification().getTime());
        packageName = source.getPackageName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public Date getCreation() {
        return new Date(creation.getTime());
    }

    public Date getModification() {
        return new Date(modification.getTime());
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    protected void setCreation(Date creation) {
        this.creation = new Date(creation.getTime());
    }

    protected void setModification(Date modification) {
        this.modification = new Date(modification.getTime());
    }

    @Override
    public final MutableConfiguration toMutable() {
        return new MutableConfiguration(this);
    }

}
