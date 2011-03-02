package thirdparty.jaxb.emptyconstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */

public class ElementOne {

    @XmlElement
    private final ElementTwo two;

    public ElementOne(ElementOneBuilder builder) {
        this.two = builder.two;
    }

    /**
     * JAXB constructor
     */
    private ElementOne() {
        this.two = null;
    }

    public ElementTwo getTwo() {
        return two;
    }
}
