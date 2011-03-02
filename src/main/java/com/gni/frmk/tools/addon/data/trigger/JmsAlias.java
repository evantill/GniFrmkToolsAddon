package com.gni.frmk.tools.addon.data.trigger;

import com.gni.frmk.tools.addon.data.component.ActivableComponentState;
import com.gni.frmk.tools.addon.data.component.Component;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 30 sept. 2010
 * Time: 19:50:14
 * To change this template use File | Settings | File Templates.
 */
//TODO place la relation ENABLE DISABLED avec CONNECTED DISCONNECTED qq part
@XmlJavaTypeAdapter(JmsAliasBuilder.CustomXmlAdapter.class)
public class JmsAlias extends Component<JmsAliasInfo, ActivableComponentState> {

    public JmsAlias(JmsAliasBuilder builder) {
        super(builder);
    }

    /**
     * Empty constructor only for jaxb.
     */
    private JmsAlias() {
    }

    public void accept(ConfigurationVisitor visitor) {
        visitor.visit(this);
    }


    /**
     * Note: this class has a natural ordering that is inconsistent with equals.
     *
     * @param that element to compare
     * @return 0 if same status
     */
    @Override
    public <T extends Component<JmsAliasInfo, ActivableComponentState>> int compareStatusTo(T that) {
        return ComparisonChain.start().compare(compareTo(that), 0).compare(getState(), that.getState()).result();
    }

}
