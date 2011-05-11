package com.gni.frmk.tools.addon.model;

import com.gni.frmk.tools.addon.model.Component.Type;
import com.gni.frmk.tools.addon.model.jaxb.JaxbUtil;
import com.google.common.collect.Sets;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
    public ConfigurationUtils utils = new ConfigurationUtils("2010-05-03T20:01:59+01:00");

    @Rule
    public FileResource expectedXml = new FileResource(ConfigurationTest.class, "ConfigurationTest.xml");

    @Test
    public void testToXml() throws JAXBException, IOException, SAXException {

        Configuration cnf = utils.createConfiguration();

        JAXBContext ctx = JaxbUtil.newContext();

        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter out = new StringWriter();
        marshaller.marshal(cnf, out);

        System.out.println("out = " + out);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }

    @Test
    public void testFromXml() throws JAXBException, IOException, SAXException {
        StringReader in = new StringReader(expectedXml.getContent());
        JAXBContext ctx = JaxbUtil.newContext();

        Unmarshaller unmarshaller = ctx.createUnmarshaller();

        Configuration cnf = (Configuration) unmarshaller.unmarshal(in);
        Configuration expectedCnf = utils.createConfiguration();

        Assert.assertEquals(expectedCnf.getCreation(), cnf.getCreation());
        Assert.assertEquals(expectedCnf.getId(), cnf.getId());
        Assert.assertEquals(expectedCnf.getComponentConfigurations().size(), cnf.getComponentConfigurations().size());

        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter out = new StringWriter();
        marshaller.marshal(cnf, out);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());

    }


    @Test
    public void testTypeFilter() throws JAXBException, IOException, SAXException {
        StringReader in = new StringReader(expectedXml.getContent());
        JAXBContext ctx = JaxbUtil.newContext();
        Unmarshaller unmarshaller = ctx.createUnmarshaller();

        Configuration cnf = (Configuration) unmarshaller.unmarshal(in);
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
