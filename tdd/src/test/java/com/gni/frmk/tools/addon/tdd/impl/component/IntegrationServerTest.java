package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.impl.component.integrationserver.IntegrationServer;
import com.gni.frmk.tools.addon.tdd.impl.component.integrationserver.IntegrationServerState;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 13:55
 *
 * @author: e03229
 */
public class IntegrationServerTest {

    private IntegrationServer server;

    @BeforeMethod
    public void initServer() {
        server = new IntegrationServer();
    }

    //TODO @Ignore("testing emma plugins")
    @Test(enabled = false)
    public void testFullOpen() throws Exception {
        server.open();
    }

    //TODO @Ignore("testing emma plugins")
    @Test(enabled = false)
    public void testFullClose() throws Exception {
        server.close();
    }

    //TODO @Ignore("testing emma plugins")
    @Test(enabled = false)
    public void testCloseBeforeShutdown() throws Exception {
        IntegrationServerState currentState = server.getState();
        server.close(currentState);
    }

    /**
     * ouverture de l'IS :
     * 1-lister l'ensemble des composants
     * 2-parcourir l'ensemble des composants dans l'ordre
     * 3-modifier l'etat des composants
     * <p/>
     * doit lever une exception en cas de probleme
     * doit annuler les operations en cas de probleme
     * doit laisser le systeme dans un etat stable
     * doit fonctionner sur l'ensemble des versions IS 65 7 8
     *
     * @throws Exception
     */
    //TODO @Ignore("testing emma plugins")
    @Test(enabled = false)
    public void testOpenAfterStartup() throws Exception {
        IntegrationServerState currentState = server.getState();
        server.open(currentState);
    }
}
