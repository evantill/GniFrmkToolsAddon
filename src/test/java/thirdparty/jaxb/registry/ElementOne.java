package thirdparty.jaxb.registry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */

public class ElementOne implements Element{

    @XmlElement
    private String value11;

    @XmlElement
    private String value12;

    @XmlElement
    private ElementType type;

    @XmlElement
    private String name;

    @XmlElement
    private StatusEnable status;

    public ElementOne(String value11, String value12, ElementType type, String name) {
        this.value11 = value11;
        this.value12 = value12;
        this.type = type;
        this.name = name;
    }

    public ElementOne() {
    }

    public StatusEnable getStatus() {
        return status;
    }

    public void setStatus(StatusEnable status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public String getValue11() {
        return value11;
    }

    private void setValue11(String value11) {
        this.value11 = value11;
    }

    public String getValue12() {
        return value12;
    }

    private void setValue12(String value12) {
        this.value12 = value12;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ElementOne");
        sb.append("{value11='").append(value11).append('\'');
        sb.append(", value12='").append(value12).append('\'');
        sb.append(", type=").append(type);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
