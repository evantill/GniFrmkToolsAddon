package thirdparty.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
public class ElementTwoBuilder extends XmlAdapter<ElementTwoBuilder, ElementTwo> {

    public String contenu;

    public ElementTwoBuilder define(ElementTwo element) {
        contenu = element.getContenu();
        return this;
    }

    public ElementTwo build() {
        return new ElementTwo(this);
    }

    @Override
    public ElementTwoBuilder marshal(ElementTwo element) throws Exception {
        return new ElementTwoBuilder().define(element);
    }

    @Override
    public ElementTwo unmarshal(ElementTwoBuilder builder) throws Exception {
        return builder.build();
    }

    public ElementTwoBuilder define(String contenu) {
        this.contenu=contenu;
        return this;
    }
}