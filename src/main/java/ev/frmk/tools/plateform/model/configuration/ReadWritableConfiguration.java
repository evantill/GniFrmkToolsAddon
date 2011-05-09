package ev.frmk.tools.plateform.model.configuration;

import com.gni.frmk.tools.addon.model.configuration.component.*;
import ev.frmk.tools.plateform.api.immutable.ReadWritableObject;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 13:33
 *
 * @author: e03229
 */
public interface ReadWritableConfiguration
        extends ReadableConfiguration,
        ReadWritableObject<Configuration, MutableConfiguration> {
     void setId(String id);

        void setName(String name);

        void setCreation(Date date);

        void setPackageName(String name);

        void setModification(Date date);
/*
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
*/
}
