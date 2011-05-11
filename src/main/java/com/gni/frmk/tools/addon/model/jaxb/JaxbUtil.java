package com.gni.frmk.tools.addon.model.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 13:22
 *
 * @author: e03229
 */
public class JaxbUtil {

    public static final JAXBContext newContext() throws JAXBException {
        String contextPath="com.gni.frmk.tools.addon.model.jaxb"+
                           ":com.gni.frmk.tools.addon.model"+
                           ":com.gni.frmk.tools.addon.model.component"+
                           ":com.gni.frmk.tools.addon.model.component.art"+
                           ":com.gni.frmk.tools.addon.model.component.jms"+
                           ":com.gni.frmk.tools.addon.model.component.root";
        return JAXBContext.newInstance(contextPath);
    }
}
