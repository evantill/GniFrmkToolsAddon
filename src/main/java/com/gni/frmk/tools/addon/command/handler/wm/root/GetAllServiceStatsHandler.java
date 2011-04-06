package com.gni.frmk.tools.addon.command.handler.wm.root;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.command.action.wm.root.GetAllServiceStats;
import com.gni.frmk.tools.addon.command.action.wm.root.GetAllServiceStats.Result;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.google.common.collect.Sets;
import com.wm.data.*;

import java.util.Set;

import static com.google.common.collect.Sets.filter;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 20:22
 *
 * @author: e03229
 */
public class GetAllServiceStatsHandler extends AbstractInvokeHandler<GetAllServiceStats, Result>
        implements ActionHandler<GetAllServiceStats, Result, InvokeContext> {

    public GetAllServiceStatsHandler() {
        super("wm.server.query:getAllServiceStats");
    }

    @Override
    public Class<GetAllServiceStats> getActionType() {
        return GetAllServiceStats.class;
    }

    @Override
    protected IData prepareInput(GetAllServiceStats in) {
        return EMPTY_INPUT;
    }

    @Override
    protected Result parseOutput(GetAllServiceStats action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Set<String> values = Sets.newTreeSet();
            IData[] statsDatas = IDataUtil.getIDataArray(cur, "SvcStats");
            if (statsDatas != null) {
                for (IData statData : statsDatas) {
                    IDataCursor curLoop = statData.getCursor();
                    try {
                        String sRunning = IDataUtil.getString(curLoop, "sRunning");
                        String ifc = IDataUtil.getString(curLoop, "ifc");
                        String svc = IDataUtil.getString(curLoop, "svc");
                        String name = String.format("%s:%s", ifc, svc);
                        if (!IntegrationServerUtil.EMPTY_VALUE_NBSP.equals(sRunning)) {
                            values.add(name);
                        }
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new Result(filter(values, action.getFilter()));
        } finally {
            cur.destroy();
        }
    }
}