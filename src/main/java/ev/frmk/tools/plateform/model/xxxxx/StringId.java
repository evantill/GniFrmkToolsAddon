package ev.frmk.tools.plateform.model.xxxxx;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 19:09
 *
 * @author: e03229
 */
public class StringId implements ComponentId{
    private final String id;

    public StringId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
