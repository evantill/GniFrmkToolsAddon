package com.gni.frmk.tools.addon.data2;

import com.gni.frmk.tools.addon.data2.ComponentState.EnableStatus;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Sets;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 09:32
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public abstract class Component implements Comparable<Component>{

    @XmlElement
    private final ComponentType type;

    @XmlElement
    private final ComponentId id;

    @XmlElement
    @XmlElementWrapper
    private final SortedSet<ComponentDetail> details;

    @XmlElement
    private final ComponentState state;

    protected Component(Builder<?> builder) {
        type = builder.type;
        id = builder.id;
        details = builder.details;
        state = builder.state;
    }

    /**
     * empty constructor for jaxb.
     */
    protected Component() {
        type = null;
        id = null;
        details = null;
        state = null;
    }

   abstract  void accept(ComponentVisitor visitor) ;

    public ComponentType getType() {
        return type;
    }

    public ComponentId getId() {
        return id;
    }

    public Set<ComponentDetail> getDetails() {
        return Collections.unmodifiableSet(details);
    }

    public void addDetail(final ComponentDetail detail) {
        details.add(Preconditions.checkNotNull(detail));
    }

    public ComponentDetail findDetail(final String key) {
        final String searchKey = Preconditions.checkNotNull(key, "findDetail need a key");
        Iterator<ComponentDetail> iterator = Collections2.filter(getDetails(), new Predicate<ComponentDetail>() {
            public boolean apply(ComponentDetail input) {
                return searchKey.equals(input.getKey());
            }
        }).iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    public ComponentDetail findRequiredDetail(String key) {
        return Preconditions.checkNotNull(findDetail(key), "missing required detail %s", key);
    }

    public ComponentState getState() {
        return state;
    }

    public static abstract class Builder<T extends Builder<T>> {
        private ComponentType type;
        private ComponentId id;
        private TreeSet<ComponentDetail> details = Sets.newTreeSet();
        private ComponentState state;

        protected abstract T self();

        public T type(ComponentType value) {
            type = value;
            return self();
        }

        public T id(ComponentId value) {
            id = value;
            return self();
        }

        public T id(String id) {
            id(new ComponentId(id));
            return self();
        }

        public T addDetail(ComponentDetail detail) {
            details.add(detail);
            return self();
        }

        public T addDetail(String key, String value) {
            addDetail(new ComponentDetail(key, value));
            return self();
        }

        public T state(ComponentState value) {
            state = value;
            return self();
        }

        public T enabled(EnableStatus value) {
            state = new ComponentState(value);
            return self();
        }

        public T check() throws CheckException {
            try {
                checkNotNull(type, "type is required");
                checkNotNull(id, "id is required");
                checkNotNull(state, "state is required");
                return self();
            } catch (NullPointerException npex) {
                throw new CheckException(npex);
            }
        }

        public abstract Component build() throws BuildException;
    }

    public static class BuildException extends RuntimeException {

        public BuildException(Throwable cause) {
            super(cause);
        }
    }

    public static class CheckException extends BuildException {

        public CheckException(Throwable cause) {
            super(cause);
        }
    }

    public int compareTo(Component component) {
        return ComparisonChain
                .start()
                .compare(getType(),component.getType())
                .compare(getId(),component.getId())
                .result();
    }
}
