package thirdparty.jaxb.emptyconstructor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
public class ElementTwoBuilder {

    public String contenu;

    public ElementTwoBuilder define(ElementTwo element) {
        contenu = element.getContenu();
        return this;
    }

    public ElementTwo build() {
        return new ElementTwo(this);
    }

    public ElementTwoBuilder define(String contenu) {
        this.contenu = contenu;
        return this;
    }
}