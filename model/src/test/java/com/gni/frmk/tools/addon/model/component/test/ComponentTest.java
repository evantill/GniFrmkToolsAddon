package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;
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
 * Time: 14:57
 *
 * @author: e03229
 */
public class ComponentTest {

    private static final Package COMPONENT_PACKAGE = Component.class.getPackage();
    private static final Package COMPONENT_TEST_PACKAGE = Component1.class.getPackage();
    private static final Package COMPONENT_BASE_PACKAGE = BaseComponent.class.getPackage();

    @Rule
    public ConstantLocaleRule localeRule = utc_and_us_rule();

    @Rule
    public NowTimestampRule nowRule = new NowTimestampRule();

    @Rule
    public XmlTestRule xmlRule = new XmlTestRule(COMPONENT_PACKAGE, COMPONENT_BASE_PACKAGE, COMPONENT_TEST_PACKAGE);

    @Rule
    public FileResource expectedXml = new FileResource(getClass(), "ComponentTest_Component1.xml");

    @Rule
    public ComponentResource builder = new ComponentResource();

    @Test
    public void testToXml() throws JAXBException, IOException, SAXException {
        Component1 component = builder.createComponent1();
        StringWriter out = new StringWriter();
        xmlRule.save(component, out, true);

        System.out.println("out = " + out);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }

    @Test
    public void testFromXml() throws JAXBException, IOException, SAXException {
        StringReader in = new StringReader(expectedXml.getContent());

        Component1 component = xmlRule.load(in, Component1.class);
        Component1 expected = builder.createComponent1();

        Assert.assertEquals(expected.getType(), component.getType());
        Assert.assertEquals(expected.getId(), component.getId());
        Assert.assertEquals(expected.getCurrentState(), component.getCurrentState());
        Assert.assertEquals(expected.getDetail(), component.getDetail());

        StringWriter out = new StringWriter();
        xmlRule.save(component, out, true);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }

}
