package com.gni.frmk.tools.addon.data.scheduler;

import com.gni.frmk.tools.addon.data.component.ComponentInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 25/11/10
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerInfo extends ComponentInfo<SchedulerInfo> {

    private static final String DESCRIPTION_KEY = "description";
    private static final String SERVICE_KEY = "service";
    private static final String NAME_KEY = "name";
    private static final String TYPE_KEY = "type";

    public SchedulerInfo(String type, String name, String service, String description) {
        setType(type);
        setName(name);
        setService(service);
        setDescription(description);
    }

    public String getType() {
        return getRequiredInfo(TYPE_KEY);
    }

    public String getName() {
        return getRequiredInfo(NAME_KEY);
    }

    public String getDescription() {
        return getRequiredInfo(DESCRIPTION_KEY);
    }

    public String getService() {
        return getRequiredInfo(SERVICE_KEY);
    }

    public void setType(String type) {
        addInfo(TYPE_KEY, checkNotNull(type));
    }

    public void setName(String name) {
        addInfo(NAME_KEY, checkNotNull(name));
    }

    public void setDescription(String description) {
        addInfo(DESCRIPTION_KEY, checkNotNull(description));
    }

    public void setService(String service) {
        addInfo(SERVICE_KEY, checkNotNull(service));
    }

}
