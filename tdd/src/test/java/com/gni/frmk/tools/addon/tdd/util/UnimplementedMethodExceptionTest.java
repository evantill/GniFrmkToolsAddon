package com.gni.frmk.tools.addon.tdd.util;


import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 14:22
 *
 * @author: e03229
 */
@SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
public class UnimplementedMethodExceptionTest {

    private final String EXPECTED_METHOD = "unimplementedMethod";
    private final String EXPECTED_CLASS_NAME = getClass().getName();

    @Test(expectedExceptions = UnimplementedMethodException.class)
    public void testUnimplementedMethod() throws Exception {
        unimplementedMethod();
    }

    private void unimplementedMethod() {
        throw new UnimplementedMethodException();
    }

    @Test
    public void testGetMessage() throws Exception {
        String actualMessage = generateException().getMessage();
        String expectedMessage = format("method %s is not yet implemented in class %s", EXPECTED_METHOD, EXPECTED_CLASS_NAME);
        assertThat(actualMessage).isNotNull().isNotEmpty().isEqualTo(expectedMessage);
    }

    private UnimplementedMethodException generateException() {
        try {
            unimplementedMethod();
            throw new IllegalStateException("failed to generate exception");
        } catch (UnimplementedMethodException cause) {
            return cause;
        }
    }

    @Test
    public void testGetOrigin() throws Exception {
        String actualOrigin = generateException().getOrigin();
        String expectedOrigin = getClass().getName();
        assertThat(actualOrigin).isNotNull().isEqualTo(expectedOrigin);
    }

    @Test
    public void testGetMethodName() throws Exception {
        String actualMethodName = generateException().getMethodName();
        String expectedMethodName = EXPECTED_METHOD;
        assertThat(actualMethodName).isNotNull().isEqualTo(expectedMethodName);
    }

}
