package com.gni.frmk.tools.addon.api.visitor;

import com.gni.frmk.tools.addon.api.TComposant1;
import com.gni.frmk.tools.addon.api.TComposant2;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:25
 *
 * @author: e03229
 */
public interface TConfigurationVisited extends Visitable<TConfigurationVisitor, TConfigurationVisited> {

    TComposant1 getComposant1();
    TComposant2 getComposant2();

}
