package thirdparty.jaxb.emptyconstructor;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
public class ElementTwo {

    @XmlElement
    private final String contenu;

    public ElementTwo(ElementTwoBuilder builder) {
        this.contenu = builder.contenu;
    }

    /**
     * JAXB constructor
     */
    private ElementTwo() {
        contenu=null;
    }

    public String getContenu() {
        return contenu;
    }
}
