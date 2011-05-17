package com.gni.frmk.tools.addon.model;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:02
 *
 * @author: e03229
 */
public interface ModelResource {
    /**
     * used for creation Jaxb context path
     * @return the FQDN of this resource (could use ':' to separate multiple path).
     */
    String getContextPath();
}
