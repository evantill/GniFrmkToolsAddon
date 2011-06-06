package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentId;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:37
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "packageName",
        "id"
})
public class PackageAndStringId extends BaseComponentId<PackageAndStringId> {
    private String packageName;
    private String id;

    private PackageAndStringId() {
    }

    public PackageAndStringId(Builder builder) {
        super(builder);
        packageName = builder.packageName;
        id = builder.id;
    }

    @XmlElement
    public String getPackageName() {
        return packageName;
    }

    private void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @XmlElement
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(PackageAndStringId o) {
        return ComparisonChain.start()
                              .compare(packageName, o.packageName)
                              .compare(id, o.id)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackageAndStringId that = (PackageAndStringId) o;

        return Objects.equal(packageName, that.packageName)
               && Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(packageName, id);
    }

    public static PackageAndStringId newInstance(String packageName, String id) {
        return builder().packageName(packageName).id(id).validate().build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder extends BaseComponentId.Builder<Builder, PackageAndStringId> {
        private String id;
        private String packageName;

        @Override
        public PackageAndStringId build() {
            return new PackageAndStringId(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(id);
            checkNotNull(packageName);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }

        public Builder packageName(String packageName) {
            this.packageName = checkNotNull(packageName);
            return this;
        }

        public Builder id(String id) {
            this.id = checkNotNull(id);
            return this;
        }
    }
}


