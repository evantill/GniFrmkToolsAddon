package com.gni.frmk.tools.addon.manager;

import com.gni.frmk.tools.addon.manager.ConfigurationSerializer.SerializationException;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTest;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTestRule;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 11:40
 *
 * @author: e03229
 */
public class ConfigurationSerializerTest {

    private static String xmlSimple = ConfigurationTestRule.loadXml("simple", ConfigurationTest.class);
    private static String xmlFull = ConfigurationTestRule.loadXml("full", ConfigurationTest.class);

    @Rule
    public ConfigurationTestRule util = new ConfigurationTestRule();

    private static ConfigurationSerializer serializer;

    @BeforeClass
    public static void initSerializer() throws SerializationException {
        serializer = new ConfigurationSerializer();
    }

    @Before
    public void xmlUnitConfiguration() {
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
    }

    @Test
    public void testSaveSimpleConfiguration() throws IOException, SAXException {
        Configuration cnf = util.newSimpleConfiguration();
        StringWriter out = new StringWriter();
        serializer.saveConfiguration(cnf, out);
        assertXMLEqual(xmlSimple, out.toString());
    }

    @Test
    public void testLoadSimpleConfiguration() throws SerializationException {
        Configuration fromCnf = util.newSimpleConfiguration();
        StringReader in = new StringReader(xmlSimple);
        Configuration fromXml = serializer.loadConfiguration(in);
        util.raiseExceptionIfInvalid(fromXml);
        assertNotNull(fromXml);
        assertEquals(fromCnf.getAdapterConnectionConfigurations().size(), fromXml.getAdapterConnectionConfigurations()
                                                                                 .size());
    }

    @Test
    public void testSaveFullConfiguration() throws IOException, SAXException {
        Configuration cnf = util.newFullConfiguration();
        StringWriter out = new StringWriter();
        serializer.saveConfiguration(cnf, out);
        assertXMLEqual(xmlFull, out.toString());
    }

    @Test
    public void testLoadFullConfiguration() throws SerializationException {
        Configuration fromCnf = util.newFullConfiguration();
        StringReader in = new StringReader(xmlFull);
        Configuration fromXml = serializer.loadConfiguration(in);
        util.raiseExceptionIfInvalid(fromXml);
        assertNotNull(fromXml);
        assertEquals(fromCnf.getAdapterConnectionConfigurations().size(),
                fromXml.getAdapterConnectionConfigurations().size());
        assertEquals(10, fromXml.getAdapterConnectionConfigurations().size());
    }

}
