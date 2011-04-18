package com.gni.frmk.tools.addon.oldies.services;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
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

    public ImmutableConfiguration loadConfiguration(String name) {
        File input = new File(packageConfigDir, String.format("%s.xml", name));
        if (input.exists() && input.canRead() && input.isFile()) {
            try {
                JAXBContext ctx = JAXBContext.newInstance(ImmutableConfiguration.class);
                return (ImmutableConfiguration) ctx.createUnmarshaller().unmarshal(new FileInputStream(input));
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


    public ImmutableConfiguration saveConfiguration(ImmutableConfiguration cnf) {
        File output = new File(packageConfigDir, String.format("%s.xml", cnf.getName()));
        ImmutableConfiguration toSave = cnf;
        if (output.exists()) {
            toSave= ImmutableConfiguration.builder().from(cnf).touch(new Date()).build();
        }
        try {
            JAXBContext ctx = JAXBContext.newInstance(ImmutableConfiguration.class);
            ctx.createMarshaller().marshal(cnf,new FileOutputStream(output));
        } catch (Exception e) {
            throw  new RuntimeException("saveConfiguration", e);
        }
        return toSave;
    }

    public ImmutableConfiguration clearConfiguration(String configurationName)  {
        File output = new File(packageConfigDir, String.format("%s.xml", configurationName));
        ImmutableConfiguration cnf = loadConfiguration(configurationName);
        if (output.exists()) {
            cnf=saveConfiguration(ImmutableConfiguration.builder().from(cnf).clear().build());
        }
        return cnf;
    }

    public ImmutableConfiguration closeAllConfiguration(ImmutableConfiguration cnf) {
        DisableStatusVisitor visitor = new DisableStatusVisitor();
//        ParseStrategy strategy = new ParseStrategy(visitor);
//        strategy.execute(cnf);
        return cnf;
    }

    public ImmutableConfiguration openAllConfiguration(ImmutableConfiguration cnf) {
//        EnableStatusVisitor visitor = new EnableStatusVisitor();
//        ParseStrategy strategy = new ParseStrategy(visitor);
//        strategy.execute(cnf);
        return cnf;
    }
}
