package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 16:50
 *
 * @author: e03229
 */
public interface ComponentFactory {

    public static interface ComponentFactoryDelegate
            <T extends ComponentType<T>, C extends Component<C>, I extends ComponentId<I>> {

        C newComponent(T type, I id) throws ComponentFactoryException;

        Class<T> getComponentTypeClass();

        Class<I> getComponentIdClass();
    }

    Component newComponent(ComponentType type, ComponentId id) throws ComponentFactoryException;

    public static class ComponentFactoryException extends RuntimeException {
        public ComponentFactoryException(String message) {
            super(message);
        }
    }
}
