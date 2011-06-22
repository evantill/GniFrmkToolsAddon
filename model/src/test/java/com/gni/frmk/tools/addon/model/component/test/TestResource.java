package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.ModelResource;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:16
 *
 * @author: e03229
 */
public class TestResource implements ModelResource {

    @Override
    public List<Package> getModelPackages() {
        return Arrays.asList(getClass().getPackage());
    }
}
