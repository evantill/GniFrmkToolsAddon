package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 25/10/10
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public class WmRootInvoker {

    private ServiceInvoker getAllServiceStats;
    private IntegrationServerUtil isUtil;

    public WmRootInvoker(IntegrationServerUtil util, ServiceInvokerFactory factory) {
        isUtil = util;
        getAllServiceStats = factory.createServiceInvokerBuilder("wm.server.query:getAllServiceStats")
                .defineOutput("SvcStats")
                .build();
    }

    public Set<String> getRunningServices() throws ServiceException {
        Map<String, ?> out = getAllServiceStats.invoke(ServiceInvoker.EMPTY_DATA);
        Set<String> result = new TreeSet<String>();
        if (out.containsKey("SvcStats")) {
            IData[] SvcStats = (IData[]) out.get("SvcStats");
            if (SvcStats != null) {
                for (IData SvcStat : SvcStats) {
                    IDataCursor curSvcStat = SvcStat.getCursor();
                    try {
                        String sRunning = IDataUtil.getString(curSvcStat, "sRunning");
                        String ifc = IDataUtil.getString(curSvcStat, "ifc");
                        String svc = IDataUtil.getString(curSvcStat, "svc");
                        String name = String.format("%s:%s", ifc, svc);
                        if (!IntegrationServerUtil.EMPTY_VALUE_NBSP.equals(sRunning)) {
                            if (name.startsWith(isUtil.getToolsPackageName())) {
                                continue;
                            }
                            result.add(name);
                        }
                    } finally {
                        curSvcStat.destroy();
                    }
                }
            }
        }
        return result;
    }

    public void waitServicesEnd(long maxSecondsToWait) throws ServiceException {
        long timeoutTime = System.currentTimeMillis() + (maxSecondsToWait * 1000);
        long nbrRunning = getNbrRunningServices();
        for (long currentTime = System.currentTimeMillis(); currentTime < timeoutTime; currentTime = System.currentTimeMillis()) {
            nbrRunning = getNbrRunningServices();
            if (nbrRunning > 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignore) {
                    ignore.printStackTrace();
                }
            } else {
                break;
            }
        }
        if (nbrRunning > 0) {
            throw new ServiceException("waitServicesEnd",
                    String.format("wait timeout : still %s service(s) running", nbrRunning));
        }
    }

    public long getNbrRunningServices() throws ServiceException {
        return getRunningServices().size();
    }

    /**
     * WmRoot is always enabled
     *
     * @return true
     */
    public boolean isEnabled() {
        return true;
    }
}
