package com.gni.frmk.tools.addon.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:47
 *
 * @author: e03229
 */
public class TestConfiguration implements TestConfigurationVisited {
    private final TestComposant1 c1;
    private final TestComposant2 c2;

    public TestConfiguration(TestComposant1 c1, TestComposant2 c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public TestComposant1 getComposant1() {
        return c1;
    }

    @Override
    public TestComposant2 getComposant2() {
        return c2;
    }

    @Override
    public void accept(TestConfigurationVisitor visitor) {
        System.out.println("Configuration.accept");
        visitor.dispatchVisit(this);
    }
}
