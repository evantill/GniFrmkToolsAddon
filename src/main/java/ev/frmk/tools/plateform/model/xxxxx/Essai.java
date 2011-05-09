package ev.frmk.tools.plateform.model.xxxxx;

import ev.frmk.tools.plateform.model.xxxxx.AdapterConnection.MutableAdapterConnection;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/05/11
 * Time: 08:53
 *
 * @author: e03229
 */
public class Essai {

    public static void main(String[] args) {
        MutableAdapterConnection cnx = new MutableAdapterConnection();
        cnx.setId(new StringId("eesai"));
        AdapterConnection cnx2 = cnx.asImmutable();

    }
}
