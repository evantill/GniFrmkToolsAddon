package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.model.api.immutable.ReadableObject;
import com.gni.frmk.tools.addon.model.api.immutable.WritableObject;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration.MutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.component.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 18:22
 *
 * @author: e03229
 */
public interface ReadableConfiguration
        extends ReadableObject<ImmutableConfiguration, ImmutableConfiguration, MutableConfiguration>,
        ConfigurationVisited {

    @NotNull
    @Pattern(regexp = "\\w{1,32}")
    @XmlAttribute
    String getId();

    @NotNull
    @XmlAttribute
    String getName();

    @NotNull
    @XmlAttribute
    Date getCreation();

    @NotNull
    @XmlAttribute
    String getPackageName();

    @NotNull
    @XmlAttribute
    Date getModification();

    @Valid
    @XmlElement(name = "adapterConnectionConfiguration")
    @XmlElementWrapper
    List<AdapterConnectionConfiguration> getAdapterConnectionConfigurations();

    @Valid
    @XmlElement(name = "adapterListenerConfiguration")
    @XmlElementWrapper
    List<AdapterListenerConfiguration> getAdapterListenerConfigurations();

    @Valid
    @XmlElement(name = "adapterNotificationConfiguration")
    @XmlElementWrapper
    List<AdapterNotificationConfiguration> getAdapterNotificationConfigurations();

    @Valid
    @XmlElement(name = "integrationServerPackageConfiguration")
    @XmlElementWrapper
    List<IntegrationServerPackageConfiguration> getIntegrationServerPackageConfigurations();

    @Valid
    @XmlElement(name = "jmsAliasConfiguration")
    @XmlElementWrapper
    List<JmsAliasConfiguration> getJmsAliasConfigurations();

    @Valid
    @XmlElement(name = "jmsTriggerConfiguration")
    @XmlElementWrapper
    List<JmsTriggerConfiguration> getJmsTriggerConfigurations();

    @Valid
    @XmlElement(name = "nativeTriggerConfiguration")
    @XmlElementWrapper
    List<NativeTriggerConfiguration> getNativeTriggerConfigurations();

    @Valid
    @XmlElement(name = "portConfiguration")
    @XmlElementWrapper
    List<PortConfiguration> getPortConfigurations();

    @Valid
    @XmlElement(name = "schedulerConfiguration")
    @XmlElementWrapper
    List<SchedulerConfiguration> getSchedulerConfigurations();

    interface WritableConfiguration
            extends ReadableConfiguration,
            WritableObject<ImmutableConfiguration, ImmutableConfiguration, MutableConfiguration> {
        void setId(String id);

        void setName(String name);

        void setCreation(Date date);

        void setPackageName(String name);

        void setModification(Date date);

        void setAdapterConnectionConfiguration(List<AdapterConnectionConfiguration> elements);

        void setAdapterListenerConfiguration(List<AdapterListenerConfiguration> elements);

        void setAdapterNotificationConfiguration(List<AdapterNotificationConfiguration> elements);

        void setIntegrationServerPackageConfiguration(List<IntegrationServerPackageConfiguration> elements);

        void setJmsAliasConfiguration(List<JmsAliasConfiguration> elements);

        void setJmsTriggerConfiguration(List<JmsTriggerConfiguration> elements);

        void setNativeTriggerConfiguration(List<NativeTriggerConfiguration> elements);

        void setPortConfiguration(List<PortConfiguration> elements);

        void setSchedulerConfiguration(List<SchedulerConfiguration> elements);

        void addAdapterConnectionConfiguration(AdapterConnectionConfiguration element);

        void addAdapterListenerConfiguration(AdapterListenerConfiguration element);

        void addAdapterNotificationConfiguration(AdapterNotificationConfiguration element);

        void addIntegrationServerPackageConfiguration(IntegrationServerPackageConfiguration element);

        void addJmsAliasConfiguration(JmsAliasConfiguration element);

        void addJmsTriggerConfiguration(JmsTriggerConfiguration element);

        void addNativeTriggerConfiguration(NativeTriggerConfiguration element);

        void addPortConfiguration(PortConfiguration element);

        void addSchedulerConfiguration(SchedulerConfiguration element);
    }
}
