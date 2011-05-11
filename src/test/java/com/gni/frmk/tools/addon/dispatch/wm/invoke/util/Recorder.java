package com.gni.frmk.tools.addon.dispatch.wm.invoke.util;

import com.gni.frmk.tools.addon.action.component.art.RetrieveAdapterTypesList;
import com.gni.frmk.tools.addon.action.component.art.connection.DisableConnection;
import com.gni.frmk.tools.addon.action.component.art.connection.EnableConnection;
import com.gni.frmk.tools.addon.action.component.art.connection.AdapterConnectionIdList;
import com.gni.frmk.tools.addon.action.component.art.listener.ListenerIdList;
import com.gni.frmk.tools.addon.action.component.art.notifications.NotificationIdList;
import com.gni.frmk.tools.addon.action.component.art.notifications.ResumeNotification;
import com.gni.frmk.tools.addon.action.component.art.notifications.SuspendNotification;
import com.gni.frmk.tools.addon.action.component.jms.alias.DisableJmsAlias;
import com.gni.frmk.tools.addon.action.component.jms.alias.EnableJmsAlias;
import com.gni.frmk.tools.addon.action.component.jms.alias.JmsAliasIdList;
import com.gni.frmk.tools.addon.action.component.jms.trigger.DisableJmsTriggers;
import com.gni.frmk.tools.addon.action.component.jms.trigger.EnableJmsTriggers;
import com.gni.frmk.tools.addon.action.component.jms.trigger.JmsTriggerIdList;
import com.gni.frmk.tools.addon.action.component.jms.trigger.SuspendJmsTriggers;
import com.gni.frmk.tools.addon.action.component.root.port.DisablePortListener;
import com.gni.frmk.tools.addon.action.component.root.port.EnablePortListener;
import com.gni.frmk.tools.addon.action.component.root.port.PortListenerIdList;
import com.gni.frmk.tools.addon.action.component.root.scheduler.SchedulerIdList;
import com.gni.frmk.tools.addon.action.component.root.scheduler.SuspendUserTask;
import com.gni.frmk.tools.addon.action.component.root.scheduler.WakeUpUserTask;
import com.gni.frmk.tools.addon.action.component.root.service.GetAllServiceStats;
import com.gni.frmk.tools.addon.action.component.root.trigger.NativeTriggerIdList;
import com.gni.frmk.tools.addon.action.component.root.trigger.SuspendTriggers;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeDispatcher;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeServiceRegistry;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeServiceRegistryBuilder;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInvokeException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.context.InvokeContextRecord;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.context.InvokeContextRemote;
import com.gni.frmk.tools.addon.handler.InvokeHandler;
import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.model.component.id.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.result.NoResult;
import com.google.common.collect.Lists;
import com.wm.lang.ns.NSName;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:11
 *
 * @author: e03229
 */
public class Recorder {

    static class AllActions implements Action<NoResult> {
        final InvokeDispatcher dispatcher;
        final List<Action<?>> actions = Lists.newArrayList();

