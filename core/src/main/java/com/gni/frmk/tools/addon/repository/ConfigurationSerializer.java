package com.gni.frmk.tools.addon.repository;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.module.ModuleManager;

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

    private final ModuleManager<?> manager;
    private final boolean prettyPrint;

    public ConfigurationSerializer(ModuleManager<?> manager) {
        this.manager = manager;
        prettyPrint = true;
    }

    public Configuration loadConfiguration(InputStream source) throws SerializationException {
        return loadConfiguration(new InputStreamReader(source));
    }

    public Configuration loadConfiguration(Reader source) throws SerializationException {
        try {
            Unmarshaller u = manager.getContext().createUnmarshaller();
            return (Configuration) u.unmarshal(source);
        } catch (JAXBException e) {
            throw new SerializationException(e);
        }
    }

    public void saveConfiguration(Configuration cnf, Writer destination) throws SerializationException {
        try {
            Marshaller m = manager.getContext().createMarshaller();
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
