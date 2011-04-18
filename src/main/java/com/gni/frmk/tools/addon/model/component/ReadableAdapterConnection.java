package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.ImmutableAdapterConnection.MutableAdapterConnection;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.EnableState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 16:54
 *
 * @author: e03229
 */
public interface ReadableAdapterConnection
        extends ReadableAdapterTypeAware<ImmutableAdapterConnection, MutableAdapterConnection, StringId, EnableState> {

    @NotNull
    @XmlElement
    String getAlias();

    interface WritableAdapterConnection
            extends ReadableAdapterConnection,
            WritableAdapterTypeAware<ImmutableAdapterConnection, MutableAdapterConnection, StringId, EnableState> {

        void setAlias(String value);
    }
}
