package com.gni.frmk.tools.addon.invoker.context;

import com.wm.data.*;
import com.wm.lang.ns.NSName;
import com.wm.util.coder.IDataXMLCoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/05/11
 * Time: 16:34
 *
 * @author: e03229
 */
public class RecordPipelineUtils {

    private final Class<?> clazz;

    private RecordPipelineUtilsStrategy strategy;

    public RecordPipelineUtils(Class<?> clazz, RecordPipelineUtilsStrategy strategy) {
        this.clazz = clazz;
        this.strategy = strategy;
    }

    public IData recordOutput(String id, IData output) {
        try {
            return recordIData("out", id, output);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public IData recordInput(String id, IData input) {
        try {
            return recordIData("in", id, input);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public IData recordIData(String prefix, String id, IData data) throws IOException, URISyntaxException {
        File currentDir = new File(clazz.getResource(".").toURI());
        File pipelineDir = new File(currentDir, String.format("%s_pipelines", clazz.getSimpleName()));
        if (!pipelineDir.exists()) {
            boolean created = pipelineDir.mkdir();
            assert created;
        }
        File outputFile = new File(pipelineDir, String.format("%s_%s.xml", prefix, id));
        FileOutputStream out = new FileOutputStream(outputFile);
        try {
            IDataXMLCoder coder = new IDataXMLCoder();
            coder.encode(out, data);
        } finally {
            out.close();
        }
        return data;
    }

    public IData replayIData(String prefix, String id) {
        try {
            IDataXMLCoder coder = new IDataXMLCoder();
            return coder.decode(openResource(prefix, id));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private InputStream openResource(String prefix, String id) {
        String resName = String.format("%s_pipelines/%s_%s.xml", clazz.getSimpleName(), prefix, id);
        return clazz.getResourceAsStream(resName);
    }

    public String generateId(NSName serviceName) {
        return strategy.generateId(serviceName);
    }

    public boolean exist(String prefix, NSName serviceName) {
        InputStream in = openResource(prefix, strategy.checkNextId(serviceName));
        return in != null;
    }
}
