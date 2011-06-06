package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 16:19
 *
 * @author: e03229
 */
@XmlRootElement
public class JmsAliasType
        extends BaseComponentType<JmsAliasType, JmsAlias,
        StringId, ConnectableState, JmsAliasDetail> {

    public static final JmsAliasType TYPE = new JmsAliasType();

    private JmsAliasType() {
        super(JmsAlias.class, StringId.class, ConnectableState.class, JmsAliasDetail.class);
    }

    @Override
    public boolean isInput() {
        return false;
    }

    @Override
    public boolean isOutput() {
        return true;
    }

    @Override
    public JmsAlias.Builder componentBuilder() {
        return JmsAlias.builder();
    }

    @Override
    public StringId.Builder idBuilder() {
        return StringId.builder();
    }

    @Override
    public ConnectableState.Builder stateBuilder() {
        return ConnectableState.builder();
    }

    @Override
    public JmsAliasDetail.Builder detailBuilder() {
        return JmsAliasDetail.builder();
    }

    public static JmsAliasType newInstance() {
        return TYPE;
    }
}
