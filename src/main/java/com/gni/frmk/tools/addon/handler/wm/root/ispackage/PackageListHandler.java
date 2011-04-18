package com.gni.frmk.tools.addon.handler.wm.root.ispackage;

import com.gni.frmk.tools.addon.action.wm.root.ispackage.PackageList;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.IntegrationServerPackage;
import com.gni.frmk.tools.addon.model.component.IntegrationServerPackage.IntegrationServerPackageBuilder;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.result.SetResult;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

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
            Map<String, IntegrationServerPackage> values = Maps.newHashMap();
            for (IntegrationServerPackage value : action.getCollection()) {
                values.put(value.getComponentId().asString(), value);
            }
            IData[] packagesDatas = IDataUtil.getIDataArray(cur, "packages");
            if (packagesDatas != null) {
                for (IData packageData : packagesDatas) {
                    IDataCursor curLoop = packageData.getCursor();
                    try {
                        String pkgName = IDataUtil.getString(curLoop, "name");
                        IntegrationServerPackage value = values.get(pkgName);
                        if (value == null && action.isUpdate()) {
                            continue;
                        }
                        IntegrationServerPackageBuilder builder = IntegrationServerPackage.builder();
                        if (action.isUpdate()) {
                            builder.from(value);
                        } else {
                            builder.packageName(pkgName);
                        }
                        EnableStatus enabled = EnableStatus.fromBooleanString(IDataUtil.getString(curLoop, "enabled"));
                        value = builder.defineState(new EnableState(enabled)).build();
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new SetResult<IntegrationServerPackage>(values.values());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(PackageList in) {
        return EMPTY_INPUT;
    }

}