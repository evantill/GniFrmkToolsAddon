package com.gni.frmk.tools.addon.dispatch.wm.invoke.util;

import com.wm.data.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 27/10/10
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
public class PipelineTestUtils {

    private static final String SRV_OUT_PATTERN = "%s_out.xml";
    private static final String SRV_OUT_PATTERN_LOAD = "%s/%s_out.xml";
    private static final String SRV_IN_PATTERN = "%s_in.xml";
    private static final String SRV_PROPERTIES_PATTERN = "%s.properties";
    private static final String SRV_PROPERTIES_PATTERN_LOAD = "%s/%s.properties";

    private final Class classToTest;
    private final File traceDirectory;


    public PipelineTestUtils(Class testClass) {
        this.classToTest = testClass;
        try {
            URI uri = testClass.getResource(".").toURI();
            File file1 = new File(uri);
            String name = testClass.getSimpleName();
            this.traceDirectory = new File(file1, name);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("can not define trace directory", e);
        }
    }

    public File getTraceDirectory() {
        if (!traceDirectory.exists()) {
            boolean created = traceDirectory.mkdir();
            if (!created) {
                throw new IllegalStateException(String.format("can not create directory %s", traceDirectory));
            }
        }
        return traceDirectory;
    }

    public void saveServiceInput(String serviceName, IData pipeline) throws IOException {
        serviceName = secureServiceName(serviceName);
        File fic = new File(traceDirectory, String.format(SRV_IN_PATTERN, serviceName));
        if(!fic.getParentFile().exists()){
            boolean creationDone=fic.getParentFile().mkdirs();
            if(!creationDone){
                throw new IllegalStateException(String.format("can not create directory %s", fic.getParent()));
            }
        }
        PipelineUtils.savePipeline(pipeline, fic);
    }

    public void saveServiceOutput(String serviceName, IData pipeline) throws IOException {
        serviceName = secureServiceName(serviceName);
        File fic = new File(traceDirectory, String.format(SRV_OUT_PATTERN, serviceName));
        PipelineUtils.savePipeline(pipeline, fic);
    }

    public IData loadServiceOutput(String serviceName) throws IOException {
        serviceName = secureServiceName(serviceName);
        String resName = String.format(SRV_OUT_PATTERN_LOAD,
                classToTest.getSimpleName(),
                serviceName);
        InputStream in = classToTest.getResourceAsStream(resName);
        return PipelineUtils.loadPipeline(in);
    }

    public void saveServiceProperties(String serviceName, Properties props) throws IOException {
        serviceName = secureServiceName(serviceName);
        File fic = new File(traceDirectory, String.format(SRV_PROPERTIES_PATTERN, serviceName));
        FileOutputStream out = new FileOutputStream(fic);
        try {
            props.store(out, "do not edit by hand");
        } finally {
            out.close();
        }
    }

    public Properties loadServiceProperties(String serviceName) throws IOException {
        serviceName = secureServiceName(serviceName);
        Properties props = new Properties();
        String resName = String.format(SRV_PROPERTIES_PATTERN_LOAD,
                classToTest.getSimpleName(),
                serviceName);
        InputStream in = classToTest.getResourceAsStream(resName);
        if (in != null) {
            try {
                props.load(in);
            } finally {
                in.close();
            }
        }
        return props;
    }

    private String secureServiceName(String serviceName) {
        return serviceName.replace(':', '_');
    }

    public boolean existServiceOutput(String serviceName) {
        serviceName = secureServiceName(serviceName);
        String resName = String.format(SRV_OUT_PATTERN_LOAD,
                classToTest.getSimpleName(),
                serviceName);
        InputStream in = classToTest.getResourceAsStream(resName);
        boolean exist = (in != null);
        if (exist) {
            try {
                in.close();
            } catch (IOException ignore) {}
        }
        return exist;
    }
}
