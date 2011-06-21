package com.gni.frmk.tools.addon.repository;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.module.ModuleResource.ModelContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 11:38
 *
 * @author: e03229
 */
public class ConfigurationSerializer {

    private final boolean prettyPrint;
    private final Set<ModelContext> contexts;
    private final JAXBContext jaxbContext;

    public ConfigurationSerializer(Set<ModelContext> contexts) {
        this.contexts = contexts;
        prettyPrint = true;
        jaxbContext = createJAXBContext(contexts);
    }

    private JAXBContext createJAXBContext(Set<ModelContext> contexts) {
        StringBuilder contextPathBuilder = new StringBuilder();
        for (ModelContext ctx : contexts) {
            for (Package p : ctx.getModelPackages()) {
                contextPathBuilder.append(p.getName()).append(':');
            }
        }
        try {
            return JAXBContext.newInstance(contextPathBuilder.toString());
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public Configuration loadConfiguration(InputStream source) throws SerializationException {
        return loadConfiguration(new InputStreamReader(source));
    }

    public Configuration loadConfiguration(Reader source) throws SerializationException {
        try {

            Unmarshaller u = jaxbContext.createUnmarshaller();
            return (Configuration) u.unmarshal(source);
        } catch (JAXBException e) {
            throw new SerializationException(e);
        }
    }

    public void saveConfiguration(Configuration cnf, Writer destination) throws SerializationException {
        try {
            Marshaller m = jaxbContext.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, prettyPrint);
            m.marshal(cnf, destination);
        } catch (PropertyException e) {
            throw new SerializationException(e);
        } catch (JAXBException e) {
            throw new SerializationException(e);
        }
    }

    public void saveConfiguration(Configuration cnf, OutputStream destination) throws SerializationException {
        saveConfiguration(cnf, new OutputStreamWriter(destination));
    }

    public static class SerializationException extends IOException {
        public SerializationException(Throwable cause) {
            super(cause);
        }
    }

}
