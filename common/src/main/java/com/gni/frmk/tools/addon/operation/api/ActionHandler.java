package com.gni.frmk.tools.addon.operation.api;

import javax.enterprise.util.TypeLiteral;
import javax.inject.Singleton;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 13:56
 *
 * @author: e03229
 */
@Singleton
public interface ActionHandler<A extends Action<? extends Result>, R extends Result, E extends ExecutionContext> {
    TypeLiteral<A> getActionType();

    R execute(A action, E context) throws ActionException;

    //TODO ajouter fonction de rollback
//        void rollback(A action, R result, E context) throws ActionException;
}
