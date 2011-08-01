package com.gni.frmk.tools.addon.tdd.impl.component.integrationserver;

import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.google.common.collect.ComparisonChain;

import java.net.InetAddress;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 15:47
 *
 * @author: e03229
 */
public class IntegrationServerId implements ComponentId<IntegrationServerId> {

    private final String name;
    private final InetAddress host;
    private final int primaryPort;

    public IntegrationServerId(String name, InetAddress host, int primaryPort) {
        this.name = checkNotNull(name);
        this.host = checkNotNull(host);
        this.primaryPort = primaryPort;
    }

    public String getName() {
        return name;
    }

    public InetAddress getHost() {
        return host;
    }

    public int getPrimaryPort() {
        return primaryPort;
    }

    @Override
    public int compareTo(IntegrationServerId o) {
        return ComparisonChain.start()
                              .compare(getName(), o.getName())
                              .compare(getHost().getHostAddress(), o.getHost().getHostAddress())
                              .compare(getPrimaryPort(), o.getPrimaryPort())
                              .result();
    }
}
