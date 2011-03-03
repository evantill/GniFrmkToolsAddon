package com.gni.frmk.tools.addon.data2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class EnableComponentState extends ComponentState {

    public static enum EnableStatus {
        ENABLED, DISABLED
    }

    @XmlElement
    private final EnableStatus enabled;

    public EnableComponentState(EnableStatus enabled) {
        this.enabled = enabled;
    }

    /**
     * empty constructor for jaxb
     */
    protected EnableComponentState() {
        enabled=null;
    }

    public EnableStatus getEnabled() {
        return enabled;
    }
}
