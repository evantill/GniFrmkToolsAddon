package com.gni.frmk.tools.addon.model.module;

import com.gni.frmk.tools.addon.model.module.ModuleResource.ModelContext;
import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import java.util.List;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 19:17
 *
 * @author: e03229
 */
public abstract class ModelModule extends AbstractModule {

    private static final Package[] EMPTY_PACKAGES = new Package[0];

    private List<Package> packages = Lists.newArrayList();

    protected abstract void registerModelContextPackages();

    protected void registerModelContextPackage(Package p) {
        packages.add(p);
    }

    @Override
    protected void configure() {
        registerModelContextPackages();
        final Package[] pkgs = packages.toArray(EMPTY_PACKAGES);
        Multibinder<ModelContext> modelContextBinder = newSetBinder(binder(), ModelContext.class);
        modelContextBinder.addBinding().toInstance(new ModelContext() {
            @Override
            public Package[] getModelPackages() {
                return pkgs;
            }
        });
    }

}
