package com.gni.frmk.tools.addon.model.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 18:19
 *
 * @author: e03229
 */
@XmlRootElement
public enum ComponentType {
    ADAPTER_CONNECTION,
    ADAPTER_LISTENER,
    ADAPTER_NOTIFICATION,
    SCHEDULER,
    PORT,
    NATIVE_TRIGGER,
    JMS_TRIGGER,
    JMS_ALIAS,
    IS_PACKAGE
}
