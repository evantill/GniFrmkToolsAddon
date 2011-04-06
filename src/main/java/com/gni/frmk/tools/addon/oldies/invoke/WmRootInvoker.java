package com.gni.frmk.tools.addon.oldies.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.command.action.wm.root.*;
import com.gni.frmk.tools.addon.command.action.wm.root.GetAllServiceStats.Result;
import com.gni.frmk.tools.addon.command.api.ActionException;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeException;
import com.gni.frmk.tools.addon.command.handler.wm.root.GetAllServiceStatsHandler;
import com.gni.frmk.tools.addon.command.handler.wm.root.PackageListHandler;
import com.gni.frmk.tools.addon.command.handler.wm.root.WaitServicesEndHandler;
import com.gni.frmk.tools.addon.model.component.IntegrationServerPackage;
import com.gni.frmk.tools.addon.model.component.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.Port;
import com.gni.frmk.tools.addon.model.component.Scheduler;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 20:03
 *
 * @author: e03229
 */
public class WmRootInvoker extends AbstractWmHandler {

    private final String toolsPackageName;

    public WmRootInvoker(IntegrationServerUtil util, InvokeContext context, String toolsPackageName) {
        super("WmRoot", util, context);
        this.toolsPackageName = toolsPackageName;
        addHandler(new PackageListHandler());
        addHandler(new GetAllServiceStatsHandler());
        addHandler(new WaitServicesEndHandler());
    }

    public void disablePortListener(DisablePortListener param) throws InvokeException, ActionException {
        invoke(param);
    }

    public void disableScheduler(SuspendUserTask param) throws InvokeException, ActionException {
        invoke(param);
    }

    public Set<IntegrationServerPackage> getPackageList(PackageList param) throws ActionException, InvokeException {
        return invoke(param).getCollection();
    }

    public Result getAllServiceStats() throws ActionException, InvokeException {
        return invoke(new GetAllServiceStats(toolsPackageName));
    }

    public int getNbrRunningServices() throws ActionException, InvokeException {
        return getAllServiceStats().getNbrRunningServices();
    }

    public Set<String> getRunningServices() throws ActionException, InvokeException {
        return getAllServiceStats().getRunningServices();
    }

    public void waitServicesEnd(long maxSecondsToWait) throws ActionException, InvokeException {
        invoke(new WaitServicesEnd(toolsPackageName, maxSecondsToWait, 500));
    }

    public void suspendUserTask(SuspendUserTask param) throws InvokeException, ActionException {
        invoke(param);
    }

    public void suspendTriggers(SuspendTriggers param) throws InvokeException, ActionException {
        invoke(param);
    }

    public void disablePackage(DisablePackage param) throws InvokeException, ActionException {
        invoke(param);
    }

    public void enablePortListener(EnablePortListener param) throws InvokeException, ActionException {
        invoke(param);
    }

    public void wakeUpUserTask(WakeUpUserTask param) throws InvokeException, ActionException {
        invoke(param);
    }

    public void enablePackage(EnablePackage param) throws InvokeException, ActionException {
        invoke(param);
    }

    public List<Port> listPortListeners() throws InvokeException, ActionException {
        return invoke(new ListPortListeners()).getCollection();
    }

    public List<Scheduler> getUserTaskList() throws InvokeException, ActionException {
        return invoke(new GetUserTaskList()).getCollection();
    }

    public List<NativeTrigger> getNativeTriggerReport() throws InvokeException, ActionException {
        return invoke(new GetNativeTriggerReport()).getCollection();
    }
}
