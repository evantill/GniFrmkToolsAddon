package com.gni.frmk.tools.addon.oldies.services;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.visitor.DisableStatusVisitor;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;

import javax.xml.bind.JAXBContext;
import java.io.*;
import java.util.Date;

/**
 * Cette impletion simple va sauvegarder la configuration dans un fichier xml dans le repertoire config du package.
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 30 sept. 2010
 * Time: 11:12:05
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationService {

    private final File packageConfigDir;

    public ConfigurationService(IntegrationServerUtil utils) {
        packageConfigDir = utils.getCurrentPackageConfigDir();
    }

    public Configuration loadConfiguration(String name) {
        File input = new File(packageConfigDir, String.format("%s.xml", name));
        if (input.exists() && input.canRead() && input.isFile()) {
            try {
                JAXBContext ctx = JAXBContext.newInstance(Configuration.class);
                return (Configuration) ctx.createUnmarshaller().unmarshal(new FileInputStream(input));
            } catch (Exception e) {
                throw new RuntimeException("loadConfiguration", e);
            }
        } else {
            throw new IllegalArgumentException(String.format("configuration name %s not found", name));
        }
    }

    public String loadRawConfiguration(String name)  {
        File input = new File(packageConfigDir, String.format("%s.xml", name));
        if (input.exists() && input.canRead() && input.isFile()) {
            try {
                FileReader in = new FileReader(input);
                try {
                    StringWriter out = new StringWriter();
                    try {
                        CharStreams.copy(in, out);
                        return out.toString();
                    } finally {
                        Closeables.closeQuietly(out);
                    }
                } finally {
                    Closeables.closeQuietly(in);
                }
            } catch (IOException e) {
                throw new RuntimeException("loadRawConfiguration", e);
            }
        } else {
            throw new IllegalArgumentException(String.format("configuration name %s not found", name));
        }
    }


    public Configuration saveConfiguration(Configuration cnf) {
        File output = new File(packageConfigDir, String.format("%s.xml", cnf.getName()));
        Configuration toSave = cnf;
        if (output.exists()) {
            toSave=Configuration.builder().from(cnf).touch(new Date()).build();
        }
        try {
            JAXBContext ctx = JAXBContext.newInstance(Configuration.class);
            ctx.createMarshaller().marshal(cnf,new FileOutputStream(output));
        } catch (Exception e) {
            throw  new RuntimeException("saveConfiguration", e);
        }
        return toSave;
    }

    public Configuration clearConfiguration(String configurationName)  {
        File output = new File(packageConfigDir, String.format("%s.xml", configurationName));
        Configuration cnf = loadConfiguration(configurationName);
        if (output.exists()) {
            cnf=saveConfiguration(Configuration.builder().from(cnf).clear().build());
        }
        return cnf;
    }

    public Configuration closeAllConfiguration(Configuration cnf) {
        DisableStatusVisitor visitor = new DisableStatusVisitor();
//        ParseStrategy strategy = new ParseStrategy(visitor);
//        strategy.execute(cnf);
        return cnf;
    }

    public Configuration openAllConfiguration(Configuration cnf) {
//        EnableStatusVisitor visitor = new EnableStatusVisitor();
//        ParseStrategy strategy = new ParseStrategy(visitor);
//        strategy.execute(cnf);
        return cnf;
    }
}
