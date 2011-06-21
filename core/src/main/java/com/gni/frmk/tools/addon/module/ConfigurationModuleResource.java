package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.module.ModelModule;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.google.inject.AbstractModule;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 18:08
 *
 * @author: e03229
 */
public class ConfigurationModuleResource extends AbstractModule{

    @Override
    protected void configure() {
        install(new ModelModule() {
            @Override
            protected void registerModelContextPackages() {
                registerModelContextPackage(Configuration.class.getPackage());
            }
        });
    }
}
