package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.ImmutableIntegrationServerPackage.MutableIntegrationServerPackage;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.EnableState;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 17:15
 *
 * @author: e03229
 */
public interface ReadableIntegrationServerPackage
        extends ReadablePackageAware<ImmutableIntegrationServerPackage, MutableIntegrationServerPackage, StringId, EnableState> {

    interface WritableIntegrationServerPackage
            extends ReadableIntegrationServerPackage,
            WritablePackageAware<ImmutableIntegrationServerPackage, MutableIntegrationServerPackage, StringId, EnableState> {

    }
}
