package com.gni.frmk.tools.addon.invoker.service.art;

import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 12:01
 *
 * @author: e03229
 */
public class ArtServiceUtils {

    public static IData prepareListComponentsInput(AdapterTypeInput input) {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 input.getAdapterType()}
        });
    }
}
