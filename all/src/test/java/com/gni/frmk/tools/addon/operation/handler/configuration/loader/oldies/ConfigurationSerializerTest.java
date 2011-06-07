package com.gni.frmk.tools.addon.operation.handler.configuration.loader.oldies;

import com.gni.frmk.tools.addon.model.configuration.test.oldies.ConfigurationBuilder;
//import com.gni.frmk.tools.addon.model.configuration.ConfigurationAllTestRule;
import com.gni.frmk.tools.addon.repository.ConfigurationSerializer;
import com.gni.frmk.tools.addon.repository.ConfigurationSerializer.SerializationException;
import com.gni.frmk.tools.addon.util.NowTimestampRule;
import com.gni.frmk.tools.addon.util.ValidationRule;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 11:40
 *
 * @author: e03229
 */
public class ConfigurationSerializerTest {


    @Rule public ValidationRule validationRule = new ValidationRule();
    @Rule public NowTimestampRule nowRule = new NowTimestampRule();

    private static ConfigurationSerializer serializer;
    private String xmlSimple;
    private String xmlFull;

    @Before
    public void xmlUnitConfiguration() {
        ConfigurationBuilder builder = new ConfigurationBuilder(0,nowRule.now());
//        serializer = new ConfigurationSerializer(utils.getResourceManager());
//        String xmlSimple = utils.loadXml("simple", ConfigurationTest.class);
//        String xmlFull = utils.loadXml("full", ConfigurationTest.class);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
    }

    @Test
    public void testSaveSimpleConfiguration() throws IOException, SAXException {
//        Configuration cnf = builder.newSimpleConfiguration();
//        StringWriter out = new StringWriter();
//        serializer.saveConfiguration(cnf, out);
//        assertXMLEqual(xmlSimple, out.toString());
    }

    @Test
    public void testLoadSimpleConfiguration() throws SerializationException {
//        Configuration fromCnf = utils.getConfigurationBuilder().newSimpleConfiguration();
//        StringReader in = new StringReader(xmlSimple);
//        Configuration fromXml = serializer.loadConfiguration(in);
//        validationRule.raiseExceptionIfInvalid(fromXml);
//        assertNotNull(fromXml);
//        assertEquals(fromCnf.listComponentConfigurationsByType(ComponentType.ADAPTER_CONNECTION)
//                            .size(), fromXml.listComponentConfigurationsByType(ComponentType.ADAPTER_CONNECTION).size());
    }

    @Test
    public void testSaveFullConfiguration() throws IOException, SAXException {
//        Configuration cnf = utils.getConfigurationBuilder().newFullConfiguration();
//        StringWriter out = new StringWriter();
//        serializer.saveConfiguration(cnf, out);
//        assertXMLEqual(xmlFull, out.toString());
    }

    @Test
    public void testLoadFullConfiguration() throws SerializationException {
//        Configuration fromCnf = utils.getConfigurationBuilder().newFullConfiguration();
//        StringReader in = new StringReader(xmlFull);
//        Configuration fromXml = serializer.loadConfiguration(in);
//        utils.raiseExceptionIfInvalid(fromXml);
//        assertNotNull(fromXml);
//        assertEquals(fromCnf.listComponentConfigurationsByType(AdapterConnectionType.TYPE).size(),
//                fromXml.listComponentConfigurationsByType(AdapterConnectionType.TYPE).size());
//        assertEquals(10, fromXml.listComponentConfigurationsByType(AdapterConnectionType.TYPE).size());
    }

}
