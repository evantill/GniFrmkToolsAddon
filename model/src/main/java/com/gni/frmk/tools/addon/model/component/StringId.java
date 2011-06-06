package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentId;
import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:56
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "value"
})
public class StringId extends BaseComponentId<StringId> {

    private String value;

    private StringId() {
        super();
    }

    public StringId(Builder builder) {
        super(builder);
        this.value = builder.value;
    }

    @XmlElement
    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(StringId o) {
        return value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringId that = (StringId) o;
        return Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public static StringId build(String value) {
        return builder().value(value).validate().build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder extends BaseComponentId.Builder<Builder, StringId> {
        private String value;

        public Builder value(String value) {
            this.value = checkNotNull(value);
            return this;
        }

        @Override
        public StringId build() {
            return new StringId(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(value);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
