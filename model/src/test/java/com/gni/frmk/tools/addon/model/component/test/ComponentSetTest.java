package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.Component;
import com.google.common.collect.Sets;
import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;


/**
 * Created by IntelliJ IDEA.
 * Date: 07/06/11
 * Time: 10:06
 *
 * @author: e03229
 */
public class ComponentSetTest {

    private Component1 createC1() {
        return Component1.builder()
                         .id(Component1Id.builder().value("cle 1").build())
                         .detail(SimpleDetail.builder().description("description composant 1").build())
                         .state(Component1State.builder().enable(true).build())
                         .build();
    }

    private Component2 createC2() {
        return Component2.builder()
                         .id(Component2Id.builder().value(2).build())
                         .detail(SimpleDetail.builder().description("description composant 2").build())
                         .state(Component2State.builder().enable(true).active(true).build())
                         .build();
    }

    @Test
    public void testComponentSet() {
        Set<Component<?, ?, ?, ?, ?>> collection = Sets.newHashSet();
        Component1 c1 = createC1();
        Component1 c1Bis = createC1();
        Component2 c2 = createC2();
        Component2 c2Bis = createC2();

        collection.add(c1);
        collection.add(c2);
        collection.add(c1Bis);
        collection.add(c2Bis);
        collection.add(c1);
        collection.add(c2);

        Assertions.assertThat(collection).hasSize(2).containsOnly(c1, c2);
    }

    @Test
    public void testComponentEquals() {
        Component1 c1 = createC1();
        Component1 c1Bis = createC1();
        Component2 c2 = createC2();
        Component2 c2Bis = createC2();

        assertReflectionEquals(c1, c1Bis);
        assertReflectionEquals(c2, c2Bis);
    }

    @Test
    public void testComponentTreeSet() {
        TreeSet<Component<?, ?, ?, ?, ?>> collection = Sets.newTreeSet();
        Component1 c1 = createC1();
        Component1 c1Bis = createC1();
        Component2 c2 = createC2();
        Component2 c2Bis = createC2();

        collection.add(c1);
        collection.add(c2);
        collection.add(c1Bis);
        collection.add(c2Bis);
        collection.add(c1);
        collection.add(c2);

        Assertions.assertThat(collection).hasSize(2).containsOnly(c1, c2);
    }
}
