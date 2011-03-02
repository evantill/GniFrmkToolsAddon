package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.ConfigurationElement;
import com.gni.frmk.tools.addon.data.component.Component;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class TraceConfigurationVisitorAdapter extends ConfigurationVisitorAdapter {
    private final Set<Component> startedComponents = Sets.newHashSet();
    private final Set<Component> completedComponents = Sets.newHashSet();
    private final Set<Component> failedComponents = Sets.newHashSet();
    private final Map<Component, Throwable> failedExceptions = Maps.newHashMap();

    public TraceConfigurationVisitorAdapter(ConfigurationVisitorRaisingException decorated) {
        super(decorated);
    }

    @Override
    protected void statusStarted(Component visited) {
        startedComponents.add(visited);
    }

    @Override
    protected void statusCompleted(Component visited) {
        completedComponents.add(visited);
    }

    @Override
    protected void statusFailed(Component visited, Throwable t) {
        failedComponents.add(visited);
        //noinspection ThrowableResultOfMethodCallIgnored
        failedExceptions.put(visited, t);
    }

    public void clear() {
        startedComponents.clear();
        completedComponents.clear();
        failedComponents.clear();
        failedExceptions.clear();
    }

    public Set<Component> getStartedComponents() {
        return unmodifiableSet(startedComponents);
    }

    public Set<Component> getCompletedComponents() {
        return unmodifiableSet(completedComponents);
    }

    public Set<Component> getFailedComponents() {
        return unmodifiableSet(failedComponents);
    }

    public Map<Component, Throwable> getFailedExceptions() {
        return unmodifiableMap(failedExceptions);
    }
}
