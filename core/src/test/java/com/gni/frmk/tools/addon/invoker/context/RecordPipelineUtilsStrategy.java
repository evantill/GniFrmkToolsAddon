package com.gni.frmk.tools.addon.invoker.context;

import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/06/11
 * Time: 19:24
 *
 * @author: e03229
 */
public interface RecordPipelineUtilsStrategy {
    String generateId(NSName serviceName);
    String checkNextId(NSName serviceName);
}
