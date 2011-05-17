package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.util.FileResource;
import com.google.common.collect.Sets;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:16
 *
 * @author: e03229
 */
public class ConfigurationTest {

    @Rule
    public ConfigurationTestRule utils = new ConfigurationTestRule();

    @Rule
    public FileResource expectedXml = new FileResource(ConfigurationTest.class, "ConfigurationTest.xml");

    @Test
    public void testToXml() throws JAXBException, IOException, SAXException {

        Configuration cnf = utils.getConfigurationBuilder().createConfiguration();

        StringWriter out = new StringWriter();
        utils.saveConfiguration(cnf, out);

        System.out.println("out = " + out);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }

    @Test
    public void testFromXml() throws JAXBException, IOException, SAXException {
        StringReader in = new StringReader(expectedXml.getContent());

        Configuration cnf = utils.loadConfiguration(in);
        Configuration expectedCnf = utils.getConfigurationBuilder().createConfiguration();

        Assert.assertEquals(expectedCnf.getCreation(), cnf.getCreation());
        Assert.assertEquals(expectedCnf.getId(), cnf.getId());
        Assert.assertEquals(expectedCnf.getComponentConfigurations().size(), cnf.getComponentConfigurations().size());

        StringWriter out = new StringWriter();
        utils.saveConfiguration(cnf, out);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }

    @Test
    public void testTypeFilter() throws JAXBException, IOException, SAXException {
        StringReader in = new StringReader(expectedXml.getContent());

        Configuration cnf = utils.loadConfiguration(in);
        Set<Type> types = cnf.listTypes();
        Set<Type> expectedTypes = Sets.newHashSet(Type.UNKNOWN);
        Assert.assertEquals(expectedTypes, types);

        List<ComponentConfiguration> expectedList = cnf.getComponentConfigurations();
        Collection<ComponentConfiguration> filteredList = cnf.listComponentConfigurationsByType(Type.UNKNOWN);
        Assert.assertTrue(filteredList.containsAll(expectedList));

        Collection<ComponentConfiguration> emptyList = cnf.listComponentConfigurationsByType(Type.ADAPTER_CONNECTION);
        Assert.assertTrue(emptyList.isEmpty());
    }

}
