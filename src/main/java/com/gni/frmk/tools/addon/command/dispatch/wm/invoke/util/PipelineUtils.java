package com.gni.frmk.tools.addon.command.dispatch.wm.invoke.util;

import com.wm.data.*;
import com.wm.util.coder.IDataXMLCoder;

import java.io.*;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.               Properties props
 * User: e03229
 * Date: 27/10/10
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
public class PipelineUtils {

    public static void savePipeline(final IData pipeline, final OutputStream out) throws IOException {
        IDataXMLCoder coder = new IDataXMLCoder();
        coder.encode(out, pipeline);
    }

    public static void savePipeline(final IData pipeline, File fic) throws IOException {
        FileOutputStream out = new FileOutputStream(fic);
        try {
            savePipeline(pipeline, out);
        } finally {
            out.close();
        }
    }

    public static IData loadPipeline(final InputStream in) throws IOException {
        IDataXMLCoder coder = new IDataXMLCoder();
        return coder.decode(in);
    }

    public static IData loadPipeline(final File fic) throws IOException {
        FileInputStream in = new FileInputStream(fic);
        try {
            return loadPipeline(in);
        } finally {
            in.close();
        }
    }

    public static IData loadPipeline(final URL url) throws IOException {
        InputStream in = url.openStream();
        try {
            return loadPipeline(in);
        } finally {
            in.close();
        }
    }
}
