package com.gni.frmk.tools.addon.model.api.immutable;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 15:29
 *
 * @author: e03229
 */
public interface WritableObject<T, I extends ImmutableObject<? extends T, I, M>, M extends MutableObject<? extends T, I, M>>
        extends ReadableObject<T, I, M> {
}
