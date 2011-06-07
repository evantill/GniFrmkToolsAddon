package com.gni.frmk.tools.addon.model.configuration.test.oldies;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.util.ConstantLocaleRule;
import com.gni.frmk.tools.addon.util.FileResource;
import com.gni.frmk.tools.addon.util.NowTimestampRule;
import com.gni.frmk.tools.addon.util.XmlTestRule;
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

import static com.gni.frmk.tools.addon.util.ConstantLocaleRule.utc_and_us_rule;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 12:36
 *
 * @author: e03229
 */
public class ConfigurationFullTest {

    private static final Package CONFIGURATION_PACKAGE = Configuration.class.getPackage();
    private static final Package COMPONENT_PACKAGE = Component.class.getPackage();
    private static final Package COMPONENT_BASE_PACKAGE = BaseComponent.class.getPackage();
    private static final Package COMPONENT_ART_PACKAGE = AdapterConnection.class.getPackage();
    private static final Package COMPONENT_JMS_PACKAGE = JmsAlias.class.getPackage();
    private static final Package COMPONENT_ROOT_PACKAGE = Port.class.getPackage();

    private static final Package[] CONTEXT_PATH = new Package[]{
            CONFIGURATION_PACKAGE,
            COMPONENT_PACKAGE,
            COMPONENT_BASE_PACKAGE,
            COMPONENT_ART_PACKAGE,
            COMPONENT_JMS_PACKAGE,
            COMPONENT_ROOT_PACKAGE
    };

    @Rule
    public ConstantLocaleRule localeRule = utc_and_us_rule();

    @Rule
    public NowTimestampRule nowRule = new NowTimestampRule();

    @Rule
    public XmlTestRule xmlRule = new XmlTestRule(CONTEXT_PATH);

    @Rule
    public NowTimestampRule utils = new NowTimestampRule();

    @Rule
    public FileResource expectedXml = new FileResource(ConfigurationFullTest.class, "ConfigurationFullTest.xml");

    @Test
    public void testToXml() throws JAXBException, IOException, SAXException {
        Configuration cnf = new ConfigurationFullBuilder(0, nowRule.now()).buildFullConfiguration();

        StringWriter out = new StringWriter();
        xmlRule.save(cnf, out, true);

        System.out.println("out = " + out);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }

    @Test
    public void testFromXml() throws JAXBException, IOException, SAXException {
        StringReader in = new StringReader(expectedXml.getContent());

        Configuration cnf = xmlRule.load(in, Configuration.class);
        Configuration expectedCnf = new ConfigurationFullBuilder(0, nowRule.now()).buildFullConfiguration();

        Assert.assertEquals(expectedCnf.getCreation(), cnf.getCreation());
        Assert.assertEquals(expectedCnf.getId(), cnf.getId());
        Assert.assertEquals(expectedCnf.getComponentConfigurations().size(), cnf.getComponentConfigurations().size());

        StringWriter out = new StringWriter();
        xmlRule.save(cnf, out, true);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }
}
