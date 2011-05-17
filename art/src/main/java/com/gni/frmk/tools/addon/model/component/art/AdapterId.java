package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractId;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:26
 *
 * @author: e03229
 */
public class AdapterId  extends AbstractId {

        private String name;
        private String adapterType;

    public AdapterId(String name, String adapterType) {
        this.name = name;
        this.adapterType = adapterType;
    }

    public AdapterId() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdapterType() {
        return adapterType;
    }

    public void setAdapterType(String adapterType) {
        this.adapterType = adapterType;
    }
}
