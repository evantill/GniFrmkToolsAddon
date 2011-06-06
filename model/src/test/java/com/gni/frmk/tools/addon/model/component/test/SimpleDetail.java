package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentDetail;
import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:17
 *
 * @author: e03229
 */
@XmlRootElement
public class SimpleDetail extends BaseComponentDetail<SimpleDetail> {
    private String description;

    public SimpleDetail() {
        super();
    }

    public SimpleDetail(Builder builder) {
        super(builder);
        description=builder.description;
    }

    @XmlElement(required = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(SimpleDetail o) {
        return description.compareTo(o.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleDetail that = (SimpleDetail) o;

        return Objects.equal(description,that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description);
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder extends BaseComponentDetail.Builder<Builder, SimpleDetail> {
        private String description;

        @Override
        public SimpleDetail build() {
            return new SimpleDetail(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(description);
            return this;
        }

        public Builder description(String value) {
            description = checkNotNull(value);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
