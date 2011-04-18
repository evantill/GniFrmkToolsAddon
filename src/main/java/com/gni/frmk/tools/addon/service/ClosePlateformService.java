package com.gni.frmk.tools.addon.service;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.handler.configuration.visitor.ClosePlateformVisitor;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 13/04/11
 * Time: 18:10
 *
 * @author: e03229
 */
public class ClosePlateformService {


    public void execute(ImmutableConfiguration cnf){
        ClosePlateformVisitor visitor = new ClosePlateformVisitor();
        visitor.dispatchVisit(cnf);
        List<Action<?>> actions = visitor.produce();

    }
}
