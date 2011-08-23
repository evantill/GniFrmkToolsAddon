package com.gni.frmk.tools.addon.tdd.alpha;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 18:33
 *
 * @author: e03229
 */
public class Scenario {

    interface ComponentType {
    }

    interface ComponentId {
    }

    /**
     * different pour chaque plugins
     */
    interface ComponentData {
        ComponentType getType();

        ComponentId getId();

        ComponentData save();

        void restore(ComponentData saved);
    }

    /**
     * different pour chaque plugins
     */
    interface ComponentDataFactory {
        List<ComponentType> getComponentTypes();

        List<ComponentId> getComponentIdsByType(ComponentType type);

        ComponentData getComponentData(ComponentType type, ComponentId id);
    }

    /**
     * persist data (xml files,db....)
     */
    interface ComponentDataRepository {
        ComponentData load(ComponentType type, ComponentId id);

        void save(ComponentData saved);
    }

    /**
     * different pour chaque plugins
     */
    interface ComponentStrategy {
        void open();

        void close();

        void refreshData();
    }

    interface ComponentStrategyFactory {
        List<ComponentType> getComponentTypes();

        ComponentStrategy getStrategy(ComponentType type, ComponentId id);
    }

    /**
     * permettra de definir un referentiel des strategies definies par les plugins
     */
    interface ComponentStrategyRegistry {
        void register(ComponentStrategyFactory factory);

        ComponentStrategy findStrategy(ComponentType type);
    }

    interface Component extends ComponentData, ComponentStrategy {

    }

    interface ComponentFactory extends ComponentDataFactory, ComponentStrategyFactory {
        Component getComponent(ComponentType type, ComponentId id);

        List<Component> getComponentsByType(ComponentType type);
    }

    interface ComponentRegistry extends ComponentDataRepository, ComponentStrategyRegistry {
        void register(ComponentFactory factory);

        Component findComponent(ComponentType type, ComponentId id);
    }

    interface Context {
        ComponentStrategyRegistry getComponentStrategyRegistry();

        ComponentDataRepository getComponentDataRepository();

        ComponentRegistry getComponentRegistry();
    }

    public static void doLoadComponentFromConfig(ComponentType type, ComponentId id, Context context) {
        //dans ce ca le component factory utilise par le registry ne charge pas les donnees : un ComponentDataFactory qui retourne toujours UNKNOWN_DATA
        Component component = context.getComponentRegistry().findComponent(type, id);
        ComponentData data = context.getComponentDataRepository().load(type, id);
        component.restore(data);
    }

    public static void doLoadComponentFromIS(ComponentType type, ComponentId id, Context context) {
        //dans ce ca l'IS est un registry de component qui utilisera un component factory qui chargera les donnees a la volee
        Component component = context.getComponentRegistry().findComponent(type, id);
    }


    public static void doOpenComponent(ComponentType type, ComponentId id, Context context) {
        Component component = context.getComponentRegistry().findComponent(type, id);
        component.refreshData();
        ComponentData saved = component.save();
        try {
            component.open();
        } catch (RuntimeException ex) {
            component.restore(saved);
            component.close();
        }
    }

    public static void doSaveComponent(ComponentType type, ComponentId id, Context context) {
        Component component = context.getComponentRegistry().findComponent(type, id);
        component.refreshData();
        ComponentData saved = component.save();
        context.getComponentDataRepository().save(saved);
    }

}