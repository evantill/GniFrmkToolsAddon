package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.ImmutableScheduler.MutableScheduler;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 16:22
 *
 * @author: e03229
 */
public interface ReadableScheduler extends ReadablePackageAware<ImmutableScheduler, MutableScheduler, StringId, SchedulerState> {

    @NotNull
    @XmlElement
    String getOid();

    @NotNull
    @XmlElement
    String getSchedulerType();

    @NotNull
    @XmlElement
    String getName();

    @NotNull
    @XmlElement
    String getService();

    @XmlElement
    String getDescription();


    interface WritableScheduler extends ReadableScheduler, WritablePackageAware<ImmutableScheduler, MutableScheduler, StringId, SchedulerState> {
        void setOid(String value);

        void setSchedulerType(String value);

        void setName(String value);

        void setService(String value);

        void setDescription(String value);
    }
}
