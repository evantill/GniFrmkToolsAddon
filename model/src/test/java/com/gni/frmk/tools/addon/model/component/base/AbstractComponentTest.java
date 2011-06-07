package com.gni.frmk.tools.addon.model.component.base;

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
 * Date: 06/06/11
 * Time: 10:21
 *
 * @author: e03229
 */
public abstract class AbstractComponentTest<T extends Component<?, ?, ?, ?, ?>> {
    private static final Package COMPONENT_PACKAGE = Component.class.getPackage();
    private static final Package COMPONENT_BASE_PACKAGE = BaseComponent.class.getPackage();

    @Rule
    public ConstantLocaleRule localeRule = utc_and_us_rule();

    @Rule
    public NowTimestampRule nowRule = new NowTimestampRule();

    @Rule
    public XmlTestRule xmlRule;

    @Rule
    public FileResource expectedXml;

    private final Class<T> type;

    protected AbstractComponentTest(Class<T> componentType) {
        type = componentType;
        final Package componentPackage = componentType.getPackage();
        xmlRule = new XmlTestRule(COMPONENT_PACKAGE, COMPONENT_BASE_PACKAGE, componentPackage);
        final String expectedXmlResourceName = String.format("%s.xml", getClass().getSimpleName());
        expectedXml = new FileResource(getClass(), expectedXmlResourceName);
    }

    protected abstract T buildComponent();

    @Test
    public void testToXml() throws JAXBException, IOException, SAXException {
        T component = buildComponent();
        StringWriter out = new StringWriter();
        xmlRule.save(component, out, true);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedXml.getContent(), out.toString());
    }

    @Test
    public void testFromXml() throws JAXBException, IOException, SAXException {
        StringReader in = new StringReader(expectedXml.getContent());

        T component = xmlRule.load(in, type);
        T expected = buildComponent();

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
