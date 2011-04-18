package com.gni.frmk.tools.addon.model.api;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.model.api.ImmutableComponent.MutableComponent;
import com.gni.frmk.tools.addon.model.api.immutable.ReadableObject;
import com.gni.frmk.tools.addon.model.api.immutable.WritableObject;
import com.gni.frmk.tools.addon.model.component.detail.SimpleDetail;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 15:32
 *
 * @author: e03229
 */
public interface ReadableComponent
        <IC extends ImmutableComponent<IC, MC, I, S>,
                MC extends MutableComponent<IC, MC, I, S>,
                I extends ComponentId,
                S extends ComponentState>
        extends ReadableObject<IC, IC, MC>,
        ConfigurationVisited {

    @NotNull
    @Valid
    @XmlElementRef
    I getComponentId();

    @NotNull
    @XmlElementRef
    @XmlElementWrapper
    List<SimpleDetail> getDetails();

    @NotNull
    @XmlElement
    ComponentType getType();

    @NotNull
    @XmlElementRef
    @Valid
    S getState();

    interface WritableComponent
            <IC extends ImmutableComponent<IC, MC, I, S>,
                    MC extends MutableComponent<IC, MC, I, S>,
                    I extends ComponentId,
                    S extends ComponentState>
            extends WritableObject<IC, IC, MC>,
            ReadableComponent<IC, MC, I, S> {

        void setComponentId(I value);

        void addDetail(SimpleDetail value);

        void setType(ComponentType value);

        void setState(S value);
    }
}
