package com.gni.frmk.tools.addon.data2;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Sets;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 09:40
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractComponent implements Component {

    @XmlElement
    private final ComponentType type;
    @XmlElement
    private final ComponentId id;
    @XmlElement(name = "detail")
    @XmlElementWrapper
    private final SortedSet<ComponentDetail> details;

    public abstract ComponentState getState();

    protected AbstractComponent(Builder<?, ?> builder) {
        type = builder.type;
        id = builder.id;
        details = builder.details;
    }

    /**
     * empty constructor for jaxb.
     */
    protected AbstractComponent() {
        type = null;
        id = null;
        details = null;
    }

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

    public static abstract class Builder<S extends ComponentState, T extends Builder<S, T>> {
        private ComponentType type;
        private ComponentId id;
        private S state;
        private TreeSet<ComponentDetail> details = Sets.newTreeSet();

        protected abstract T self();

        public S getState() {
            return state;
        }

        public T state(S value) {
            state = checkNotNull(value, "state required");
            return self();
        }

        public T type(ComponentType value) {
            type = checkNotNull(value, "type required");
            return self();
        }

        public T id(ComponentId value) {
            id = checkNotNull(value, "id required");
            return self();
        }

        public T id(String id) {
            return id(new ComponentId(id));
        }

        public T addDetail(ComponentDetail detail) {
            details.add(detail);
            return self();
        }

        public T addDetail(String key, String value) {
            return addDetail(new ComponentDetail(key, value));
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
        return ComparisonChain.start()
                              .compare(getClass().getName(), component.getClass().getName())
                              .compare(getType(), component.getType())
                              .compare(getId(), component.getId())
                              .result();
    }
}
