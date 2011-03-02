package thirdparty.jaxb.emptyconstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class RootElement {

    @XmlElement
    private ElementOne one;

    public ElementOne getOne() {
        return one;
    }

    public void setOne(ElementOne one) {
        this.one = one;
    }
}
