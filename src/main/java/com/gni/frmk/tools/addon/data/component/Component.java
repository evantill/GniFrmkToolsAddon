package com.gni.frmk.tools.addon.data.component;

import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 04/11/10
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
@XmlType
public abstract class Component<F extends ComponentInfo, S extends ComponentState> implements Comparable<Component<F, S>> {

    @XmlElement(required = true)
    private final ComponentId id;

    @XmlElement(required = true)
    private final ComponentType type;

    @XmlElement(required = true)
    private final S state;

    @XmlElement(required = true, name = "toto")
    private final F infos;

    protected Component(ComponentType type, ComponentId id, S state, F infos) {
        this.type = type;
        this.id = id;
        this.state = state;
        this.infos = infos;
    }

    public Component(ComponentBuilder<?, F, S> builder) {
        type = builder.buildType();
        id = builder.buildId();
        state = builder.buildState();
        infos = builder.buildInfos();
    }

    /**
     * Empty construtor : only used by jaxb
     */
    protected Component(){
        id=null;
        type=null;
        state=null;
        infos =null;
    }

    public ComponentId getId() {
        return id;
    }

    public ComponentType getType() {
        return type;
    }

    public S getState() {
        return state;
    }

    public F getInfos() {
        return infos;
    }

    public int compareTo(Component<F, S> that) {
        return ComparisonChain.start()
                .compare(getType(), that.getType())
                .compare(getId(), that.getId())
                .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component that = (Component) o;

        return Objects.equal(getType(), that.getType())
                && Objects.equal(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getType(), getId());
    }

    public abstract void accept(ConfigurationVisitor visitor);

    public abstract <T extends Component<F, S>> int compareStatusTo(T that);
}
