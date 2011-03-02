package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.Configuration;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 04/11/10
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationUtils {

    public static Configuration loadConfiguration(Class classToTest, String name) throws Exception {
        String resName = String.format("%s/%s.xml", classToTest.getSimpleName(), name);
        InputStream input = classToTest.getResourceAsStream(resName);
        Serializer serializer = new Persister();
        return serializer.read(Configuration.class, input);
    }

}
