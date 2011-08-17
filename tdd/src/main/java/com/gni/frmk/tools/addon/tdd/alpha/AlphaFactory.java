package com.gni.frmk.tools.addon.tdd.alpha;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 18:03
 *
 * @author: e03229
 */
public abstract class AlphaFactory {

    public abstract BaseComponent buildComponentFromConfig(AlphaConfig config,AlphaContext context);

    public abstract  AlphaConfig buildConfig(BaseComponent component);
}
