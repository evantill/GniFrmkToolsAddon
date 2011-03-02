package com.gni.frmk.tools.addon.data2;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Configuration {

    @XmlAttribute(required = true)
    private final String name;

    @XmlAttribute(required = true)
    private final Date creation;

    @XmlAttribute
    private Date modification;

    @XmlElement(name = "adapterConnection")
    @XmlElementWrapper(name = "adapterConnectionList")
    private Set<AdapterConnection> adapterConnectionList = new TreeSet<AdapterConnection>();

    @XmlElement
    @XmlElementWrapper
    private Set<AdapterNotification> adapterNotificationList = new TreeSet<AdapterNotification>();

    @XmlElement
    @XmlElementWrapper
    private Set<AdapterListener> adapterListenerList = new TreeSet<AdapterListener>();

    @XmlElement
    @XmlElementWrapper
    private Set<Port> portList = new TreeSet<Port>();

    @XmlElement
    @XmlElementWrapper
    private Set<Scheduler> schedulerList = new TreeSet<Scheduler>();

    @XmlElement
    @XmlElementWrapper
    private Set<JmsTrigger> jmsTriggerList = new TreeSet<JmsTrigger>();

    @XmlElement
    @XmlElementWrapper
    private Set<NativeTrigger> nativeTriggerList = new TreeSet<NativeTrigger>();

    @XmlElement
    @XmlElementWrapper
    private Set<JmsAlias> jmsAliasList = new TreeSet<JmsAlias>();

    private Configuration(String name, Date creation) {
        this.name = name;
        this.creation = creation;
    }

    public Configuration(String name) {
        this.name = name;
        creation = new Date();
    }

    public void updateModificationDate() {
        modification = new Date();
    }

    /**
     * Empty constructor only used for jaxb.
     */
    private Configuration() {
        name = null;
        creation = null;
    }

    public void updateConfiguration(Configuration newConf) {
        String newName = newConf.getName();
        String myName = getName();
        Date newCreationDate = newConf.getCreation();
        Date newModificationDate = newConf.getModification();
        if (!myName.equals(newName)) {
            throw new IllegalStateException(String.format("configuration %s and %s have not the same name", newName, myName));
        }
        if (!creation.before(newCreationDate)) {
            throw new IllegalStateException(String.format("configuration %s must be created after current configuration", newName));
        }
        if (!modification.before(newModificationDate)) {
            throw new IllegalStateException(String.format("configuration %s must be modified after current configuration", newName));
        }
        setModification(newModificationDate);
        //update content
        clear();
        adapterConnectionList.addAll(newConf.getAdapterConnectionList());
        adapterNotificationList.addAll(newConf.getAdapterNotificationList());
        adapterListenerList.addAll(newConf.getAdapterListenerList());
        portList.addAll(newConf.getPortList());
        schedulerList.addAll(newConf.getSchedulerList());
        jmsTriggerList.addAll(newConf.getJmsTriggerList());
        nativeTriggerList.addAll(newConf.getNativeTriggerList());
        jmsAliasList.addAll(newConf.getJmsAliasList());
    }

    public void clear() {
        adapterConnectionList.clear();
        adapterNotificationList.clear();
        adapterListenerList.clear();
        portList.clear();
        schedulerList.clear();
        jmsTriggerList.clear();
        nativeTriggerList.clear();
        jmsAliasList.clear();
    }

    public Set<AdapterConnection> getAdapterConnectionList() {
        return adapterConnectionList;
    }

    public void setAdapterConnectionList(Set<AdapterConnection> adapterConnectionList) {
        this.adapterConnectionList = adapterConnectionList;
    }

    public Set<AdapterNotification> getAdapterNotificationList() {
        return adapterNotificationList;
    }

    public void setAdapterNotificationList(Set<AdapterNotification> adapterNotificationList) {
        this.adapterNotificationList = adapterNotificationList;
    }

    public Set<AdapterListener> getAdapterListenerList() {
        return adapterListenerList;
    }

    public void setAdapterListenerList(Set<AdapterListener> adapterListenerList) {
        this.adapterListenerList = adapterListenerList;
    }

    public Set<Port> getPortList() {
        return portList;
    }

    public void setPortList(Set<Port> portList) {
        this.portList = portList;
    }

    public Set<Scheduler> getSchedulerList() {
        return schedulerList;
    }

    public void setSchedulerList(Set<Scheduler> schedulerList) {
        this.schedulerList = schedulerList;
    }

    public Set<JmsTrigger> getJmsTriggerList() {
        return jmsTriggerList;
    }

    public void setJmsTriggerList(Set<JmsTrigger> jmsTriggerList) {
        this.jmsTriggerList = jmsTriggerList;
    }

    public Set<NativeTrigger> getNativeTriggerList() {
        return nativeTriggerList;
    }

    public void setNativeTriggerList(Set<NativeTrigger> nativeTriggerList) {
        this.nativeTriggerList = nativeTriggerList;
    }

    public Set<JmsAlias> getJmsAliasList() {
        return jmsAliasList;
    }

    public void setJmsAliasList(Set<JmsAlias> jnmAliasList) {
        this.jmsAliasList = jnmAliasList;
    }

    public String getName() {
        return name;
    }

    public Date getCreation() {
        return creation;
    }

    public Date getModification() {
        return modification;
    }

    public void setModification(Date modification) {
        this.modification = modification;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                      .add("name", getName())
                      .add("creation", getCreation())
                      .add("modification", getModification())
                      .add("adapterConnectionList", getAdapterConnectionList())
                      .add("adapterNotificationList", getAdapterNotificationList())
                      .add("adapterListenerList", getAdapterListenerList())
                      .add("portList", getPortList())
                      .add("schedulerList", getSchedulerList())
                      .add("jmsTriggerList", getJmsTriggerList())
                      .add("nativeTriggerList", getNativeTriggerList())
                      .add("jmsAliasList", getJmsAliasList())
                      .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Configuration that = (Configuration) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
