package thirdparty.jaxb.emptyconstructor;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.io.StringWriter;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 15/11/10
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class TestJaxbBuilderEmptyConstructor {

    private final String raw = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rootElement><one><two><contenu>ceci est le contenu</contenu></two></one></rootElement>";
    private final String contenu = "ceci est le contenu";

    @Test
    public void testBuilderAdapterMarshaller() throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(RootElement.class);

        ElementTwo two = new ElementTwoBuilder().define(contenu).build();
        ElementOne one = new ElementOneBuilder().define(two).build();
        RootElement root = new RootElement();
        root.setOne(one);

        StringWriter out = new StringWriter();
        ctx.createMarshaller().marshal(root, out);
        assertEquals(raw, out.getBuffer().toString());

    }

    @Test
    public void testBuilderAdapterUnmarshaller() throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(RootElement.class);
        RootElement root = (RootElement) ctx.createUnmarshaller().unmarshal(new StringReader(raw));
        System.out.println("root = " + root);
        assertEquals(root.getOne().getTwo().getContenu(), contenu);
    }
}
