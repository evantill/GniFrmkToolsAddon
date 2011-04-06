package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.api.Component;
import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.AdapterListener;
import com.gni.frmk.tools.addon.model.component.Port;
import com.gni.frmk.tools.addon.visitor.api.ConfigurationVisitorAdapter;
import com.gni.frmk.tools.addon.visitor.api.ConfigurationVisitorRaisingException;
import com.gni.frmk.tools.addon.visitor.api.ConfigurationVisitorRaisingException.ConfigurationVisitorException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationVisitorAdapterTest {

    public class SimpleConfigurationVisitorAdapter extends ConfigurationVisitorAdapter {
        final List<Component> startedList = Lists.newArrayList();
        final List<Component> okList = Lists.newArrayList();
        final List<Component> koList = Lists.newArrayList();
        final Map<Component, Throwable> koExceptions = Maps.newHashMap();

        public SimpleConfigurationVisitorAdapter(ConfigurationVisitorRaisingException decorated) {
            super(decorated);
        }

        @Override
        protected void statusCompleted(Component visited) {
            okList.add(visited);
        }

        @Override
        protected void statusStarted(Component visited) {
            startedList.add(visited);
        }

        /** @noinspection ThrowableResultOfMethodCallIgnored*/
        @Override
        protected void statusFailed(Component visited, Throwable t) {
            koList.add(visited);
            koExceptions.put(visited, t);
        }

        public List<Component> getStartedList() {
            return unmodifiableList(startedList);
        }

        public List<Component> getOkList() {
            return unmodifiableList(okList);
        }

        public List<Component> getKoList() {
            return unmodifiableList(koList);
        }

        public Map<Component, Throwable> getKoExceptions() {
            return unmodifiableMap(koExceptions);
        }

        public void clear() {
            okList.clear();
            koList.clear();
            startedList.clear();
        }
    }

    @Test
    public void testExceptionAdapterOnException() throws Throwable {

        AdapterConnection con1 = Mockito.mock(AdapterConnection.class);
        ConfigurationVisitorException conEx1 = new ConfigurationVisitorException(con1, "connection error");
        Port port1 = Mockito.mock(Port.class);
        ConfigurationVisitorException portEx1 = new ConfigurationVisitorException(port1, "port error");
        AdapterListener l1 = Mockito.mock(AdapterListener.class);
        AdapterListener l2 = Mockito.mock(AdapterListener.class);
        AdapterListener l3 = Mockito.mock(AdapterListener.class);

        ConfigurationVisitorRaisingException badVisitor = Mockito.mock(ConfigurationVisitorRaisingException.class);
        Mockito.doThrow(conEx1).when(badVisitor).visit(con1);
        Mockito.doThrow(portEx1).when(badVisitor).visit(port1);
        SimpleConfigurationVisitorAdapter adapter = new SimpleConfigurationVisitorAdapter(badVisitor);

        adapter.clear();
        adapter.visit(l1);
        adapter.visit(con1);
        adapter.visit(l2);
        adapter.visit(port1);
        adapter.visit(l3);

        //noinspection ThrowableResultOfMethodCallIgnored
        Assert.assertEquals("connection exception not expected", conEx1, adapter.getKoExceptions().get(con1));
        //noinspection ThrowableResultOfMethodCallIgnored
        Assert.assertEquals("port exception not expected", portEx1, adapter.getKoExceptions().get(port1));
        Assert.assertEquals("error list size", 2, adapter.getKoList().size());
        Assert.assertEquals("success list size", 3, adapter.getOkList().size());
        Assert.assertEquals("started list size", 5, adapter.getStartedList().size());
    }

}
