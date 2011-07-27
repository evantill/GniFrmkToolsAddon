package com.gni.frmk.tools.addon.tdd.impl.beta;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.util.UnimplementedMethodException;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:24
 *
 * @author: e03229
 */
public class BetaComponent implements Component<BetaComponent, BetaComponentState> {

    private BetaComponentId id;
    private BetaComponentType type;
    private BetaComponentState state;

    public BetaComponent() {
        id = new BetaComponentId();
        type = new BetaComponentType();
        state = new BetaComponentState();
    }

    @Override
    public void open() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void open(BetaComponentState state) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void close() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void close(BetaComponentState state) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public int compareTo(BetaComponent o) {
        return ComparisonChain.start()
                              .compare(this.hashCode(), o.hashCode())
                              .result();
    }

    @Override
    public BetaComponentId getId() {
        return id;
    }

    public void setId(BetaComponentId id) {
        this.id = id;
    }

    @Override
    public BetaComponentType getType() {
        return type;
    }

    public void setType(BetaComponentType type) {
        this.type = type;
    }

    @Override
    public BetaComponentState getState() {
        return state;
    }

    public void setState(BetaComponentState state) {
        this.state = state;
    }
}
