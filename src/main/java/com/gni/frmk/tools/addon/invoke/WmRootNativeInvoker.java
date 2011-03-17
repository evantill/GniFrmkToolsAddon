package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 13 oct. 2010
 * Time: 18:57:23
 * To change this template use File | Settings | File Templates.
 */
public class WmRootNativeInvoker {

    private final IntegrationServerUtil isUtil;

    public WmRootNativeInvoker(IntegrationServerUtil util, ServiceInvokerFactory factory) {
        isUtil = util;




    }

    public boolean isEnabled() {
        return isUtil.isPackageEnabled("WmRoot");
    }

    private class ServiceInvoker {
        public static final Object EMPTY_DATA = "";
    }

    private class ServiceInvokerFactory {
        public ServiceInvokerFactory createServiceInvokerBuilder(String s) {
            return null;
        }

        public ServiceInvokerFactory defineOutput(String... triggers) {
            return null;  //To change body of created methods use File | Settings | File Templates.
        }

        public ServiceInvoker build() {
                return null;  //To change body of created methods use File | Settings | File Templates.
        }
    }

    private class ServiceException extends Exception {
    }
}
