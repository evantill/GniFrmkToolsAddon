package com.gni.frmk.tools.addon.data2;

import com.gni.frmk.tools.addon.data2.Component;
import com.gni.frmk.tools.addon.data2.ComponentDetail;

import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public abstract class PackageComponent extends Component {
    protected static final String PACKAGE_NAME_KEY = "packageName";

    public PackageComponent(Builder<?> builder) {
        super(builder);
    }

    /**
     * empty constructor for jaxb.
     */
    protected PackageComponent() {
    }

    @XmlTransient
    public String getPackageName() {
        return findRequiredDetail(PACKAGE_NAME_KEY).getValue();
    }

    public void setPackageName(String packageName) {
        addDetail(new ComponentDetail(PACKAGE_NAME_KEY, packageName));
    }

    public static abstract class Builder<T extends Builder<T>> extends Component.Builder<T> {

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
