package com.gni.frmk.tools.addon.visitor.essai.impl;

import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationVisited;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:47
 *
 * @author: e03229
 */
public class Configuration implements ConfigurationVisited {
    private final Composant1 c1;
    private final Composant2 c2;

    public Configuration(Composant1 c1, Composant2 c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public Composant1 getCompoqsant1() {
        return c1;
    }

    @Override
    public Composant2 getCompoqsant2() {
        return c2;
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        System.out.println("Configuration.accept");
        visitor.dispatchVisit(this);
    }
}
