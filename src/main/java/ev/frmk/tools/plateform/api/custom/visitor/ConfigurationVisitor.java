package ev.frmk.tools.plateform.api.custom.visitor;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import ev.frmk.tools.plateform.api.visitor.Visitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:39
 *
 * @author: e03229
 */
public interface ConfigurationVisitor
        extends Visitor<ConfigurationVisitor, ConfigurationVisited> {

    void visitComponent(AdapterConnection visited);

    void visitComponent(AdapterListener visited);

    void visitComponent(AdapterNotification visited);

    void visitComponent(ImmutablePort visited);

    void visitComponent(ImmutableScheduler visited);

    void visitComponent(ImmutableNativeTrigger visited);

    void visitComponent(ImmutableJmsTrigger visited);

    void visitComponent(ImmutableJmsAlias visited);

    void visitComponent(ImmutableIntegrationServerPackage visited);

    void visitConfiguration(ImmutableConfiguration configuration);

    void visitComponentConfiguration(AdapterConnectionConfiguration visited);

    void visitComponentConfiguration(AdapterListenerConfiguration visited);

    void visitComponentConfiguration(AdapterNotificationConfiguration visited);

    void visitComponentConfiguration(PortConfiguration visited);

    void visitComponentConfiguration(SchedulerConfiguration visited);

    void visitComponentConfiguration(NativeTriggerConfiguration visited);

    void visitComponentConfiguration(JmsTriggerConfiguration visited);

    void visitComponentConfiguration(JmsAliasConfiguration visited);

    void visitComponentConfiguration(IntegrationServerPackageConfiguration visited);
}
