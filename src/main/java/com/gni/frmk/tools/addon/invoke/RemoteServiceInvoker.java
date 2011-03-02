package com.gni.frmk.tools.addon.invoke;

import com.wm.app.b2b.client.Context;
import com.wm.data.IData;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 25/10/10
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */
public class RemoteServiceInvoker extends ServiceInvoker {

    private final Context context = new Context();

    private final String server;
    private final String userName;
    private final String password;

    public RemoteServiceInvoker(ServiceInvokerBuilder builder) {
        super(builder);
        server = builder.server;
        userName = builder.userName;
        password = builder.password;
    }

    public void connect() throws ServiceException {
        try {
            context.connect(server, userName, password);
        } catch (com.wm.app.b2b.client.ServiceException e) {
            throw new ServiceException(getFullName(), e);
        }
    }

    public void disconnect() {
        context.disconnect();
    }

    @Override
    protected IData invokeHandler(IData input) throws Exception {
        boolean connectOnCall = !context.isConnected();
        if (connectOnCall) {
            connect();
        }
        try {
            return context.invoke(name, input);
        } finally {
            if (connectOnCall) {
                disconnect();
            }
        }
    }

    @Override
    public boolean exist() {
        boolean connectOnCall = !context.isConnected();
        if (connectOnCall) {
            try {
                connect();
            } catch (ServiceException e) {
                throw new IllegalStateException("connection error", e);
            }
        }
        try {
            return context.getNamespace().nodeExists(name);
        } finally {
            if (connectOnCall) {
                disconnect();
            }
        }
    }
}