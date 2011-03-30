package com.gni.frmk.tools.addon.invoke.handler.wmroot;

import com.gni.frmk.tools.addon.configuration.component.EnableState;
import com.gni.frmk.tools.addon.configuration.component.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.configuration.component.IntegrationServerPackage;
import com.gni.frmk.tools.addon.dispatcher.SetResult;
import com.gni.frmk.tools.addon.invoke.action.wmroot.PackageList;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
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
public class PackageListHandler extends AbstractInvokeHandler<PackageList, SetResult<IntegrationServerPackage>>
        implements ActionHandler<PackageList, SetResult<IntegrationServerPackage>, InvokeContext> {

    public PackageListHandler() {
        super("wm.server.packages:packageList");
    }

    @Override
    public Class<PackageList> getActionType() {
        return PackageList.class;
    }

    @Override
    protected SetResult<IntegrationServerPackage> parseOutput(PackageList action, IData output) {
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