package com.gni.frmk.tools.addon.tdd.impl.alpha;

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
public class AlphaComponent implements Component<AlphaComponent, AlphaComponentState> {

    private AlphaComponentId id;
    private AlphaComponentType type;
    private AlphaComponentState state;

    public AlphaComponent() {
        id=new AlphaComponentId();
        type = new AlphaComponentType();
        state=new AlphaComponentState();
    }

    @Override
    public void open() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void open(AlphaComponentState state) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void close() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void close(AlphaComponentState state) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

     @Override
    public int compareTo(AlphaComponent o) {
        return ComparisonChain.start()
                              .compare(this.hashCode(), o.hashCode())
                              .result();
    }

    @Override
    public AlphaComponentId getId() {
        return id;
    }

    public void setId(AlphaComponentId id) {
        this.id = id;
    }

    @Override
    public AlphaComponentType getType() {
        return type;
    }

    public void setType(AlphaComponentType type) {
        this.type = type;
    }

    @Override
    public AlphaComponentState getState() {
        return state;
    }

    public void setState(AlphaComponentState state) {
        this.state = state;
    }
}
