package com.gni.frmk.tools.addon.tdd.alpha;


import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 18:33
 *
 * @author: e03229
 */
public class Scenario {
    class Data {

        private Type type;
        private Id id;
        //ensemble des donnees
        //definir des sections supplementaires comme le statut

        public Type getType() {return type;}
    }

    class Type {
        public Factory getFactory() {
            throw new IllegalStateException("retourne le factory specifique a ce type de composant");
        }
    }

    class Id {
    }


    class Command {
        public void execute(Context ctx, Component component) {
            throw new IllegalStateException("le traitement specificque est ici");
        }
    }

    class Context {
        private Map<Type, Command> openRegistry;

        public Command selectOpenStrategy(Type type) {
            return openRegistry.get(type);
        }
    }

    class Factory {
        public List<Component> createComponents(Type type, Context ctx) {
            throw new IllegalStateException("extraction des donnees des composants de l'is");
        }

        public Data createData(Type type, Id id) {
            throw new IllegalStateException("extraction des donnees d'un composant de l'is");
        }

        public Component createComponent(Data data, Context ctx) {
            throw new IllegalStateException("creation du composant specifique");
        }

        public Component createComponentFromId(Id id, Context ctx) {
            throw new IllegalStateException("recherche du composant ");
        }

        public Component createComponentFromData() {

        }
    }


    class Component {

        private Type type;

        public void open(Context ctx) {
            ctx.selectOpenStrategy(getType()).execute(ctx, this);
        }

        private Type getType() {
            return type;
        }
    }


    private Component buildComponent(Type type, Id id, Context ctx) {
        Data data = loadData(type, id, ctx);
        return type.getFactory().createComponentFromConfig(type, data, ctx);
    }

    private Component buildComponent(Data data, Context ctx) {
        Type type = data.getType();
        Factory factory = type.getFactory();
        return factory.createComponentFromConfig(type, data, ctx);
    }

    private Context selectContext() {
        throw new IllegalStateException("selection du context de connection (local,remote,invoke,jms...)");
    }

    private Data readConfig() {
        throw new IllegalStateException("parsing fichier xml de config");
    }

    private Data loadData(Type type, Id id, Context ctx) {
        throw new IllegalStateException("construction des donnees relatives au composant");
    }


    void openScenario(Type type, Id id) {
        Context ctx = selectContext();
        Component c = buildComponent(type, id, ctx);
        c.open(ctx);
    }

    void loadComponentScenario() {
        Data data = readConfig();
        Context ctx = selectContext();
        Component c = buildComponent(data, ctx);
        c.open(ctx);
    }

}
