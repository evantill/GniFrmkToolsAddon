package com.gni.frmk.tools.addon.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:41
 *
 * @author: e03229
 */
public class TestComposant2 implements TestComponentVisited {
    @Override
    public void accept(TestComponentVisitor visitor) {
        System.out.println("Composant2.accept");
        visitor.visit(this);
    }
}
