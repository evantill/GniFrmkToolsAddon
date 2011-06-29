package com.gni.frmk.tools.addon.invoker.context;

import com.wm.lang.ns.NSName;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/06/11
 * Time: 19:25
 *
 * @author: e03229
 */
public class RecordPipelineUtilsSimpleStrategy implements RecordPipelineUtilsStrategy {
    private AtomicInteger index = new AtomicInteger(0);

    @Override
    public synchronized String generateId(NSName serviceName) {
        return newId(serviceName, index.incrementAndGet());
    }

    @Override
    public String checkNextId(NSName serviceName) {
        return newId(serviceName, index.get()+1);
    }

    private String newId(NSName serviceName, int indx) {
        return String.format("%s_%d", serviceName.getFullName()
                                                 .replace(':', '.')
                                                 .replace('.', '_'), indx);
    }
}
