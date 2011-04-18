package com.gni.frmk.tools.addon.handler.configuration.repository;

import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 11:38
 *
 * @author: e03229
 */
public class ConfigurationSerializer {
    private static final String[] MODEL_PACKAGES = new String[]{
            "com.gni.frmk.tools.addon.model.configuration",
            "com.gni.frmk.tools.addon.model.configuration.component",
            "com.gni.frmk.tools.addon.model.component",
            "com.gni.frmk.tools.addon.model.component.detail",
            "com.gni.frmk.tools.addon.model.component.id",
            "com.gni.frmk.tools.addon.model.component.state",
    };
    private static final String JAXB_PACKAGES = buildJaxbPackages();

    private final JAXBContext ctx;
    private final boolean prettyPrint;

    public ConfigurationSerializer() throws SerializationException {
        try {
            ctx = JAXBContext.newInstance(JAXB_PACKAGES);
            prettyPrint = true;
        } catch (JAXBException e) {
            throw new SerializationException(e);
        }
    }

    private static String buildJaxbPackages() {
        StringBuilder builder = new StringBuilder();
        for (String p : MODEL_PACKAGES) {
            builder.append(p).append(':');
        }
        return builder.toString();
    }

    public ImmutableConfiguration loadConfiguration(InputStream source) throws SerializationException {
        return loadConfiguration(new InputStreamReader(source));
    }

    public ImmutableConfiguration loadConfiguration(Reader source) throws SerializationException {
        try {
            Unmarshaller u = ctx.createUnmarshaller();
            return (ImmutableConfiguration) u.unmarshal(source);
        } catch (JAXBException e) {
            throw new SerializationException(e);
        }
    }

    public void saveConfiguration(ImmutableConfiguration cnf, Writer destination) throws SerializationException {
        try {
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, prettyPrint);
            m.marshal(cnf, destination);
        } catch (PropertyException e) {
            throw new SerializationException(e);
        } catch (JAXBException e) {
            throw new SerializationException(e);
        }
    }

    public void saveConfiguration(ImmutableConfiguration cnf, OutputStream destination) throws SerializationException {
        saveConfiguration(cnf, new OutputStreamWriter(destination));
    }

    public static class SerializationException extends IOException {
        public SerializationException(Throwable cause) {
            super(cause);
        }
    }

}
