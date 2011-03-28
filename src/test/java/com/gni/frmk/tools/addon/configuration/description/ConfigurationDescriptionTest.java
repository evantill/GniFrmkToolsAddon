package com.gni.frmk.tools.addon.configuration.description;

import com.gni.frmk.tools.addon.configuration.components.AbstractComponent.AbstractComponentDetail;
import com.gni.frmk.tools.addon.configuration.components.Component;
import com.gni.frmk.tools.addon.configuration.components.ComponentDetail;
import com.gni.frmk.tools.addon.configuration.components.ComponentId;
import com.gni.frmk.tools.addon.configuration.visitors.GenericComponentVisitor;
import com.google.common.collect.Lists;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.gni.frmk.tools.addon.configuration.components.ComponentType.ADAPTER_CONNECTION;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationDescriptionTest {

    private final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><configuration modification=\"2011-03-03T19:00:23.828+00:00\" creation=\"2011-03-03T19:00:23.828+00:00\" name=\"essai\"><components><component><type>ADAPTER_CONNECTION</type><id>comp1</id><informations><information key=\"info1\">value1</information><information key=\"info0\">value0</information><information key=\"info2\">value2</information></informations></component><component><type>ADAPTER_CONNECTION</type><id>comp2</id><informations><information key=\"info1\">value1</information><information key=\"info0\">value0</information><information key=\"info4\">value4</information><information key=\"info3\">value3</information><information key=\"info2\">value2</information></informations></component></components></configuration>";
    private static Locale savedLocale = Locale.getDefault();
    private static TimeZone savedTimeZone = TimeZone.getDefault();

    private Date newDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.parse("2011-03-03 19:00:23.828");
    }

    private ComponentId newId(String name) {
        ComponentId mock = mock(ComponentId.class);
        when(mock.asString()).thenReturn(name);
        return mock;
    }

    private List<ComponentDetail> newDetails(String[][] infos) {
        List<ComponentDetail> list = Lists.newArrayList();
        for (String[] row : infos) {
            list.add(newInformation(row[0], row[1]));
        }
        return list;
    }

    private AbstractComponentDetail newInformation(String key, String value) {
        ComponentDetail.Value detailValue = mock(ComponentDetail.Value.class);
        when(detailValue.asString()).thenReturn(value);
        AbstractComponentDetail mock = mock(AbstractComponentDetail.class);
        when(mock.getKey()).thenReturn(key);
        when(mock.getValue()).thenReturn(detailValue);
        return mock;
    }

    private Component newComponent(String name, int nbrValue) {
        Component mock = mock(Component.class);
        ComponentId id = newId(name);
        when(mock.getComponentId()).thenReturn(id);
        when(mock.getType()).thenReturn(ADAPTER_CONNECTION);
        String[][] rawInfos = new String[nbrValue][2];
        for (int i = 0; i < rawInfos.length; i++) {
            rawInfos[i] = new String[]{"info" + i,
                                       "value" + i};
        }
        List<ComponentDetail> details = newDetails(rawInfos);
        when(mock.getDetails()).thenReturn(details);
        Mockito.doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                GenericComponentVisitor visitor = (GenericComponentVisitor) invocation.getArguments()[0];
                visitor.visitAny((Component) invocation.getMock());
                return null;
            }
        }).when(mock).accept(any(GenericComponentVisitor.class));
        return mock;
    }

    private ConfigurationDescription createConfiguration() throws ParseException {
        return ConfigurationDescription.build()
                            .name("essai")
                            .addComponent(newComponent("comp1", 3))
                            .addComponent(newComponent("comp2", 5))
                            .createAt(newDate())
                            .modifyAt(newDate())
                            .build();

    }

    @BeforeClass
    public static void utcLocals() {
        Locale.setDefault(Locale.US);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @AfterClass
    public static void resetLocals() {
        Locale.setDefault(savedLocale);
        TimeZone.setDefault(savedTimeZone);
    }

    @Test
    public void testMarshalling() throws JAXBException, ParseException {
        ConfigurationDescription cnf = createConfiguration();
        JAXBContext ctx = JAXBContext.newInstance(ConfigurationDescription.class);
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        StringWriter out = new StringWriter();
        m.marshal(cnf, out);
        assertEquals("marshalling error", xml, out.toString());
    }

    @Test
    public void testUnmarshalling() throws JAXBException, ParseException {
        ConfigurationDescription cnf = createConfiguration();
        JAXBContext ctx = JAXBContext.newInstance(ConfigurationDescription.class);
        Unmarshaller u = ctx.createUnmarshaller();
        StringReader in = new StringReader(xml);
        ConfigurationDescription readCnf = (ConfigurationDescription) u.unmarshal(in);
        assertEquals("unmarshalling error", cnf, readCnf);
        List<ComponentDescription> fromComponents = cnf.getComponents();
        List<ComponentDescription> toComponents = readCnf.getComponents();
        assertEquals(fromComponents.size(), toComponents.size());
        assertArrayEquals(fromComponents.toArray(), toComponents.toArray());
    }

}

