package com.gni.frmk.tools.addon.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:23
 *
 * @author: e03229
 */
//TODO remplacer adapter par implementation directe
public abstract class BaseModelResource implements ModelResource {

    protected abstract Package[] getContextPathPackages();

    @Override
    public List<Package> getModelPackages() {
        return Arrays.asList(getContextPathPackages());
    }
}
