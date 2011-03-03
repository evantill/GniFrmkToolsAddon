package thirdparty.jaxb.registry;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
public interface Element {
    ElementType getType();
    String getName();
    Status getStatus();
}
