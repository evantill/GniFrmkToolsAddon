package com.gni.frmk.tools.addon;

import com.wm.app.b2b.server.InvokeState;
import com.wm.app.b2b.server.PackageManager;
import com.wm.app.b2b.server.ServerAPI;
import com.wm.app.b2b.server.ns.Namespace;
import com.wm.lang.ns.NSName;
import com.wm.util.JournalLogger;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 30 sept. 2010
 * Time: 16:06:25
 * To change this template use File | Settings | File Templates.
 */
public class IntegrationServerUtil {
    public static final String EMPTY_VALUE_NBSP = "&nbsp;";
    private final String toolsPkgName;

    public IntegrationServerUtil(String toolsPkgName) {
        this.toolsPkgName = toolsPkgName;
    }

    public File getCurrentPackageConfigDir() {
        InvokeState state = InvokeState.getCurrentState();
        String packageName = state.getService().getPackage().getName();
        return ServerAPI.getPackageConfigDir(packageName);
    }

    public void logException(String message, Exception e) {
        JournalLogger.logWarning(9999, JournalLogger.FAC_DEBUG, message);
        JournalLogger.logDebug(9998, JournalLogger.FAC_DEBUG, e);
    }

    public boolean isPackageEnabled(String packageName) {
        return PackageManager.isEnabled(packageName);
    }

    public boolean isNodeExist(String nodeName) {
        return Namespace.current().nodeExists(NSName.create(nodeName));
    }

    public String getToolsPackageName() {
        return toolsPkgName;
    }
}
