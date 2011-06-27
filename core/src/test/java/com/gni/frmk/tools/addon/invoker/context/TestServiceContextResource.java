package com.gni.frmk.tools.addon.invoker.context;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import org.junit.rules.ExternalResource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:42
 *
 * @author: e03229
 */
public class TestServiceContextResource extends ExternalResource {

    public static enum Phase {RECORD, REPLAY}

    public static enum DevMode {DEV, TEST}

    private final Class testClass;

    private static String user;
    private static String password;
    private static String url;

    private static Phase phase = Phase.REPLAY;
    private static DevMode deVMode = DevMode.TEST;

    private ServiceContext context;

    public TestServiceContextResource(Class testClass) {
        this.testClass = testClass;
    }

    public static final void defineTestContext(String user, String password, String url) {
        TestServiceContextResource.user = checkNotNull(user);
        TestServiceContextResource.password = checkNotNull(password);
        TestServiceContextResource.url = checkNotNull(url);
    }

    public static final void changeMode(DevMode newMode, Phase newPhase) {
        TestServiceContextResource.phase = newPhase;
        TestServiceContextResource.deVMode = newMode;
    }

    @Override
    protected void before() throws Throwable {
        switch (deVMode) {
            case DEV:
                context = RemoteContext.builder()
                                       .credential(user, password)
                                       .url(url)
                                       .build();
                break;
            case TEST:
                context = LocalContext.singleton;
                break;
        }
        switch (phase) {
            case RECORD:
                context = RecordTestContext.newInstance(testClass, context,new RecordPipelineUtilsSimpleStrategy());
                break;
            case REPLAY:
                context = ReplayTestContext.newInstance(testClass,new RecordPipelineUtilsSimpleStrategy());
                break;
        }
    }

    @Override
    protected void after() {
        context.dispose();
        context = null;
    }

    public ServiceContext getContext() {
        return context;
    }
}
