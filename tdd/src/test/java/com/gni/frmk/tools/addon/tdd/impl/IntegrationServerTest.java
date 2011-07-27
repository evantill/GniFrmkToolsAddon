package com.gni.frmk.tools.addon.tdd.impl;

import com.gni.frmk.tools.addon.tdd.impl.integrationserver.IntegrationServer;
import com.gni.frmk.tools.addon.tdd.impl.integrationserver.IntegrationServerState;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 13:55
 *
 * @author: e03229
 */
public class IntegrationServerTest {

    private IntegrationServer server;

    @Before
    public void initServer() {
        server = new IntegrationServer();
    }

    @Test
    public void testFullOpen() throws Exception {
        server.open();
    }

    @Test
    public void testFullClose() throws Exception {
        server.close();
    }

    @Test
    public void testCloseBeforeShutdown() throws Exception {
        IntegrationServerState currentState = server.getState();
        server.close(currentState);
    }

    /**
         * ouverture de l'IS :
         * 1-lister l'ensemble des composants
         * 2-parcourir l'ensemble des composants dans l'ordre
         * 3-modifier l'etat des composants
         *
         * doit lever une exception en cas de probleme
         * doit annuler les operations en cas de probleme
         * doit laisser le systeme dans un etat stable
         * doit fonctionner sur l'ensemble des versions IS 65 7 8
         * @throws Exception
         */
    @Test
    public void testOpenAfterStartup() throws Exception {
        IntegrationServerState currentState = server.getState();
        server.open(currentState);
    }
}
