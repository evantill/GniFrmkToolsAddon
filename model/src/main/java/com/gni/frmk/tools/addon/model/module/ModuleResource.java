package com.gni.frmk.tools.addon.model.module;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 15:05
 *
 * @author: e03229
 */
public interface ModuleResource {
    static interface ModelContext {
        Package[] getModelPackages();
    }
}
