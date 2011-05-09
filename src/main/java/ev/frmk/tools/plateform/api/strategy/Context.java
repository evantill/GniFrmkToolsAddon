package ev.frmk.tools.plateform.api.strategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:09
 *
 * @author: e03229
 */
public interface Context<R, T, S extends Strategy<R, T>> {
    R executeStrategy(T target);
}
