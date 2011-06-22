package com.gni.frmk.tools.addon.repository;

import com.gni.frmk.tools.addon.model.ModelResource;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.google.common.collect.Sets;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;
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
@Singleton
public class ConfigurationSerializer {

    private final boolean prettyPrint;
    private final JAXBContext jaxbContext;

    @Inject
    public ConfigurationSerializer(Instance<ModelResource> contexts) {
        prettyPrint = true;
        jaxbContext = createJAXBContext(Sets.<ModelResource>newHashSet(contexts));
    }

    private JAXBContext createJAXBContext(Set<ModelResource> contexts) {
        StringBuilder contextPathBuilder = new StringBuilder();
        for (ModelResource res : contexts) {
            for (Package p : res.getModelPackages()) {
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
