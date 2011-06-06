package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentDetail;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:30
 *
 * @author: e03229
 */
@XmlRootElement
public class NoDetail extends BaseComponentDetail<NoDetail> {

    private static final NoDetail singleton = new NoDetail();
    private static final Builder singletonBuilder = new Builder();

    private NoDetail() {
    }

    @Override
    public int compareTo(NoDetail o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NoDetail;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public static final NoDetail newInstance() {
        return singleton;
    }

    public static Builder builder(){
        return singletonBuilder;
    }

    @XmlTransient
    public static final class Builder extends BaseComponentDetail.Builder<Builder,NoDetail>{
        @Override
        public NoDetail build() {
            return singleton;
        }

        @Override
        public Builder validate() {
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
