package ev.frmk.tools.plateform.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 13/04/11
 * Time: 11:18
 *
 * @author: e03229
 */
public interface Consumer<P extends Producer> {
    void consume(P provider);
}
