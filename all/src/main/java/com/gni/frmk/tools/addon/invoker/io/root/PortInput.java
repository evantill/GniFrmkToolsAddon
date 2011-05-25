package com.gni.frmk.tools.addon.invoker.io.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInput;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class PortInput implements ServiceInput {
    private final String key;
    private final String packageName;

    private PortInput(String key, String packageName) {
        this.key = key;
        this.packageName = packageName;
    }

    public String getKey() {
        return key;
    }

    public String getPackageName() {
        return packageName;
    }

    public static PortInput newInstance(String key, String packageName) {
        return new PortInput(checkNotNull(key), checkNotNull(packageName));
    }
}
