package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ComponentId;
import com.gni.frmk.tools.addon.model.api.ComponentState;
import com.gni.frmk.tools.addon.model.api.ImmutableComponent;
import com.gni.frmk.tools.addon.model.api.ImmutableComponent.MutableComponent;
import com.gni.frmk.tools.addon.model.api.ReadableComponent;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 15:54
 *
 * @author: e03229
 */
public interface ReadablePackageAware
        <IC extends ImmutableComponent<IC, MC, I, S>,
                MC extends MutableComponent<IC, MC, I, S>,
                I extends ComponentId,
                S extends ComponentState>
        extends ReadableComponent<IC, MC, I, S> {

    @NotNull
    @XmlElement
    String getPackageName();

    interface WritablePackageAware
            <IC extends ImmutableComponent<IC, MC, I, S>,
                    MC extends MutableComponent<IC, MC, I, S>,
                    I extends ComponentId,
                    S extends ComponentState>
            extends
            ReadablePackageAware<IC, MC, I, S>,
            WritableComponent<IC, MC, I, S> {

        void setPackageName(String value);
    }
}
