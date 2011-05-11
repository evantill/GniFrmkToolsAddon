package com.gni.frmk.tools.addon.model.component.detail;

import com.gni.frmk.tools.addon.model.BaseComponent.AbstractDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:30
 *
 * @author: e03229
 */
public class NoDetail extends AbstractDetail {

    private static final NoDetail singleton = new NoDetail();

    private NoDetail() {
    }

    public static final NoDetail newInstance() {
        return singleton;
    }
}
