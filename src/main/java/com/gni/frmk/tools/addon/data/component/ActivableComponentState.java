package com.gni.frmk.tools.addon.data.component;

import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public class ActivableComponentState extends ComponentState<ActivableComponentState> {
    public static enum ActiveStatus {
        ACTIVE, SUSPENDED
    }

    //TODO mutable : doit etre retire des equals hashcode et compare ???
    private ActiveStatus activeStatus;

    public ActivableComponentState(EnableStatus enableStatus, ActiveStatus activeStatus) {
        super(enableStatus);
        this.activeStatus = activeStatus;
    }

    public ActiveStatus getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(ActiveStatus activeStatus) {
        this.activeStatus = activeStatus;
    }

    public int compareActiveStatus(ActivableComponentState other) {
        return ComparisonChain.start()
                .compare(getActiveStatus(), other.getActiveStatus())
                .result();
    }

    @Override
    public int compareTo(ActivableComponentState other) {
        return ComparisonChain.start()
                .compare(getEnableStatus(), other.getEnableStatus())
                .compare(getActiveStatus(), other.getActiveStatus())
                .result();
    }
}
