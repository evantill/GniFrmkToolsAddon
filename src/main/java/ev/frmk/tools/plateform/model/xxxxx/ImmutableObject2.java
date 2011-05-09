package ev.frmk.tools.plateform.model.xxxxx;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 19:14
 *
 * @author: e03229
 */
public interface ImmutableObject2<I, M> {

    I asImmutable();
    M asMutable();

    public static interface MutableObject2<I, M> extends ImmutableObject2<I, M> {

    }

}
