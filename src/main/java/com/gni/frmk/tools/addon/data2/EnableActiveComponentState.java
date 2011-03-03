package com.gni.frmk.tools.addon.data2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 18:48
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class EnableActiveComponentState extends EnableComponentState {
    public static enum ActiveStatus {
        ACTIVE, SUSPENDED
    }

    @XmlElement
    private final ActiveStatus active;

    public EnableActiveComponentState(EnableStatus enabled, ActiveStatus active) {
        super(enabled);
        this.active = active;
    }

    /**
     * empty constructor for jaxb
     */
    protected EnableActiveComponentState() {
        active = null;
    }

    public ActiveStatus getActive() {
        return active;
    }
}
