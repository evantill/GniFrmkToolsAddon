package thirdparty.jaxb.registry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */
public class ElementTwo extends ElementOne{
    @XmlElement
    private String value21;
    @XmlElement
    private String value22;

     @XmlElement
    private StatusActivable status;

    public ElementTwo(String value11, String value12, ElementType type, String name, String value21, String value22) {
        super(value11, value12, type, name);
        this.value21 = value21;
        this.value22 = value22;
    }

    public ElementTwo() {
    }

    public StatusActivable getStatus() {
        return status;
    }

    public void setStatus(StatusActivable status) {
        this.status = status;
    }

    public String getValue21() {
        return value21;
    }

    public void setValue21(String value21) {
        this.value21 = value21;
    }

    public String getValue22() {
        return value22;
    }

    public void setValue22(String value22) {
        this.value22 = value22;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ElementTwo");
        sb.append("{value21='").append(value21).append('\'');
        sb.append(", value22='").append(value22).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
