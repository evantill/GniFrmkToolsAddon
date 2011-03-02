package com.gni.frmk.tools.addon.data.trigger;

import com.gni.frmk.tools.addon.data.component.ComponentInfo;

import static com.google.common.base.Objects.firstNonNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 19:00
 * To change this template use File | Settings | File Templates.
 */
public class JmsAliasInfo<C extends JmsAliasInfo> extends ComponentInfo<C> {

    private static final String DESCRIPTION_KEY = "description";

    public JmsAliasInfo(String description) {
        setDescription(description);
    }

    public String getDescription() {
        return firstNonNull(getInfo(DESCRIPTION_KEY), "");
    }

    public void setDescription(String description) {
        addInfo(DESCRIPTION_KEY, description);
    }

}
