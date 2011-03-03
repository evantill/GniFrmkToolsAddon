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
@XmlRootElement
public class ElementRoot {
    @XmlElement
    ElementOne one;
    @XmlElement
    ElementTwo two;
    @XmlElement
    ElementThree three;

    public ElementOne getOne() {
        return one;
    }

    public void setOne(ElementOne one) {
        this.one = one;
    }

    public ElementTwo getTwo() {
        return two;
    }

    public void setTwo(ElementTwo two) {
        this.two = two;
    }

    public ElementThree getThree() {
        return three;
    }

    public void setThree(ElementThree three) {
        this.three = three;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ElementRoot");
        sb.append("{one=").append(one);
        sb.append(", two=").append(two);
        sb.append(", three=").append(three);
        sb.append('}');
        return sb.toString();
    }
}
