package ev.frmk.tools.plateform.model.component;

import ev.frmk.tools.plateform.model.component.ReadableComponent.Id;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 17:46
 *
 * @author: e03229
 */
public class StringId implements Id {
    private final String id;

    public StringId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
