package thirdparty.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@XmlJavaTypeAdapter(ElementTwoBuilder.class)
public class ElementTwo {
    private final String contenu;

    public ElementTwo(ElementTwoBuilder builder) {
        this.contenu = builder.contenu;
    }

    public String getContenu() {
        return contenu;
    }
}
