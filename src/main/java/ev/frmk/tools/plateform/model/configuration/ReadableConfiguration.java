package ev.frmk.tools.plateform.model.configuration;

import com.gni.frmk.tools.addon.model.configuration.component.*;
import ev.frmk.tools.plateform.api.immutable.ReadableObject;

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
 * Date: 27/04/11
 * Time: 13:33
 *
 * @author: e03229
 */
public interface ReadableConfiguration
        extends ReadableObject<Configuration,MutableConfiguration> {

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
/*
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
*/
}
