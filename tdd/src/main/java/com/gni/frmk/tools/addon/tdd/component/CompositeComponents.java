package com.gni.frmk.tools.addon.tdd.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.gni.frmk.tools.addon.tdd.api.CompositeComponent;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 14:25
 *
 * @author: e03229
 */
public class CompositeComponents {
    private static final Comparator<Component> compareByTypeAndId = new Comparator<Component>() {
        @Override
        public int compare(Component o1, Component o2) {
            return ComparisonChain.start()
                                  .compare(o1.getType(), o2.getType())
                                  .compare(o1.getId(), o2.getId())
                                  .result();
        }
    };

    public static void accept(CompositeComponent composite, ComponentVisitor visitor) {
        for (Component child : composite) {
            child.accept(visitor);
        }
        visitor.visitComponent(composite);
    }

    public static Iterator<Component> iterateOnComposite(Iterator<Component> compositeIterator) {
        return Iterators.unmodifiableIterator(compositeIterator);
    }

    public static Set<Component> createChildrenSet(Component... components) {
        return createChildrenSet(Arrays.asList(components));
    }

    public static Set<Component> createChildrenSet(Collection<Component> components) {
        Set<Component> children = Sets.newTreeSet(compareByTypeAndId);
        children.addAll(components);
        return children;
    }
}
