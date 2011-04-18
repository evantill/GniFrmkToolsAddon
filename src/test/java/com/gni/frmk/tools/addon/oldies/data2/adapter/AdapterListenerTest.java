package com.gni.frmk.tools.addon.oldies.data2.adapter;

import com.gni.frmk.tools.addon.model.component.ImmutableAdapterListener;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterListener;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class AdapterListenerTest {
    private static final String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><adapterListenerXmlRoot><listener><type>ADAPTER_LISTENER</type><id>listener1</id><details><details key=\"adapterType\">JDBCAdapter</details><details key=\"key1\">value1</details><details key=\"key2\">value2</details><details key=\"listenerName\">listenerEssai</details><details key=\"packageName\">WmEssai</details></details><state><enabled>ENABLED</enabled></state></listener></adapterListenerXmlRoot>";

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.NONE)
    static class AdapterListenerXmlRoot {

        @XmlElement
        private ImmutableAdapterListener listener;

        public ImmutableAdapterListener getListener() {
            return listener;
        }

        public void setListener(ImmutableAdapterListener listener) {
            this.listener = listener;
        }
    }


//    private AdapterListener createSample(boolean checked) {
//        AdapterListenerBuilder builder = AdapterListener.builder()
//                                                        .id("listener1")
//                                                        .adapterType("JDBCAdapter")
//                                                        .packageName("WmEssai")
//                                                        .listenerName("listenerEssai")
//                                                        .addDetail("key1", "value1")
//                                                        .addDetail("key2", "value2")
//                                                        .state(new EnableComponentState(ENABLED));
//        if (checked) {
//            builder.check();
//        }
//        return builder.build();
//    }

//    private void assertSampleAsExpected(AdapterListener sample) {
//        assertNotNull("adapter listener must be not null", sample);
//        assertEquals("packageName must be WmEssai", "WmEssai", sample.getPackageName());
//        assertEquals("listenerName must be listenerEssai", "listenerEssai", sample.getListenerName());
//        assertEquals("id must be listener1", "listener1", sample.getId().toString());
//        assertEquals("type is not valid", AdapterListener.TYPE, sample.getType());
//        assertEquals("key1 value must be value1", "value1", sample.findDetail("key1").getValue());
//        assertEquals("key2 value must be value2", "value2", sample.findDetail("key2").getValue());
//        assertEquals("adapter connection must be ENABLED", ENABLED, sample.getState().getEnabled());
//        assertNull("key3 must return null", sample.findDetail("key3"));
//    }
//
//    @Test
//    public void testAdapterListenerBuilder() {
//        AdapterListener adapterListener = createSample(false);
//        assertSampleAsExpected(adapterListener);
//    }
//
//    @Test
//    public void testAdapterListenerBuilderFailedOnState() {
//        AdapterListener adapterListener = createSample(true);
//        assertSampleAsExpected(adapterListener);
//    }
//
//    @Test
//    public void testAdapterListenerXmlMarshalling() throws JAXBException {
//        AdapterListener listener = createSample(false);
//
//        AdapterListenerXmlRoot root = new AdapterListenerXmlRoot();
//        root.setListener(listener);
//
//        JAXBContext ctx = JAXBContext.newInstance(AdapterListenerXmlRoot.class);
//
//        Marshaller marshaller = ctx.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
//
//        StringWriter out = new StringWriter();
//        marshaller.marshal(root, out);
//        assertEquals("invalid marshalling", xmlContent, out.toString());
//    }
//
//    @Test
//    public void testAdapterListenerXmlUnmarshalling() throws JAXBException {
//        JAXBContext ctx = JAXBContext.newInstance(AdapterListenerXmlRoot.class);
//        Unmarshaller unmarshaller = ctx.createUnmarshaller();
//
//        StringReader in = new StringReader(xmlContent);
//        AdapterListenerXmlRoot root = (AdapterListenerXmlRoot) unmarshaller.unmarshal(in);
//        assertSampleAsExpected(root.getListener());
//    }
}
