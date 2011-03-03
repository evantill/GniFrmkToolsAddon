package com.gni.frmk.tools.addon.data3.configuration;

import com.gni.frmk.tools.addon.data3.components.Component;
import com.gni.frmk.tools.addon.data3.components.ComponentId;
import com.gni.frmk.tools.addon.data3.components.ComponentInformation;
import com.google.common.collect.Lists;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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

import static com.gni.frmk.tools.addon.data3.components.ComponentType.ADAPTER_CONNECTION;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationTest {

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

    private List<ComponentInformation> newInformations(String[][] infos) {
        List<ComponentInformation> list = Lists.newArrayList();
        for (String[] row : infos) {
            list.add(newInformation(row[0], row[1]));
        }
        return list;
    }

    private ComponentInformation newInformation(String key, String value) {
        ComponentInformation mock = mock(ComponentInformation.class);
        when(mock.getKey()).thenReturn(key);
        when(mock.getValue()).thenReturn(value);
        return mock;
    }

    private Component newComponent(String name, int nbrValue) {
        Component mock = mock(Component.class);
        ComponentId id = newId(name);
        when(mock.getId()).thenReturn(id);
        when(mock.getType()).thenReturn(ADAPTER_CONNECTION);
        String[][] rawInfos = new String[nbrValue][2];
        for (int i = 0; i < rawInfos.length; i++) {
            rawInfos[i] = new String[]{"info" + i,
                                       "value" + i};
        }
        List<ComponentInformation> informations = newInformations(rawInfos);
        when(mock.getInformations()).thenReturn(informations);
        return mock;
    }

    private Configuration createConfiguration() throws ParseException {
        return Configuration.build()
                            .name("essai")
                            .addComponent(newComponent("comp1", 3))
                            .addComponent(newComponent("comp2", 5))
                            .create(newDate())
                            .modify(newDate())
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
        Configuration cnf = createConfiguration();
        JAXBContext ctx = JAXBContext.newInstance(Configuration.class);
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        StringWriter out = new StringWriter();
        m.marshal(cnf, out);
        assertEquals("marshalling error", xml, out.toString());
    }

    @Test
    public void testUnmarshalling() throws JAXBException, ParseException {
        Configuration cnf = createConfiguration();
        JAXBContext ctx = JAXBContext.newInstance(Configuration.class);
        Unmarshaller u = ctx.createUnmarshaller();
        StringReader in = new StringReader(xml);
        Configuration readCnf = (Configuration) u.unmarshal(in);
        assertEquals("unmarshalling error", cnf, readCnf);
        List<ComponentDescription> fromComponents = cnf.getComponents();
        List<ComponentDescription> toComponents = readCnf.getComponents();
        assertEquals(fromComponents.size(), toComponents.size());
        assertArrayEquals(fromComponents.toArray(), toComponents.toArray());
    }

}

