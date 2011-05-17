package com.gni.frmk.tools.addon.model.component;

/**
* Created by IntelliJ IDEA.
* Date: 17/05/11
* Time: 14:38
*
* @author: e03229
*/
public enum ActivableStatus {
    UNKNOWN(false),
    ACTIVE(true),
    INACTIVE(false);

    private final boolean active;

    ActivableStatus(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isNotActive() {
        return !active;
    }

    /**
     * we could not use switch as it generate an inner "$1" class causing problems in jaxb
     * @param status to invert
     * @return  the negation of this status
     */
    public static ActivableStatus invert(ActivableStatus status) {
        if(status==ACTIVE){
            return INACTIVE ;
        }else if(status==INACTIVE){
            return ACTIVE;
        }else if(status==UNKNOWN){
            return UNKNOWN;
        }else{
            return UNKNOWN;
        }
    }

    public static boolean isActivation(ActivableStatus from,ActivableStatus to) {
        return from==INACTIVE && to==ACTIVE;
    }

    public static boolean isDesactivation(ActivableStatus from,ActivableStatus to) {
        return from==ACTIVE && to==INACTIVE;
    }

    public static ActivableStatus fromStateString(String stateValue, String activeString, String inactiveString) {
        if (activeString.equals(stateValue)) {
            return ACTIVE;
        }
        if (inactiveString.equals(stateValue)) {
            return INACTIVE;
        } else {
            return UNKNOWN;
        }
    }

    public static ActivableStatus fromBooleanString(String active) {
        return fromBoolean(Boolean.parseBoolean(active));
    }

    public static ActivableStatus fromBoolean(boolean active) {
        return active ? ACTIVE : INACTIVE;
    }
}
