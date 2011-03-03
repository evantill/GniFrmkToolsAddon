package thirdparty.jaxb.registry;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public class ElementTest {

    @Test
    public void testMarshall() throws JAXBException {
        ElementRoot root = new ElementRoot();
        ElementOne one = new ElementOne("value11", "value12", ElementType.ONE, "nameOne");
        one.setStatus(new StatusEnable());
        root.setOne(one);

        ElementTwo two = new ElementTwo("value11", "value12", ElementType.TWO, "nameOne", "value21", "value22");
        root.setTwo(two);

        ElementThree three = new ElementThree("value11", "value12", ElementType.TWO, "nameOne", "value21", "value22", "value31");
        root.setThree(three);

        JAXBContext ctx = JAXBContext.newInstance(ElementRoot.class);
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter out = new StringWriter();
        m.marshal(root, out);

        System.out.println("out = " + out);
    }

    @Test
    public void testUnmarshall() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><elementRoot><one><value11>value11</value11><value12>value12</value12><type>ONE</type><name>nameOne</name></one><two><value11>value11</value11><value12>value12</value12><type>TWO</type><name>nameOne</name><value21>value21</value21><value22>value22</value22></two><three><value11>value11</value11><value12>value12</value12><type>TWO</type><name>nameOne</name><value21>value21</value21><value22>value22</value22><value31>value31</value31></three></elementRoot>";
        JAXBContext ctx = JAXBContext.newInstance(ElementRoot.class);
        Unmarshaller u = ctx.createUnmarshaller();

        StringReader in = new StringReader(xml);
        ElementRoot root = (ElementRoot) u.unmarshal(in);

        System.out.println("root = " + root);
    }
}
