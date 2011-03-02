package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.wm.lang.ns.NSName;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 25/10/10
 * Time: 19:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class ServiceInvokerBuilder {
    public static final Set<String> EMPTY_SIGNATURE = new TreeSet<String>();

    NSName name;
    Set<String> inputSignature;
    Set<String> outputSignature;
    String server;
    String userName;
    String password;

    ServiceInvokerBuilder(IntegrationServerUtil util, String serviceName) {
        name = NSName.create(serviceName);
        inputSignature = EMPTY_SIGNATURE;
        outputSignature = EMPTY_SIGNATURE;
    }

    public ServiceInvokerBuilder defineRemoteServer(String server, String userName, String password) {
        this.server = server;
        this.userName = userName;
        this.password = password;
        return this;
    }

    public ServiceInvokerBuilder defineInput(String... inputList) {
        inputSignature = new TreeSet<String>(Arrays.asList(inputList));
        return this;
    }

    public ServiceInvokerBuilder defineOutput(String... outputList) {
        outputSignature = new TreeSet<String>(Arrays.asList(outputList));
        return this;
    }

    public abstract ServiceInvoker build();

}
