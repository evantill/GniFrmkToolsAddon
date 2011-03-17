package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.configuration.components.EnableState;
import com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.configuration.components.IntegrationServerPackage;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.PackageList;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.results.SetResult;
import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.google.common.collect.Sets;
import com.wm.data.*;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class PackageListHandler extends AbstractHandler<PackageList, SetResult<IntegrationServerPackage>>
        implements ActionHandler<PackageList, SetResult<IntegrationServerPackage>, InvokeContext> {

    public PackageListHandler() {
        super("wm.server.packages:packageList");
    }

    @Override
    public Class<PackageList> getActionType() {
        return PackageList.class;
    }

    @Override
    protected SetResult<IntegrationServerPackage> parseOutput(IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Set<IntegrationServerPackage> values = Sets.newHashSet();
            IData[] packagesDatas = IDataUtil.getIDataArray(cur, "packages");
            if (packagesDatas != null) {
                for (IData packageData : packagesDatas) {
                    IDataCursor curLoop = packageData.getCursor();
                    try {
                        EnableStatus enabled = EnableStatus.fromBooleanString(IDataUtil.getString(curLoop, "enabled"));
                        String pkgName = IDataUtil.getString(curLoop, "name");
                        values.add(IntegrationServerPackage.builder()
                                                           .packageName(pkgName)
                                                           .defineState(new EnableState(enabled))
                                                           .build());
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new SetResult<IntegrationServerPackage>(values);
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(PackageList in) {
        return EMPTY_INPUT;
    }

}