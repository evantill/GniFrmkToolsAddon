package ev.frmk.tools.plateform.api.strategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:09
 *
 * @author: e03229
 */
public interface Strategy<R,T> {

    R execute(T target);
}
