package com.gni.frmk.tools.addon.invoker.context;

import com.gni.frmk.tools.addon.invoker.api.ServiceContextException;
import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.wm.app.b2b.client.Context;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:44
 *
 * @author: e03229
 */
public class RemoteContext implements ServiceContext {

    private final Context ctx;

    public RemoteContext(RemoteContext.Builder builder) {
        ctx = new Context();
        ctx.setAuthentication(builder.user, builder.password);
        try {
            ctx.connect(builder.url);
        } catch (com.wm.app.b2b.client.ServiceException cause) {
            throw new ServiceContextException(this, "remote connection error", cause);
        }
    }

    public void dispose() {
        ctx.disconnect();
    }

    @Override
    public IData invoke(NSName serviceName, IData in) throws ServiceContextException {
        try {
            return ctx.invoke(serviceName, in);
        } catch (com.wm.app.b2b.client.ServiceException cause) {
            throw new ServiceContextException(this, cause);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String user;
        private String password;
        private String url;

        public Builder credential(String user, String password) {
            this.user = checkNotNull(user);
            this.password = checkNotNull(password);
            return this;
        }

        public Builder url(String url) {
            this.url = checkNotNull(url);
            return this;
        }

        public Builder url(String host, int port) {
            return url(format("%s:%d", checkNotNull(host), port));
        }

        public RemoteContext build() {
            return new RemoteContext(this);
        }

    }
}
