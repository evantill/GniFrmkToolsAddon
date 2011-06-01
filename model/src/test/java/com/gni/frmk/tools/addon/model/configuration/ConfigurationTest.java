package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;
import com.gni.frmk.tools.addon.model.component.test.Component1;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.util.ConstantLocaleRule;
import com.gni.frmk.tools.addon.util.FileResource;
import com.gni.frmk.tools.addon.util.NowTimestampRule;
import com.gni.frmk.tools.addon.util.XmlTestRule;
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

import static com.gni.frmk.tools.addon.util.ConstantLocaleRule.utc_and_us_rule;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:16
 *
 * @author: e03229
 */
public class ConfigurationTest {

    private static final Package CONFIGURATION_PACKAGE = Configuration.class.getPackage();
    private static final Package COMPONENT_PACKAGE = Component.class.getPackage();
    private static final Package COMPONENT_TEST_PACKAGE = Component1.class.getPackage();
    private static final Package COMPONENT_BASE_PACKAGE = BaseComponent.class.getPackage();

    @Rule
    public ConstantLocaleRule localeRule = utc_and_us_rule();

    @Rule
    public NowTimestampRule nowRule = new NowTimestampRule();

    @Rule
    public XmlTestRule xmlRule = new XmlTestRule(CONFIGURATION_PACKAGE, COMPONENT_PACKAGE, COMPONENT_BASE_PACKAGE, COMPONENT_TEST_PACKAGE);

    @Rule
    public NowTimestampRule utils = new NowTimestampRule();

    @Rule
    public FileResource expectedXml = new FileResource(ConfigurationTest.class, "ConfigurationTest.xml");

    @Test
    public void testToXml() throws JAXBException, IOException, SAXException {
        ConfigurationBuilder builder = new ConfigurationBuilder(0, utils.now());
        Configuration cnf = builder.createConfiguration();

        StringWriter out = new StringWriter();
        xmlRule.save(cnf, out, true);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }

    @Test
    public void testFromXml() throws JAXBException, IOException, SAXException {
        ConfigurationBuilder builder = new ConfigurationBuilder(0, utils.now());
        StringReader in = new StringReader(expectedXml.getContent());

        Configuration cnf = xmlRule.load(in, Configuration.class);
        Configuration expectedCnf = builder.createConfiguration();

        Assert.assertEquals(expectedCnf.getCreation(), cnf.getCreation());
        Assert.assertEquals(expectedCnf.getId(), cnf.getId());
        Assert.assertEquals(expectedCnf.getComponentConfigurations().size(), cnf.getComponentConfigurations().size());

        StringWriter out = new StringWriter();
        xmlRule.save(cnf, out, true);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }


    @Test
    public void testTypeFilter() throws JAXBException, IOException, SAXException {
        StringReader in = new StringReader(expectedXml.getContent());

        Configuration cnf = xmlRule.load(in, Configuration.class);
        Set<ComponentType> types = cnf.listTypes();
        Set<ComponentType> expectedTypes = Sets.<ComponentType>newHashSet(Component1Type.newInstance());
        Assert.assertEquals(expectedTypes, types);

        List<ComponentConfiguration> expectedList = cnf.getComponentConfigurations();
        Collection<ComponentConfiguration> filteredList = cnf.listComponentConfigurationsByType(Component1Type.newInstance());
        Assert.assertTrue(filteredList.containsAll(expectedList));

        ComponentType unknownType = mock(ComponentType.class);
        when(unknownType.equals(any())).thenReturn(false);
        Collection<ComponentConfiguration> emptyList = cnf.listComponentConfigurationsByType(unknownType);
        Assert.assertTrue(emptyList.isEmpty());
    }

}
