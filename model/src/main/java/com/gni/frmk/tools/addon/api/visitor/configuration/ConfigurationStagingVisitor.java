package com.gni.frmk.tools.addon.api.visitor.configuration;

import com.gni.frmk.tools.addon.api.visitor.configuration.ConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/05/11
 * Time: 08:38
 *
 * @author: e03229
 */
public interface ConfigurationStagingVisitor extends ConfigurationVisitor {
    public static enum Stage {
        PHASE1, PHASE2
    }

    boolean startStage(Stage stage);

    boolean endStage(Stage stage);
}
