package com.gni.frmk.tools.addon.data2.adapter;

import com.gni.frmk.tools.addon.data2.AdapterConnection;
import com.gni.frmk.tools.addon.data2.AdapterConnection.Builder;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;

import static com.gni.frmk.tools.addon.data2.ComponentState.EnableStatus.ENABLED;
import static org.junit.Assert.*;

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
        private AdapterConnection connection;

        public AdapterConnection getConnection() {
            return connection;
        }

        public void setConnection(AdapterConnection connection) {
            this.connection = connection;
        }
    }


    private AdapterConnection createSample(boolean checked) {
        Builder<?> builder = AdapterConnection.builder()
                                              .adapterType("JDBCAdapter")
                                              .packageName("WmEssai")
                                              .alias("aliasValue")
                                              .id("compId")
                                              .addDetail("key1", "value1")
                                              .addDetail("key2", "value2")
                                              .enabled(ENABLED);
        if (checked) {
            builder.check();
        }
        return builder.build();
    }

    private void assertSampleAsExpected(AdapterConnection sample) {
        assertNotNull("adapter connection must be not null", sample);
        assertEquals("packageName must be WmEssai", "WmEssai", sample.getPackageName());
        assertEquals("alias must be aliasValue", "aliasValue", sample.getAlias());
        assertEquals("id must be compId", "compId", sample.getId().toString());
        assertEquals("type is not valid", AdapterConnection.TYPE, sample.getType());
        assertEquals("key1 value must be value1", "value1", sample.findDetail("key1").getValue());
        assertEquals("key2 value must be value2", "value2", sample.findDetail("key2").getValue());
        assertEquals("adapter connection must be ENABLED", ENABLED, sample.getState().getEnabled());
        assertNull("key3 must return null", sample.findDetail("key3"));
    }

    @Test
    public void testAdapterConnectionBuilder() {
        AdapterConnection adapterConnection = createSample(false);
        assertSampleAsExpected(adapterConnection);
    }

    @Test
    public void testAdapterConnectionBuilderFailedOnState() {
        AdapterConnection adapterConnection = createSample(true);
        assertSampleAsExpected(adapterConnection);
    }

    @Test
    public void testAdapterConnectionXmlMarshalling() throws JAXBException {
        AdapterConnection connection = createSample(false);

        AdapterConnectionXmlRoot root = new AdapterConnectionXmlRoot();
        root.setConnection(connection);

        JAXBContext ctx = JAXBContext.newInstance(AdapterConnectionXmlRoot.class);

        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);

        StringWriter out = new StringWriter();
        marshaller.marshal(root, out);
        assertEquals("invalid marshalling", xmlContent, out.toString());
    }

    @Test
    public void testAdapterConnectionXmlUnmarshalling() throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(AdapterConnectionXmlRoot.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();

        StringReader in = new StringReader(xmlContent);
        AdapterConnectionXmlRoot root = (AdapterConnectionXmlRoot) unmarshaller.unmarshal(in);
        assertSampleAsExpected(root.getConnection());
    }
}
