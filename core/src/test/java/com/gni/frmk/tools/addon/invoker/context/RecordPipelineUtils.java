package com.gni.frmk.tools.addon.invoker.context;

import com.wm.data.*;
import com.wm.lang.ns.NSName;
import com.wm.util.coder.IDataXMLCoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/05/11
 * Time: 16:34
 *
 * @author: e03229
 */
public class RecordPipelineUtils {

    private final Class<?> clazz;
    private AtomicInteger index = new AtomicInteger(0);

    public RecordPipelineUtils(Class<?> clazz) {
        this.clazz = clazz;
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
            String resName = String.format("%s_pipelines/%s_%s.xml", clazz.getSimpleName(), prefix, id);
            IDataXMLCoder coder = new IDataXMLCoder();
            return coder.decode(clazz.getResourceAsStream(resName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String generateId(NSName serviceName) {
        return String.format("%s_%d", serviceName.getFullName()
                                                 .replace(':', '.')
                                                 .replace('.', '_'), index.incrementAndGet());
    }
}
