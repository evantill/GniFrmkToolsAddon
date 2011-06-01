package com.gni.frmk.tools.addon.model.component;

import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorNode;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 10:30
 *
 * @author: e03229
 */
@XmlDiscriminatorNode("@classifier")
public interface ComponentId<I extends ComponentId<I>> extends Comparable<I> {

}
