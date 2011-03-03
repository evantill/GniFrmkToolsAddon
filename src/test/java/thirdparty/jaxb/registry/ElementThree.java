package thirdparty.jaxb.registry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
public class ElementThree extends ElementTwo {

    @XmlElement
    private String value31;

    public ElementThree(String value11, String value12, ElementType type, String name, String value21, String value22, String value31) {
        super(value11, value12, type, name, value21, value22);
        this.value31 = value31;
    }

    public ElementThree() {
    }

    public String getValue31() {
        return value31;
    }

    private void setValue31(String value31) {
        this.value31 = value31;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ElementThree");
        sb.append("{value31='").append(value31).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
