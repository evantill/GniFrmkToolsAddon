package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentDetail;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 16:22
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "packageName"
})
public class PackageDetail extends BaseComponentDetail<PackageDetail> {
    private String packageName;

    public PackageDetail(Builder builder) {
        super(builder);
        this.packageName = builder.packageName;
    }

    private PackageDetail() {
    }

    @XmlElement
    public String getPackageName() {
        return packageName;
    }

    private void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int compareTo(PackageDetail o) {
        return ComparisonChain.start()
                              .compare(packageName, o.packageName)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackageDetail that = (PackageDetail) o;

        return Objects.equal(packageName, that.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(packageName);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static PackageDetail build(String packageName) {
        return builder().packageName(packageName).validate().build();
    }

    @XmlTransient
    public static final class Builder extends BaseComponentDetail.Builder<Builder, PackageDetail> {
        private String packageName;

        public Builder packageName(String value) {
            packageName = checkNotNull(value);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public PackageDetail build() {
            return new PackageDetail(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(packageName);
            return this;
        }
    }

}
