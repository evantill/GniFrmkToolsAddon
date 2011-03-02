package com.gni.frmk.tools.addon.data.scheduler;

import com.gni.frmk.tools.addon.data.component.Component;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 18:28:15
 * To change this template use File | Settings | File Templates.
 */
@XmlType
public class Scheduler extends Component<SchedulerInfo, SchedulerState> {

    public Scheduler(SchedulerBuilder builder) {
        super(builder);
    }

    /**
     * Empty constructor only for jaxb.
     */
    private Scheduler() {
    }

    public void accept(ConfigurationVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T extends Component<SchedulerInfo, SchedulerState>> int compareStatusTo(T that) {
        return ComparisonChain.start().compare(compareTo(that), 0).compare(getState(), that.getState()).result();
    }
}
