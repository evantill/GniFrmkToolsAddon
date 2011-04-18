package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.api.visitor.TConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.TConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:47
 *
 * @author: e03229
 */
public class TConfiguration implements TConfigurationVisited {
    private final TComposant1 c1;
    private final TComposant2 c2;

    public TConfiguration(TComposant1 c1, TComposant2 c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public TComposant1 getComposant1() {
        return c1;
    }

    @Override
    public TComposant2 getComposant2() {
        return c2;
    }

    @Override
    public void accept(TConfigurationVisitor visitor) {
        System.out.println("Configuration.accept");
        visitor.dispatchVisit(this);
    }
}
