package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentDetail;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 16:44
 *
 * @author: e03229
 */
@XmlRootElement
public class JmsAliasDetail
        extends BaseComponentDetail<JmsAliasDetail> {
    private String description;

    public JmsAliasDetail(Builder builder) {
        this.description = builder.description;
    }

    private JmsAliasDetail() {
    }

    @Override
    public int compareTo(JmsAliasDetail o) {
        return ComparisonChain.start()
                              .compare(description, o.description)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmsAliasDetail that = (JmsAliasDetail) o;

        return Objects.equal(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description);
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static JmsAliasDetail build(String decription) {
        return builder().description(decription).validate().build();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponentDetail.Builder<Builder, JmsAliasDetail> {

        public String description;

        public Builder description(String value) {
            this.description = checkNotNull(value);
            return this;
        }

        @Override
        public JmsAliasDetail build() {
            return new JmsAliasDetail(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(description);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
