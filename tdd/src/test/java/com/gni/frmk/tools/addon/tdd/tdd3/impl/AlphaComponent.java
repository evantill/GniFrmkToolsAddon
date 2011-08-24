package com.gni.frmk.tools.addon.tdd.tdd3.impl;

import com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentContext;
import com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentStrategy;
import com.gni.frmk.tools.addon.tdd.tdd3.impl.AlphaComponent.Data;

import javax.validation.constraints.NotNull;

import static com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentState.CLOSED;
import static com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentState.OPENED;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:10
 *
 * @author: e03229
 */
public class AlphaComponent extends BaseComponent<AlphaComponentType, IntegerComponentId, Data> {

    public static class Data implements AlphaComponentData {
        @NotNull
        private Boolean enabled;
        @NotNull
        private String description;

        private Data() {}

        private Data(AlphaComponentData saved) {
            enabled = saved.isEnabled();
            description = saved.getDescription();
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    public AlphaComponent(IntegerComponentId id) {
        super(AlphaComponentType.singleton, id, new Data());
    }


    @Override
    public void open(ComponentContext ctx) {
            getData().enabled = true;
    }

    @Override
    public void close(ComponentContext ctx) {
        getData().enabled = false;
    }

    @Override
    public void refreshData(ComponentContext ctx) {

    }

    @Override
    protected ComponentState defineStateFromData(Data data) {
        return data.enabled ? OPENED : CLOSED;
    }

    @Override
    protected Data cloneData(Data data) {
        return new Data(data);
    }
}
