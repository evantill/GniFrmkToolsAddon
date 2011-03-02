package com.gni.frmk.tools.addon.data2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 09:32
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class ComponentState {

    public static enum EnableStatus {
        ENABLED, DISABLED
    }

    public ComponentState(EnableStatus enabled) {
        this.enabled = enabled;
    }

    /**
     * empty constructor for jaxb
     */
    private ComponentState() {
        this.enabled = null;
    }

    @XmlElement
    private final EnableStatus enabled;

    public EnableStatus getEnabled() {
        return enabled;
    }
}
