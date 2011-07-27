package com.gni.frmk.tools.addon.tdd.util;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Thread.currentThread;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 14:11
 *
 * @author: e03229
 */
public class UnimplementedMethodException extends UnsupportedOperationException {

    private static final int CALLER_POSITION_IN_STACK = 2;
    private final String origin;
    private final String methodName;

    public UnimplementedMethodException() {
        StackTraceElement[] cause = checkNotNull(currentThread().getStackTrace());
        int callerIndex = checkElementIndex(CALLER_POSITION_IN_STACK, cause.length);
        origin = cause[callerIndex].getClassName();
        methodName = cause[callerIndex].getMethodName();
    }

    @Override
    public String getMessage() {
        return String.format("method %s is not yet implemented in class %s", methodName, origin);
    }

    public String getOrigin() {
        return origin;
    }

    public String getMethodName() {
        return methodName;
    }
}
