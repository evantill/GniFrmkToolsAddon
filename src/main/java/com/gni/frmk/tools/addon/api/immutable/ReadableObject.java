package com.gni.frmk.tools.addon.api.immutable;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 15:29
 *
 * @author: e03229
 */
public interface ReadableObject<I extends ImmutableObject<I, M>, M extends MutableObject<I, M>> {
    I toImmutable();

    M toMutable();
}
