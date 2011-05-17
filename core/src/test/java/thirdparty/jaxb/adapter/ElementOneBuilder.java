package thirdparty.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
public class ElementOneBuilder extends XmlAdapter<ElementOneBuilder, ElementOne> {

    public ElementTwo two;

    public ElementOneBuilder define(ElementOne element) {
        two = element.getTwo();
        return this;
    }

    public ElementOne build() {
        return new ElementOne(this);
    }

    @Override
    public ElementOneBuilder marshal(ElementOne element) throws Exception {
        return new ElementOneBuilder().define(element);
    }

    @Override
    public ElementOne unmarshal(ElementOneBuilder builder) throws Exception {
        return builder.build();
    }

    public ElementOneBuilder define(ElementTwo two) {
        this.two = two;
        return this;
    }
}
