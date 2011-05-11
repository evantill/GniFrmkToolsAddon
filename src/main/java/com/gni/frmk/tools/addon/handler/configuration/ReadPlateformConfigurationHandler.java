package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.action.configuration.ReadPlateformConfiguration;
import com.gni.frmk.tools.addon.api.action.ActionException;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.Configuration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 13:43
 *
 * @author: e03229
 */
public class ReadPlateformConfigurationHandler
        implements ActionHandler<ReadPlateformConfiguration, ConfigurationResult, InvokeContext> {


    @Override
    public Class<ReadPlateformConfiguration> getActionType() {
        return ReadPlateformConfiguration.class;
    }

    @Override
    /**
     * chargement de la configuration de l'IntegrationServer:
     * <ol>
     *     <li>creation d'une configuration</li>
     *     <li>pour chaque type de composant</li>
     *     <li>recuperer la liste des composants</li>
     *     <li>pour chaque composant</li>
     *     <li>charger le detail</li>
     *     <li>charger le statut</li>
     *     <li></li>
     * </ol>
     */
    public ConfigurationResult execute(ReadPlateformConfiguration action, InvokeContext context)
            throws ActionException {

        Configuration actual = new Configuration();
        actual.setCreation(new Date());
        actual.setModification(new Date());
        actual.setName("live");
        actual.setId("live");
        actual.setPackageName("Default");
//        actual.setComponentConfigurations();

        return null;

    }
}
