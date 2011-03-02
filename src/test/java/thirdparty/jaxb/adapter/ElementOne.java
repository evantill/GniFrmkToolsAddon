package thirdparty.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@XmlJavaTypeAdapter(ElementOneBuilder.class)
public class ElementOne {
    private final ElementTwo two;

    public ElementOne(ElementOneBuilder builder) {
        this.two = builder.two;
    }

    public ElementTwo getTwo() {
        return two;
    }
}
