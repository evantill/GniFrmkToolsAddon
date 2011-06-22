package com.gni.frmk.tools.addon.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;
import org.junit.rules.ExternalResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 15:05
 *
 * @author: e03229
 */
public class XmlTestRule extends ExternalResource {

    private JAXBContext context;
    private Set<String> contextPathList = Sets.newHashSet();
    private Class<?>[] objectTypes;

    public XmlTestRule(Package... packages) {
        for (Package p : packages) {
            addContext(p.getName());
        }
    }

    public XmlTestRule(Class<?>... componentType) {
        setObjectTypes(componentType);
    }

    private void addContext(String path) {
        contextPathList.add(path);
    }

    private void setObjectTypes(Class<?>... objectTypes) {
        this.objectTypes = objectTypes;
    }

    @Override
    protected void before() throws Throwable {
        if (objectTypes != null) {
            context = JAXBContext.newInstance(objectTypes);
        }
        if(contextPathList.size()>0){
            StringBuilder pathBuilder = new StringBuilder();
            for (String path : contextPathList) {
                pathBuilder.append(path).append(":");
            }
            context = JAXBContext.newInstance(pathBuilder.toString());
        }

    }

    @Override
    protected void after() {
        context = null;
        contextPathList.clear();
    }

    public <T> void save(T data, Writer destination, boolean prettyPrint) {
        try {
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, prettyPrint);
            m.marshal(data, destination);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> T load(Reader source, Class<T> type) {
        try {
            Unmarshaller u = context.createUnmarshaller();
            return type.cast(u.unmarshal(source));
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> T load(Class classToTest, String name, Class<T> type) {
        String xml = loadXml(name, classToTest);
        try {
            Unmarshaller u = context.createUnmarshaller();
            return type.cast(u.unmarshal(new StringReader(xml)));
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public String loadXml(final String name, final Class testClassName) {
        try {
            URL resource = testClassName.getResource(String.format("%s_%s.xml", testClassName.getSimpleName(), name));
            return Resources.toString(resource, Charsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
