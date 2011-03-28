package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.configuration.components.TemporaryActivableState.TemporaryStatus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 16:32
 *
 * @author: e03229
 */
@XmlRootElement
public class NativeTriggerState extends AbstractComponentState implements ComponentState {

    @XmlElement
    private final EnableState enabled;
    @XmlElement
    private final TemporaryActivableState retrievalState;
    @XmlElement
    private final TemporaryActivableState processingState;

    public NativeTriggerState(Builder builder) {
        this.enabled = builder.enabled;
        this.retrievalState = builder.retrievalState;
        this.processingState = builder.processingState;
    }

    private NativeTriggerState(){
        enabled=null;
        retrievalState=null;
        processingState=null;
    }

    public EnableState getEnabled() {
        return enabled;
    }

    public TemporaryActivableState getRetrievalState() {
        return retrievalState;
    }

    public TemporaryActivableState getProcessingState() {
        return processingState;
    }

    @Override
    public ComponentStateStatus getComponentStatus() {
        return enabled.getComponentStatus()
                      .composeWith(retrievalState.getComponentStatus())
                      .composeWith(processingState.getComponentStatus());
    }

    @Override
    public ComponentStateType getType() {
        return ComponentStateType.NATIVE_TRIGGER_STATE;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private EnableState enabled;
        private TemporaryActivableState retrievalState;
        private TemporaryActivableState processingState;

        public Builder defineEnable(com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus status) {
            enabled = new EnableState(status);
            return this;
        }

        public Builder defineRetrieval(TemporaryStatus temporary, ActivableStatus status) {
            retrievalState = new TemporaryActivableState(temporary, status);
            return this;
        }

        public Builder defineRetrieval(TemporaryActivableState state) {
            retrievalState = state;
            return this;
        }

        public Builder defineProcessing(TemporaryStatus temporary, ActivableStatus status) {
            processingState = new TemporaryActivableState(temporary, status);
            return this;
        }

        public Builder defineProcessing(TemporaryActivableState state) {
            processingState = state;
            return this;
        }

        public NativeTriggerState build() {
            return new NativeTriggerState(this);
        }
    }
}
