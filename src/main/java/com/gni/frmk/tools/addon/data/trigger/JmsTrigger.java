package com.gni.frmk.tools.addon.data.trigger;

import com.gni.frmk.tools.addon.data.ConfigurationElement;
import com.gni.frmk.tools.addon.data.StatusComparable;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 15:17:37
 * To change this template use File | Settings | File Templates.
 */
public class JmsTrigger extends Trigger implements Comparable<JmsTrigger>, StatusComparable<JmsTrigger>, ConfigurationElement<JmsTrigger> {

    private State executionState;

    public JmsTrigger(String name, Status status, State executionState) {
        super(name, status, Type.JMS);
        this.executionState = executionState;
    }

    public JmsTrigger(TriggerBuilder builder) {
        super(builder);
        executionState = builder.jmsExecutionState;
    }

    /**
     * Empty constructor only for jaxb.
     */
    private JmsTrigger() {
    }

    public void accept(ConfigurationVisitor visitor) {
        visitor.visit(this);
    }

    public State getExecutionState() {
        return executionState;
    }

    public void setExecutionState(State executionState) {
        this.executionState = executionState;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", getName()).add("type", getType()).add("status", getStatus()).add("executionState", getExecutionState()).toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int compareTo(JmsTrigger that) {
        return ComparisonChain.start().compare(getType(), that.getType()).compare(getName(), that.getName()).result();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Note: this class has a natural ordering that is inconsistent with equals.
     *
     * @param that element to compare
     * @return 0 if same status
     */
    public int compareStatusTo(JmsTrigger that) {
        return ComparisonChain.start().compare(compareTo(that), 0).compare(getStatus(), that.getStatus()).compare(getExecutionState(), that.getExecutionState()).result();
    }

}
