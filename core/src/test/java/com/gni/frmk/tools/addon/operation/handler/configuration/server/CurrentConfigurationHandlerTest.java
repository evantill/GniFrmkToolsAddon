package com.gni.frmk.tools.addon.operation.handler.configuration.server;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.action.configuration.server.CurrentConfiguration;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.dispatcher.ActionHandlerRegistry;
import com.gni.frmk.tools.addon.operation.dispatcher.SimpleDispatcher;
import com.gni.frmk.tools.addon.repository.ConfigurationSerializer;
import com.gni.frmk.tools.addon.util.FileResource;
import com.gni.frmk.tools.addon.util.NowTimestampRule;
import com.gni.frmk.tools.addon.util.WeldRunner;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/06/11
 * Time: 14:26
 *
 * @author: e03229
 */
@RunWith(WeldRunner.class)
public class CurrentConfigurationHandlerTest {

    @Inject
    ActionHandlerRegistry<InvokeContext> registry;

    @Inject
    ConfigurationSerializer serializer;

    @Rule
    public NowTimestampRule nowRule = new NowTimestampRule();

    @Rule
    public FileResource expectedXml = new FileResource(getClass(), String.format("%s.xml", getClass().getSimpleName()));

    @Test
    public void testExecute() throws Exception {

        final ServiceContext serviceContext = Mockito.mock(ServiceContext.class);
        Dispatcher dispatcher = new SimpleDispatcher<InvokeContext>(registry) {
            @Override
            public InvokeContext createContext() {
                return new InvokeContext(this, serviceContext);
            }
        };
        CurrentConfiguration action = new CurrentConfiguration(nowRule.now());
        Configuration result = dispatcher.execute(action).getValue();
        StringWriter out = new StringWriter();
        serializer.saveConfiguration(result, out);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }
}

