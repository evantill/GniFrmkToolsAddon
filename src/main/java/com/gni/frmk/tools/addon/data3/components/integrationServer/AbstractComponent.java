package com.gni.frmk.tools.addon.data3.components.integrationServer;

import com.gni.frmk.tools.addon.data3.components.Component;
import com.gni.frmk.tools.addon.data3.components.ComponentId;
import com.gni.frmk.tools.addon.data3.components.ComponentInformation;
import com.gni.frmk.tools.addon.data3.components.ComponentType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractComponent implements Component {

    private final ComponentType type;
    private final Id id;
    private final List<Information> informations;

    protected AbstractComponent(ComponentType type, Id id, List<Information> informations) {
        this.type = type;
        this.id = id;
        this.informations = informations;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public ComponentId getId() {
        return id;
    }

    @Override
    public List<Information> getInformations() {
        return informations;
    }

    public static class Id implements ComponentId {
        private final String value;

        public Id(String value) {this.value = value;}

        public String getValue() {
            return value;
        }

        @Override
        public String asString() {
            return value;
        }
    }

    public static class Information implements ComponentInformation {
        private final String key;
        private final String value;

        public Information(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
