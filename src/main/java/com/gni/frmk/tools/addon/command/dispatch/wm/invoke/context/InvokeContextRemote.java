package com.gni.frmk.tools.addon.command.dispatch.wm.invoke.context;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.DispatchException;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.ServiceInvokeException;
import com.wm.app.b2b.client.Context;
import com.wm.app.b2b.client.ServiceException;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:39
 *
 * @author: e03229
 */
public class InvokeContextRemote implements InvokeContext {

    private final Context context = new Context();

    private final String server;
    private final String userName;
    private final String password;

    private final boolean autoConnection;

    public InvokeContextRemote(String server, String userName, String password, boolean autoConnection) {
        this.server = server;
        this.userName = userName;
        this.password = password;
        this.autoConnection = autoConnection;
    }

    public void connect() throws DispatchException {
        try {
            if (!context.isConnected()) {
                context.connect(server, userName, password);
            }
        } catch (ServiceException e) {
            throw new DispatchException(e);
        }
    }

    public void disconnect() {
        if (context.isConnected()) {
            context.disconnect();
            context.cleanUpConnection();
        }
    }

    @Override
    public IData invoke(Action<?> action, NSName service, IData input) throws ServiceInvokeException {
        boolean autoConnected = false;
        if (autoConnection && !context.isConnected()) {
            try {
                connect();
                autoConnected = true;
            } catch (DispatchException e) {
                throw new ServiceInvokeException(this, action, service, input, e);
            }
        }
        try {
            return context.invoke(service, input);
        } catch (ServiceException e) {
            throw new ServiceInvokeException(this, action, service, input, e);
        } finally {
            if (autoConnected) {
                disconnect();
            }
        }
    }

    @Override
    public boolean canInvoke(NSName service) {
        return context.getNamespace().nodeExists(service);
    }
}