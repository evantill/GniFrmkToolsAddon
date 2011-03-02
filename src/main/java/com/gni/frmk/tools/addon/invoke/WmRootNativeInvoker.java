package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.data.port.Port;
import com.gni.frmk.tools.addon.data.port.PortBuilder;
import com.gni.frmk.tools.addon.data.scheduler.Scheduler;
import com.gni.frmk.tools.addon.data.scheduler.SchedulerBuilder;
import com.gni.frmk.tools.addon.data.trigger.NativeTrigger;
import com.gni.frmk.tools.addon.data.trigger.TriggerBuilder;
import com.wm.data.IData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 13 oct. 2010
 * Time: 18:57:23
 * To change this template use File | Settings | File Templates.
 */
public class WmRootNativeInvoker {
    private final ServiceInvoker suspendTriggers;
    private final ServiceInvoker getTriggerReport;

    private final ServiceInvoker enablePortListener;
    private final ServiceInvoker disablePortListener;
    private final ServiceInvoker listPortListeners;

    private final ServiceInvoker wakeUpUserTask;
    private final ServiceInvoker suspendUserTask;
    private final ServiceInvoker getUserTaskList;

    private final IntegrationServerUtil isUtil;

    public WmRootNativeInvoker(IntegrationServerUtil util, ServiceInvokerFactory factory) {
        isUtil = util;
        suspendTriggers = factory.createServiceInvokerBuilder("wm.server.triggers:suspendTrigger")
                .defineInput("triggerNameList",
                        "applyChangeAcrossCluster",
                        "retrievalSuspend",
                        "processingSuspend",
                        "persistChange",
                        "applyChangeAcrossCluster").defineOutput("message").build();
        getTriggerReport = factory.createServiceInvokerBuilder("wm.server.triggers:getTriggerReport")
                .defineOutput("triggers").build();
        enablePortListener = factory.createServiceInvokerBuilder("wm.server.net.listeners:enableListener")
                .defineInput("listenerKey", "pkg").defineOutput("message").build();
        disablePortListener = factory.createServiceInvokerBuilder("wm.server.net.listeners:disableListener")
                .defineInput("listenerKey", "pkg").defineOutput("message").build();
        listPortListeners = factory.createServiceInvokerBuilder("wm.server.ports:listListeners")
                .defineOutput("listeners", "primary").build();
        wakeUpUserTask = factory.createServiceInvokerBuilder("wm.server.schedule:wakeupUserTask")
                .defineInput("oid").defineOutput("message").build();
        suspendUserTask = factory.createServiceInvokerBuilder("wm.server.schedule:suspendUserTask")
                .defineInput("oid").defineOutput("message").build();
        getUserTaskList = factory.createServiceInvokerBuilder("wm.server.schedule:getUserTaskList")
                .defineOutput("tasks").build();
    }

    public void suspendTriggers(boolean retrievalSuspend,
                                boolean processingSuspend,
                                boolean persistChange,
                                String... triggerNameList) throws ServiceException {
        Map<String, Object> in = new HashMap<String, Object>(5);
        in.put("triggerNameList", triggerNameList);
        in.put("applyChangeAcrossCluster", "false");
        in.put("retrievalSuspend", Boolean.toString(retrievalSuspend));
        in.put("processingSuspend", Boolean.toString(processingSuspend));
        in.put("persistChange", Boolean.toString(persistChange));
        suspendTriggers.invoke(in);
    }

    public Set<NativeTrigger> getTriggerReport() throws ServiceException {
        Set<NativeTrigger> result = new TreeSet<NativeTrigger>();
        Map<String, ?> out = getTriggerReport.invoke(ServiceInvoker.EMPTY_DATA);
        if (out.containsKey("triggers")) {
            IData[] dataList = (IData[]) out.get("triggers");
            for (IData doc : dataList) {
                result.add(new TriggerBuilder().defineNativeStatus(doc).buildNativeTrigger());
            }
        }
        return result;
    }

    public void enablePortListener(String listenerKey, String pkg) throws ServiceException {
        Map<String, Object> in = new HashMap<String, Object>(2);
        in.put("listenerKey", listenerKey);
        in.put("pkg", pkg);
        enablePortListener.invoke(in);
    }

    public void disablePortListener(String listenerKey, String pkg) throws ServiceException {
        Map<String, Object> in = new HashMap<String, Object>(2);
        in.put("listenerKey", listenerKey);
        in.put("pkg", pkg);
        disablePortListener.invoke(in);
    }

    public Set<Port> listPortListeners() throws ServiceException {
        Set<Port> result = new TreeSet<Port>();
        Map<String, ?> out = listPortListeners.invoke(ServiceInvoker.EMPTY_DATA);
        if (out.containsKey("listeners")) {
            IData[] dataList = (IData[]) out.get("listeners");
            for (IData doc : dataList) {
                result.add(new PortBuilder().define(doc).build());
            }
        }
        if (out.containsKey("primary")) {
            IData primaryPort = (IData) out.get("primary");
            result.remove(new PortBuilder().define(primaryPort).build());
        }
        return result;
    }

    public void wakeupUserTask(String oid) throws ServiceException {
        wakeUpUserTask.invokeSingleInput(oid);
    }

    public void suspendUserTask(String oid) throws ServiceException {
        suspendUserTask.invokeSingleInput(oid);
    }

    public Set<Scheduler> getUserTaskList() throws ServiceException {
        Set<Scheduler> result = new TreeSet<Scheduler>();
        Map<String, ?> out = getUserTaskList.invoke(ServiceInvoker.EMPTY_DATA);
        if (out.containsKey("tasks")) {
            IData[] dataList = (IData[]) out.get("tasks");
            //TODO  a corriger
//            for (IData doc : dataList) {
//                result.add(new SchedulerBuilder().defineScheduler(doc).build());
//            }
        }
        return result;
    }

    public boolean isEnabled() {
        return isUtil.isPackageEnabled("WmRoot");
    }

}
