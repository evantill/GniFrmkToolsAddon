package ev.frmk.tools.plateform.api.custom;

import ev.frmk.tools.plateform.api.custom.visitor.TConfigurationVisited;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:38
 *
 * @author: e03229
 */
public interface TConfigurationService {
    void execute(TConfigurationVisited cnf);
}
