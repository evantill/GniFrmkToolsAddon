package ev.frmk.tools.plateform.api.immutable;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 15:29
 *
 * @author: e03229
 */
public interface ReadWritableObject<I extends ImmutableObject<I, M>, M extends MutableObject<I, M>>
        extends ReadableObject<I, M> {
}
