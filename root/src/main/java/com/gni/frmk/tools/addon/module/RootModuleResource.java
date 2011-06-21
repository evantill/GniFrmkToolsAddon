package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.module.ModelModule;
import com.google.inject.AbstractModule;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 17:14
 *
 * @author: e03229
 */
public class RootModuleResource extends AbstractModule {

    @Override
    protected void configure() {
        install(new ModelModule() {
            @Override
            protected void registerModelContextPackages() {
                registerModelContextPackage(NativeTrigger.class.getPackage());
            }
        });
    }
}
