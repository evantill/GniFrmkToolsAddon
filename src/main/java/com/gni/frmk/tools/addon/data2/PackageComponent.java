package com.gni.frmk.tools.addon.data2;

import com.gni.frmk.tools.addon.data2.Component;
import com.gni.frmk.tools.addon.data2.ComponentDetail;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public abstract class PackageComponent<S extends ComponentState> extends Component<S> {
    protected static final String PACKAGE_NAME_KEY = "packageName";

    public PackageComponent(Builder<S,?> builder) {
        super(builder);
    }

    /**
     * empty constructor for jaxb.
     */
    protected PackageComponent() {
    }

    public String getPackageName() {
        return findRequiredDetail(PACKAGE_NAME_KEY).getValue();
    }

    public void setPackageName(String packageName) {
        addDetail(new ComponentDetail(PACKAGE_NAME_KEY, packageName));
    }

    public static abstract class Builder<S extends ComponentState,T extends Builder<S,T>> extends Component.Builder<S,T> {

        private String packageName;

        public T packageName(String value) {
            packageName = checkNotNull(value, "packageName required");
            addDetail(PACKAGE_NAME_KEY, packageName);
            return self();
        }

        @Override
        public T check() throws CheckException {
            super.check();
            try {
                checkNotNull(packageName, "packageName required");
                return self();
            } catch (NullPointerException npex) {
                throw new CheckException(npex);
            }
        }

    }
}
