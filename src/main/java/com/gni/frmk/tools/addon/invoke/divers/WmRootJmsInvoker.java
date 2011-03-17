package com.gni.frmk.tools.addon.invoke.divers;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.data.trigger.JmsAlias;
import com.gni.frmk.tools.addon.data.trigger.JmsAliasBuilder;
import com.gni.frmk.tools.addon.data.trigger.JmsTrigger;
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
 * Time: 17:22:06
 * To change this template use File | Settings | File Templates.
 */
public class WmRootJmsInvoker {
    private final ServiceInvoker enableJmsTriggers;
    private final ServiceInvoker disableJmsTriggers;
    private final ServiceInvoker suspendJmsTriggers;
    private final ServiceInvoker getTriggerReport;
    private final ServiceInvoker enableConnectionAlias;
    private final ServiceInvoker disableConnectionAlias;
    private final ServiceInvoker getConnectionAliasReport;
    private final IntegrationServerUtil isUtil;

    public WmRootJmsInvoker(IntegrationServerUtil util, ServiceInvokerFactory factory) {
        isUtil = util;
        enableJmsTriggers = factory.createServiceInvokerBuilder("wm.server.jms:enableJMSTriggers")
                .defineInput("triggerNameList", "applyChangeAcrossCluster")
                .defineOutput("message")
                .build();
        disableJmsTriggers = factory.createServiceInvokerBuilder("wm.server.jms:disableJMSTriggers")
                .defineInput("triggerNameList", "applyChangeAcrossCluster")
                .defineOutput("message")
                .build();
        suspendJmsTriggers = factory.createServiceInvokerBuilder("wm.server.jms:suspendJMSTriggers")
                .defineInput("triggerNameList", "applyChangeAcrossCluster")
                .defineOutput("message")
                .build();
        getTriggerReport = factory.createServiceInvokerBuilder("wm.server.jms:getTriggerReport")
                .defineOutput("triggerDataList").build();
        enableConnectionAlias = factory.createServiceInvokerBuilder("wm.server.jms:enableConnectionAlias")
                .defineInput("aliasName").defineOutput("message").build();
        disableConnectionAlias = factory.createServiceInvokerBuilder("wm.server.jms:disableConnectionAlias")
                .defineInput("aliasName").defineOutput("message").build();
        getConnectionAliasReport = factory.createServiceInvokerBuilder("wm.server.jms:getConnectionAliasReport")
                .defineOutput("aliasDataList").build();
    }

    public void enableJMSTriggers(String... triggerNameList) throws ServiceException {
        Map<String, Object> in = new HashMap<String, Object>(2);
        in.put("triggerNameList", triggerNameList);
        in.put("applyChangeAcrossCluster", "false");
        enableJmsTriggers.invoke(in);
    }

    public void disableJMSTriggers(String... triggerNameList) throws ServiceException {
        Map<String, Object> in = new HashMap<String, Object>(2);
        in.put("triggerNameList", triggerNameList);
        in.put("applyChangeAcrossCluster", "false");
        disableJmsTriggers.invoke(in);
    }

    public void suspendJmsTriggers(String... triggerNameList) throws ServiceException {
        Map<String, Object> in = new HashMap<String, Object>(2);
        in.put("triggerNameList", triggerNameList);
        in.put("applyChangeAcrossCluster", "false");
        suspendJmsTriggers.invoke(in);
    }

    public Set<JmsTrigger> getTriggerReport() throws ServiceException {
        Set<JmsTrigger> result = new TreeSet<JmsTrigger>();
        Map<String, ?> out = getTriggerReport.invoke(ServiceInvoker.EMPTY_DATA);
        if (out.containsKey("triggerDataList")) {
            IData[] dataList = (IData[]) out.get("triggerDataList");
            for (IData doc : dataList) {
                result.add(new TriggerBuilder().defineJmsStatus(doc).buildJmsTrigger());
            }
        }
        return result;
    }

    public void enableConnectionAlias(String aliasName) throws ServiceException {
        enableConnectionAlias.invokeSingleInput(aliasName);
    }

    public void disableConnectionAlias(String aliasName) throws ServiceException {
        disableConnectionAlias.invokeSingleInput(aliasName);
    }

    public Set<JmsAlias> getConnectionAliasReport() throws ServiceException {
        Set<JmsAlias> result = new TreeSet<JmsAlias>();
        Map<String, ?> out = getConnectionAliasReport.invoke(ServiceInvoker.EMPTY_DATA);
        if (out.containsKey("aliasDataList")) {
            IData[] dataList = (IData[]) out.get("aliasDataList");
            for (IData doc : dataList) {
                result.add(new JmsAliasBuilder().define(doc).build());
            }
        }
        return result;
    }

    public boolean isEnabled() {
        return isUtil.isPackageEnabled("WmRoot") && enableJmsTriggers.exist();
    }

}
