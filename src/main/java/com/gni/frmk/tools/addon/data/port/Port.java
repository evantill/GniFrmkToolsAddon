package com.gni.frmk.tools.addon.data.port;

import com.gni.frmk.tools.addon.data.component.ActivableComponentState;
import com.gni.frmk.tools.addon.data.component.Component;
import com.gni.frmk.tools.addon.data.component.ComponentPackageInfo;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 18:14:34
 * To change this template use File | Settings | File Templates.
 */
@XmlType
public class Port extends Component<ComponentPackageInfo, ActivableComponentState> {

    public Port(PortBuilder builder) {
        super(builder);
    }

    /**
     * Empty constructor only for jaxb.
     */
    private Port() {
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
    public <T extends Component<ComponentPackageInfo, ActivableComponentState>> int compareStatusTo(T that) {
        return ComparisonChain.start().compare(compareTo(that), 0).compare(getState(), that.getState()).result();
    }

}
