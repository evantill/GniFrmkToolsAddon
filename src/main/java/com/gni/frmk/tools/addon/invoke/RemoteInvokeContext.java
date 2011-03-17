package com.gni.frmk.tools.addon.invoke;

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
public class RemoteInvokeContext extends InvokeContext {

    private final Context context = new Context();

    private final String server;
    private final String userName;
    private final String password;

    private final boolean autoConnection;

    public RemoteInvokeContext(String server, String userName, String password, boolean autoConnection) {
        this.server = server;
        this.userName = userName;
        this.password = password;
        this.autoConnection = autoConnection;
    }

    public void connect() throws InvokeException {
        try {
            if (!context.isConnected()) {
                context.connect(server, userName, password);
            }
        } catch (ServiceException e) {
            throw new InvokeException(this, e);
        }
    }

    public void disconnect() {
        if (context.isConnected()) {
            context.disconnect();
        }
    }

    @Override
    public IData invoke(NSName service, IData input) throws InvokeException {
        boolean autoConnected = false;
        if (autoConnection && !context.isConnected()) {
            connect();
            autoConnected = true;
        }
        try {
            return super.invoke(service, input);
        } catch (InvokeException e) {
            throw new ServiceInvokeException(this, service, input, e.getCause());
        } finally {
            if (autoConnected) {
                disconnect();
            }
        }
    }

    @Override
    public boolean canInvoke(NSName service) throws ServiceNotAvaibleException {
        return context.getNamespace().nodeExists(service);
    }
}
