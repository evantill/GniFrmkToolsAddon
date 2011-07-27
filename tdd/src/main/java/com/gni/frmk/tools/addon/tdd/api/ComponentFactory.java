package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 16:50
 *
 * @author: e03229
 */
public interface ComponentFactory {

    public static interface ComponentFactoryDelegate<T extends ComponentType<C, I, ?>, C extends Component<C, ?>, I extends ComponentId<I>> {
        C createComponent(I id);

        Class<T> getCreatedComponentType();
    }

    <T extends ComponentType<C, I, ?>, C extends Component<C, ?>, I extends ComponentId<I>> C createComponent(T type, I id);

    public static class ComponentFactoryException extends RuntimeException {
        public ComponentFactoryException(String message) {
            super(message);
        }
    }
}
