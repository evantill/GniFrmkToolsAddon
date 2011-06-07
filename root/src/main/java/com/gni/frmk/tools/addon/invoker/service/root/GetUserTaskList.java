package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.SchedulerInfo;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 18:12
 *
 * @author: e03229
 */
public class GetUserTaskList extends WmService<NoInput, ListValueOutput<SchedulerInfo>> {
    private enum SuspendedState {
        SUSPENDED(true), READY(false);

        private final boolean suspended;

        private SuspendedState(boolean suspended) {
            this.suspended = suspended;
        }

        public boolean isSuspended() {
            return suspended;
        }
    }

    private enum ExpiredState {
        UNEXPIRED(false), EXPIRED(true);
        private final boolean expired;

        private ExpiredState(boolean expired) {
            this.expired = expired;
        }

        public boolean isExpired() {
            return expired;
        }
    }

    public GetUserTaskList() {
        super("wm.server.schedule:getUserTaskList");
    }

    @Override
    protected IData prepareInput(NoInput input) throws ServiceInputException {
        return EMPTY_IDATA;
    }

    @Override
    protected ListValueOutput<SchedulerInfo> prepareOutput(IData output) throws ServiceOutputException {
        List<SchedulerInfo> values = Lists.newArrayList();
        IData[] tasks = extractIDataArray(checkDataExist(output), "tasks", false, EMPTY_IDATA_ARRAY);
        for (IData task : tasks) {
            IData checkedTask = checkDataExist(task);
            SchedulerInfo info = SchedulerInfo.builder()
                                              .oid(extractStringValue(checkedTask, "oid", true, null))
                                              .service(extractStringValue(checkedTask, "service", true, null))
                                              .name(extractStringValue(checkedTask, "name", true, null))
                                              .description(extractStringValue(checkedTask, "description", false, ""))
                                              .type(extractStringValue(checkedTask, "type", true, null))
                                              .suspended(SuspendedState.valueOf(extractStringValue(checkedTask, "execState", true, null)
                                                      .toUpperCase()).isSuspended())
                                              .expired(ExpiredState.valueOf(extractStringValue(checkedTask, "schedState", true, null)
                                                      .toUpperCase()).isExpired())
                                              .neverRun(extractBooleanValue(checkedTask, "neverRun", false, Boolean.FALSE))
                                              .build();
            values.add(info);
        }
        return ListValueOutput.newInstance(values);
    }
}
