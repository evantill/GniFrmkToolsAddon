package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentDetail;
import com.google.common.collect.ComparisonChain;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 16:22
 *
 * @author: e03229
 */
public class PackageDetail extends BaseComponentDetail<PackageDetail> {
    private String packageName;

    public PackageDetail(Builder builder) {
        super(builder);
        this.packageName = builder.packageName;
    }

    private PackageDetail() {
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static PackageDetail build(String packageName) {
        return builder().packageName(packageName).validate().build();
    }

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
