package com.gni.frmk.tools.addon.api.visitor.configuration.strategy;

import com.gni.frmk.tools.addon.api.visitor.configuration.ConfigurationStagingVisitor;
import com.gni.frmk.tools.addon.api.visitor.configuration.ConfigurationStagingVisitor.Stage;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Sets;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.SortedSet;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 11:07
 * Close sequence is :
 * STAGE1:
 * close inputs. (respect closeSequenceOrder attribute)
 * STAGE2:
 * outputs. (respect closeSequenceOrder attribute)
 *
 * @author: e03229
 */
public class ConfigurationVisitorCloseStrategy<V extends ConfigurationStagingVisitor>
        implements ConfigurationVisitorStrategy<V> {

    private static final Comparator<ComponentType<?, ?, ?, ?, ?>> CLOSE_SEQUENCE_ORDER = new Comparator<ComponentType<?, ?, ?, ?, ?>>() {
        @Override
        public int compare(ComponentType<?, ?, ?, ?, ?> o1, ComponentType<?, ?, ?, ?, ?> o2) {
            return ComparisonChain.start()
                                  .compare(o1.getCloseSequenceOrder(), o2.getCloseSequenceOrder())
                                  .compare(o1.getClass().getName(), o2.getClass().getName())
                                  .result();
        }
    };

    private final SortedSet<ComponentType<?, ?, ?, ?, ?>> closeInputSequence = Sets.newTreeSet(CLOSE_SEQUENCE_ORDER);
    private final SortedSet<ComponentType<?, ?, ?, ?, ?>> closeOutputSequence = Sets.newTreeSet(CLOSE_SEQUENCE_ORDER);

    @Inject
    public ConfigurationVisitorCloseStrategy(@Any Instance<ComponentType<?, ?, ?, ?, ?>> types) {
        init(types);
    }

    public ConfigurationVisitorCloseStrategy(Iterable<ComponentType<?, ?, ?, ?, ?>> types) {
        init(types);
    }

    private void init(Iterable<ComponentType<?, ?, ?, ?, ?>> types) {
        for (ComponentType<?, ?, ?, ?, ?> type : types) {
            if (type.isInput()) {
                closeInputSequence.add(type);
            } else if (type.isOutput()) {
                closeOutputSequence.add(type);
            }
        }
    }

    @Override
    public <T extends V> void visitConfiguration(T visitor, Configuration<?> visited) {
        visitor.startStage(Stage.PHASE1);
        for (ComponentType<?, ?, ?, ?, ?> type : closeInputSequence) {
            for (ComponentConfiguration<?,?, ?, ?, ?> configuration : visited.getComponentConfigurationsByType(type)) {
                visitor.visitComponentConfiguration(configuration);
            }
        }
        visitor.endStage(Stage.PHASE1);
        visitor.startStage(Stage.PHASE2);
        for (ComponentType<?, ?, ?, ?, ?> type : closeOutputSequence) {
            for (ComponentConfiguration<?,?, ?, ?, ?> configuration : visited.getComponentConfigurationsByType(type)) {
                visitor.visitComponentConfiguration(configuration);
            }
        }
        visitor.endStage(Stage.PHASE2);
    }

}
