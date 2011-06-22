package com.gni.frmk.tools.addon.model;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:02
 *
 * @author: e03229
 */
@Singleton
public interface ModelResource {
    /**
     * used for creation Jaxb context path
     *
     * @return the list of packages containing model elements;
     */
    List<Package> getModelPackages();
}
