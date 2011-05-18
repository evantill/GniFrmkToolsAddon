package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.io.Resources.readLines;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 17:16
 *
 * @author: e03229
 */
public abstract class BaseModuleResource<C extends ExecutionContext> implements ModuleResource<C> {

    /**
     * @noinspection unchecked
     */
    @Override
    public final List<ActionHandler<?, ?, C>> getActionHandlers() {
        try {
            List<ActionHandler<?, ?, C>> handlers = Lists.newArrayList();
            for (String line : getHandlerClassList()) {
                if (line != null && line.trim().length() > 0) {
                    String className = line.trim();
                    handlers.add((ActionHandler<?, ?, C>) Class.forName(className).newInstance());
                }
            }
            return handlers;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("error loading handler for module " + getClass().getName(), e);
        } catch (InstantiationException e) {
            throw new IllegalStateException("error instantiating handler for module " + getClass().getName(), e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("error creating handler for module " + getClass().getName(), e);
        } catch (IOException e) {
            throw new IllegalStateException("error reading properties for module " + getClass().getName(), e);
        }
    }

    private List<String> getHandlerClassList() throws IOException {
        String confResName = String.format("%s.properties", getClass().getSimpleName());
        return readLines(getClass().getResource(confResName), UTF_8);
    }
}
