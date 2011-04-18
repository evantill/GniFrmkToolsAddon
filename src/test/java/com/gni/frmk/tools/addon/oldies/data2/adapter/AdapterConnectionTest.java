package com.gni.frmk.tools.addon.oldies.data2.adapter;

import com.gni.frmk.tools.addon.model.component.ImmutableAdapterConnection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class AdapterConnectionTest {
    private static final String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><adapterConnectionXmlRoot><connection><type>ADAPTER_CONNECTION</type><id>compId</id><details><details key=\"adapterType\">JDBCAdapter</details><details key=\"alias\">aliasValue</details><details key=\"key1\">value1</details><details key=\"key2\">value2</details><details key=\"packageName\">WmEssai</details></details><state><enabled>ENABLED</enabled></state></connection></adapterConnectionXmlRoot>";

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.NONE)
    static class AdapterConnectionXmlRoot {

        @XmlElement
        private ImmutableAdapterConnection connection;

        public ImmutableAdapterConnection getConnection() {
            return connection;
        }

        public void setConnection(ImmutableAdapterConnection connection) {
            this.connection = connection;
        }
    }


//    private AdapterConnection createSample(boolean checked) {
//        AdapterConnectionBuilder builder = AdapterConnection.builder()
//                                              .adapterType("JDBCAdapter")
//                                              .packageName("WmEssai")
//                                              .alias("aliasValue")
//                                              .id("compId")
//                                              .addDetail("key1", "value1")
//                                              .addDetail("key2", "value2")
//                                              .state(new EnableComponentState(EnableStatus.ENABLED));
//        if (checked) {
//            builder.check();
//        }
//        return builder.build();
//    }

//    private void assertSampleAsExpected(AdapterConnection sample) {
//        assertNotNull("adapter connection must be not null", sample);
//        assertEquals("packageName must be WmEssai", "WmEssai", sample.getPackageName());
//        assertEquals("alias must be aliasValue", "aliasValue", sample.getAlias());
//        assertEquals("id must be compId", "compId", sample.getId().toString());
//        assertEquals("type is not valid", AdapterConnection.TYPE, sample.getType());
//        assertEquals("key1 value must be value1", "value1", sample.findDetail("key1").getValue());
//        assertEquals("key2 value must be value2", "value2", sample.findDetail("key2").getValue());
//        assertEquals("adapter connection must be ENABLED", ENABLED, sample.getState().getEnabled());
//        assertNull("key3 must return null", sample.findDetail("key3"));
//    }

//    @Test
//    public void testAdapterConnectionBuilder() {
//        AdapterConnection adapterConnection = createSample(false);
//        assertSampleAsExpected(adapterConnection);
//    }

//    @Test
//    public void testAdapterConnectionBuilderFailedOnState() {
//        AdapterConnection adapterConnection = createSample(true);
//        assertSampleAsExpected(adapterConnection);
//    }

//    @Test
//    public void testAdapterConnectionXmlMarshalling() throws JAXBException {
//        AdapterConnection connection = createSample(false);
//
//        AdapterConnectionXmlRoot root = new AdapterConnectionXmlRoot();
//        root.setConnection(connection);
//
//        JAXBContext ctx = JAXBContext.newInstance(AdapterConnectionXmlRoot.class);
//
//        Marshaller marshaller = ctx.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//        StringWriter out = new StringWriter();
//        marshaller.marshal(root, out);
//        System.out.println("out = " + out);
//        assertEquals("invalid marshalling", xmlContent, out.toString());
//    }

//    @Test
//    public void testAdapterConnectionXmlUnmarshalling() throws JAXBException {
//        String tmp="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + "<adapterConnectionXmlRoot>\n" + "    <connection>\n" + "        <type>ADAPTER_CONNECTION</type>\n" + "        <id>compId</id>\n" + "        <details>\n" + "            <detail key=\"adapterType\">JDBCAdapter</detail>\n" + "            <detail key=\"alias\">aliasValue</detail>\n" + "            <detail key=\"key1\">value1</detail>\n" + "            <detail key=\"key2\">value2</detail>\n" + "            <detail key=\"packageName\">WmEssai</detail>\n" + "        </details>\n" + "        <state>\n" + "            <enabled>ENABLED</enabled>\n" + "        </state>\n" + "    </connection>\n" + "</adapterConnectionXmlRoot>";
//        JAXBContext ctx = JAXBContext.newInstance(AdapterConnectionXmlRoot.class);
//        Unmarshaller unmarshaller = ctx.createUnmarshaller();

//        //StringReader in = new StringReader(xmlContent);
//        StringReader in = new StringReader(tmp);
//        AdapterConnectionXmlRoot root = (AdapterConnectionXmlRoot) unmarshaller.unmarshal(in);
//        assertSampleAsExpected(root.getConnection());
//    }
}
