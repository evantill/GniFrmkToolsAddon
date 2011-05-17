package thirdparty.jaxb.emptyconstructor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
public class ElementOneBuilder {

    public ElementTwo two;

    public ElementOneBuilder define(ElementOne element) {
        two = element.getTwo();
        return this;
    }

    public ElementOne build() {
        return new ElementOne(this);
    }

    public ElementOneBuilder define(ElementTwo two) {
        this.two = two;
        return this;
    }
}
