package com.gni.frmk.tools.addon.model;

import com.google.common.collect.Sets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:02
 *
 * @author: e03229
 */
public class ModelResourceManager {
    private final Set<ModelResource> resources = Sets.newHashSet();

    private JAXBContext context;
    private final ReentrantLock contextLock = new ReentrantLock();

    public ResourceRegistration register(ModelResource resource) {
        if (resource != null) {
            resources.add(resource);
            cleanContext();
        }
        return new ResourceRegistration(this, resource);
    }

    public boolean unregister(ModelResource resource) {
        if (resource != null) {
            boolean removed = resources.remove(resource);
            if (removed) {
                cleanContext();
            }
            return removed;
        } else {
            return false;
        }
    }

    public static final class ResourceRegistration {
        private final ModelResourceManager manager;
        private final ModelResource resouce;

        public ResourceRegistration(ModelResourceManager manager, ModelResource resouce) {
            this.manager = manager;
            this.resouce = resouce;
        }

        public boolean unregister() {
            return manager.unregister(resouce);
        }
    }

    private String defineContextPath() {
        StringBuilder contextPathBuilder = new StringBuilder();
        contextPathBuilder.append(getClass().getPackage().getName()).append(':');
        for (ModelResource resource : resources) {
            contextPathBuilder.append(resource.getContextPath()).append(':');
        }
        return contextPathBuilder.toString();
    }

    public JAXBContext createContext() throws JAXBException {
        initContext();
        return context;
    }

    private void cleanContext() {
        contextLock.lock();
        try {
            context = null;
        } finally {
            contextLock.unlock();
        }
    }

    private void initContext() throws JAXBException {
        if (context == null) {
            contextLock.lock();
            try {
                if (context == null) {
                    context = JAXBContext.newInstance(defineContextPath());
                }
            } finally {
                contextLock.unlock();
            }
        }
    }
}
