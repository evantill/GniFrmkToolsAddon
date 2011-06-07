package com.gni.frmk.tools.addon.invoker.service;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.context.TestServiceContextResource;
import com.gni.frmk.tools.addon.invoker.context.TestServiceContextResource.DevMode;
import com.gni.frmk.tools.addon.invoker.context.TestServiceContextResource.Phase;
import org.junit.Rule;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 11:21
 *
 * @author: e03229
 */
public class AbstractServiceTest {

    private static final String URL = "ar2dv102.groupe.generali.fr:5550";
//    private static final String URL = "ar1tn232.groupe.generali.fr:7512";
    private static final String USER = "evantill";
    private static final String PASSWORD = "evantill";

    static {
//        TestServiceContextResource.defineTestContext(USER, PASSWORD, URL);
//        TestServiceContextResource.changeMode(DevMode.DEV, Phase.RECORD);
    }

    @Rule
    public TestServiceContextResource contextResource = new TestServiceContextResource(this.getClass());

    protected ServiceContext getContext(){
        return contextResource.getContext();
    }
}
