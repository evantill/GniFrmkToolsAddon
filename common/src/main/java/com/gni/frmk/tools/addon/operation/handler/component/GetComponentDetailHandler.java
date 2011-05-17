package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 15:24
 *
 * @author: e03229
 */
public interface GetComponentDetailHandler
        <C extends Component<I, ?, D>, D extends Detail, I extends Id> {
}
