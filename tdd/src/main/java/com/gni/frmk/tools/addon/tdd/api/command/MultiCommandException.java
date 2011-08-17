package com.gni.frmk.tools.addon.tdd.api.command;


import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:50
 *
 * @author: e03229
 */
public class MultiCommandException extends CommandException implements Iterable<CommandException> {
    private final List<CommandException> exceptions = Lists.newArrayList();

    @SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
    private MultiCommandException(Iterator<CommandException> causes) {
        super(causes.next());
        while (causes.hasNext()) {
            exceptions.add(causes.next());
        }
    }

    @Override
    public Iterator<CommandException> iterator() {
        return exceptions.iterator();
    }

    public static void throwFirstExceptionIfNeeded(Iterable<CommandException> exceptions) throws MultiCommandException {
        Iterator<CommandException> iterator = exceptions.iterator();
        if (iterator.hasNext()) {
            throw new MultiCommandException(iterator);
        }
    }
}
