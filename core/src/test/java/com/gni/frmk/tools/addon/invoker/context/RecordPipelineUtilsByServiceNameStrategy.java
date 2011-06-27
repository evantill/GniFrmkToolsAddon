package com.gni.frmk.tools.addon.invoker.context;

import com.google.common.collect.Maps;
import com.wm.lang.ns.NSName;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/06/11
 * Time: 19:25
 *
 * @author: e03229
 */
public class RecordPipelineUtilsByServiceNameStrategy implements RecordPipelineUtilsStrategy {
    private Map<String, AtomicInteger> indexes = Maps.newHashMap();

      public synchronized String generateId(NSName serviceName) {
        String key = serviceName.getFullName();
        AtomicInteger index = indexes.get(key);
        if (index == null) {
            index = new AtomicInteger(0);
            indexes.put(key, index);
        }
        return String.format("%s_%d", serviceName.getFullName()
                                                 .replace(':', '.')
                                                 .replace('.', '_'), index.incrementAndGet());
    }
}
