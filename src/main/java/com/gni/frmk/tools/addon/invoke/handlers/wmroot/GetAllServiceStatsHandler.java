package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.GetAllServiceStats;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.GetAllServiceStats.Result;
import com.google.common.collect.Sets;
import com.wm.data.*;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 20:22
 *
 * @author: e03229
 */
public class GetAllServiceStatsHandler extends AbstractHandler<GetAllServiceStats, Result>
        implements ActionHandler<GetAllServiceStats, Result, InvokeContext> {

    private final String toolsPackageName;

    GetAllServiceStatsHandler(String toolsPackageName) {
        super("wm.server.query:getAllServiceStats");
        this.toolsPackageName = checkNotNull(toolsPackageName);
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
    protected Result parseOutput(IData output) {
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
                            if (name.startsWith(toolsPackageName)) {
                                continue;
                            }
                            values.add(name);
                        }
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new Result(values);
        } finally {
            cur.destroy();
        }
    }
}