        AllActions(InvokeDispatcher dispatcher) {
            //TODO completer avec les services jms et art
            this.dispatcher = dispatcher;
            //services
            addAction(new GetAllServiceStats());
            //ports
            addAction(new PortListenerIdList());
            addAction(new DisablePortListener(new PackageAndStringId("GniFrmkToolsAddOnTest", "HTTPListener@9999")));
            addAction(new EnablePortListener(new PackageAndStringId("GniFrmkToolsAddOnTest", "HTTPListener@9999")));
            //triggers
            addAction(new NativeTriggerIdList());
            addAction(SuspendTriggers.builder()
                                     .addTriggerName("GniFrmkToolsAddOnTest.trigger:nativeTrigger")
                                     .persistChange(true)
                                     .suspendProcessing(true)
                                     .suspendRetrieval(true)
                                     .build());
            addAction(SuspendTriggers.builder()
                                     .addTriggerName("GniFrmkToolsAddOnTest.trigger:nativeTrigger")
                                     .persistChange(true)
                                     .suspendProcessing(false)
                                     .suspendRetrieval(false)
                                     .build());
            //tasks
            addAction(new SchedulerIdList());
            addAction(new SuspendUserTask(new StringId("b2f57910-5edd-11e0-8fef-b8721ad3c5a7")));
            addAction(new WakeUpUserTask(new StringId("b2f57910-5edd-11e0-8fef-b8721ad3c5a7")));
            //art
            addAction(new RetrieveAdapterTypesList());
            addAction(new AdapterConnectionIdList("JDBCAdapter"));
            addAction(new DisableConnection(new AdapterId("GniFrmkToolsAddOnTest.adapter.jdbc:cnxEssai", "JDBCAdapter")));
            addAction(new EnableConnection(new AdapterId("GniFrmkToolsAddOnTest.adapter.jdbc:cnxEssai", "JDBCAdapter")));
            //
            addAction(new NotificationIdList("JDBCAdapter"));
            addAction(new SuspendNotification(new AdapterId("GniFrmkToolsAddOnTest.adapter.jdbc:notification", "JDBCAdaper")));
            addAction(new ResumeNotification(new AdapterId("GniFrmkToolsAddOnTest.adapter.jdbc:notification", "JDBCAdaper")));
            //
            addAction(new ListenerIdList("JDBCAdapter"));
            //jms
            addAction(new JmsAliasIdList());
            addAction(new DisableJmsAlias(new StringId("GniFrmkToolsAddOnTest_JMS_ALIAS")));
            addAction(new EnableJmsAlias(new StringId("GniFrmkToolsAddOnTest_JMS_ALIAS")));
            //
            addAction(new JmsTriggerIdList());
            addAction(new SuspendJmsTriggers(new StringId("GniFrmkToolsAddOnTest.jms:jmsTrigger")));
            addAction(new DisableJmsTriggers(new StringId("GniFrmkToolsAddOnTest.jms:jmsTrigger")));
            addAction(new EnableJmsTriggers(new StringId("GniFrmkToolsAddOnTest.jms:jmsTrigger")));
        }

        private void addAction(Action<?> action) {
            actions.add(action);
        }

        public List<Action<?>> getActions() {
            return Collections.unmodifiableList(actions);
        }

        public InvokeDispatcher getDispatcher() {
            return dispatcher;
        }
    }

    public static class AllActionsHandler implements InvokeHandler<AllActions, NoResult> {
        @Override
        public Class<AllActions> getActionType() {
            return AllActions.class;
        }

        @Override
        public NoResult execute(AllActions action, InvokeContext context) throws ServiceInvokeException {
            for (Action<?> a : action.getActions()) {
                try {
                    action.getDispatcher().execute(a);
                } catch (DispatchException e) {
                    throw new ServiceInvokeException(context, a, getService(), e);
                }
            }
            return NoResult.newInstance();
        }

        @Override
        public NSName getService() {
            return NSName.create("none");
        }
    }

    public static void main(String[] args) throws DispatchException {
        PipelineTestUtils utils = new PipelineTestUtils(Recorder.class);
        InvokeContextRemote remoteCtx = new InvokeContextRemote("ar1tn232.groupe.generali.fr:7502", "evantill", "evantill", true);
        InvokeContextRecord ctx = new InvokeContextRecord(remoteCtx, utils);
        remoteCtx.connect();
        try {
            InvokeServiceRegistry registry = new InvokeServiceRegistryBuilder().defineServices()
                                                                               .addHandler(new AllActionsHandler())
                                                                               .build();
            InvokeDispatcher dispatcher = new InvokeDispatcher(registry, ctx);
            dispatcher.execute(new AllActions(dispatcher));
        } finally {
            remoteCtx.disconnect();
            System.exit(0);
        }
    }
}
