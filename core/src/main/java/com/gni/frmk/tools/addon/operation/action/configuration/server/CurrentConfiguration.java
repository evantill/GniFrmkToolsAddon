package com.gni.frmk.tools.addon.operation.action.configuration.server;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 13:27
 *
 * @author: e03229
 */
public class CurrentConfiguration implements Action<SingleResult<Configuration>> {
    private final Date now;

    public CurrentConfiguration() {
        now = new Date();
    }

    public CurrentConfiguration(Date now) {
        this.now = new Date(now.getTime());
    }

    public Date getNow() {
        return new Date(now.getTime());
    }
}
