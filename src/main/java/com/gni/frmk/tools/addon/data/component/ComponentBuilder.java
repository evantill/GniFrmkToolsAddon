package com.gni.frmk.tools.addon.data.component;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class ComponentBuilder<C extends Component<F, S>, F extends ComponentInfo, S extends ComponentState>
    extends XmlAdapter<C, ComponentBuilder<C, F, S>>{

    private ComponentId id;
    private ComponentType type;
    private S state;
    private F info;

    public ComponentBuilder<C, F, S> defineId(String key) {
        id = new ComponentId(checkNotNull(key));
        return this;
    }

    public ComponentBuilder<C, F, S> defineType(ComponentType type) {
        this.type = checkNotNull(type);
        return this;
    }

    public ComponentBuilder<C, F, S> defineState(S state) {
        this.state = checkNotNull(state);
        return this;
    }

    public ComponentBuilder<C, F, S> defineInfo(F info) {
        this.info = checkNotNull(info);
        return this;
    }

    public ComponentBuilder<C, F, S> define(C component) {
        id = component.getId();
        type = component.getType();
        state = component.getState();
        info = component.getInfos();
        return this;
    }

    public abstract C build();

    @Override
    public ComponentBuilder<C, F, S> unmarshal(C v) throws Exception {
        return define(v);
    }

    @Override
    public C marshal(ComponentBuilder<C, F, S> v) throws Exception {
        return v.build();
    }

    public ComponentId buildId() {
        return checkNotNull(id);
    }

    public S buildState() {
        return checkNotNull(state);
    }

    public F buildInfos() {
        return checkNotNull(info);
    }

    public ComponentType buildType() {
        return checkNotNull(type);
    }
}
