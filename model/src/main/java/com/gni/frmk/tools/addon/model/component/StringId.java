package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentId;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentId.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class StringId extends BaseComponentId<StringId> {

    private String value;

    private StringId() {
        super();
    }

    public StringId(Builder builder) {
        super(builder);
        this.value=builder.value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(StringId o) {
        return value.compareTo(o.value);
    }

    public static StringId build(String value) {
        return builder().value(value).validate().build();
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder extends BaseComponentId.Builder<Builder,StringId> {
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
