package com.gni.frmk.tools.addon.data3.components.integrationServer;

import com.gni.frmk.tools.addon.data3.components.Component;
import com.gni.frmk.tools.addon.data3.components.ComponentState;
import com.gni.frmk.tools.addon.data3.components.ComponentType;
import com.gni.frmk.tools.addon.data3.visitors.ComponentVisitor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class AdapterConnection extends AdapterTypeComponent implements Component {

    private final EnableComponentState state;

    public AdapterConnection(ComponentType type, Id id, List<Information> informations, String packageName, String adapterType, EnableComponentState state) {
        super(type, id, informations, packageName, adapterType);
        this.state = state;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public ComponentState getState() {
        return state;
    }
}
