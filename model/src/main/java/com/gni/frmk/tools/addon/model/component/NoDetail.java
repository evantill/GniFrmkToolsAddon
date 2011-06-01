package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:30
 *
 * @author: e03229
 */
public class NoDetail extends BaseComponentDetail<NoDetail> {

    private static final NoDetail singleton = new NoDetail();
    private static final Builder singletonBuilder = new Builder();

    private NoDetail() {
    }

    @Override
    public int compareTo(NoDetail o) {
        assert this == o;
        return 0;
    }

    public static final NoDetail newInstance() {
        return singleton;
    }

    public static Builder builder(){
        return singletonBuilder;
    }

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
