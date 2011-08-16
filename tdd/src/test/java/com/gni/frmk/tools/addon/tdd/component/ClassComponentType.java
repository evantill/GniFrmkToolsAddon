package com.gni.frmk.tools.addon.tdd.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentType;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 14:36
 *
 * @author: e03229
 */
public class ClassComponentType<C extends Component> implements ComponentType<ClassComponentType<C>> {
    private final Class<? extends Component> type;
    private final IOType iOType;

    public ClassComponentType(Class<? extends Component> type, IOType iOType) {
        this.type = type;
        this.iOType = iOType;
    }

    @Override
    public int compareTo(ClassComponentType<C> o) {
        return ComparisonChain.start()
                              .compare(type.getName(), o.type.getName())
                              .result();
    }

    public Class<? extends Component> getType() {
        return type;
    }

    public IOType getIOType() {
        return iOType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ClassComponentType");
        sb.append("{type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public static <C extends Component> ClassComponentType<C> createForComponent(C component, IOType iOType) {
        return new ClassComponentType<C>(component.getClass(), iOType);
    }
}
