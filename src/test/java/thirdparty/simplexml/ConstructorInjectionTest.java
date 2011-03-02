package thirdparty.simplexml;

import org.junit.Ignore;
import org.junit.Test;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
public class ConstructorInjectionTest {

    private static class SimpleElementTwoBuilder {
        String value;

        private SimpleElementTwoBuilder() {
            value="defaultValue";
        }

        public SimpleElementTwoBuilder defineValue(String value){
            this.value=value;
            return this;
        }

        public SimpleElementTwo build(){
            return new SimpleElementTwo(this);
        }
    }

    @Root(name = "root")
    private static class RootElement {

        @Element(name = "one")
        private final SimpleElementOne one;

        public RootElement(@Element(name = "one") SimpleElementOne one) {
            this.one = one;
        }
    }

    private static class SimpleElementOne {
        @Element(name = "two")
        private final SimpleElementTwo two;

        public SimpleElementOne(@Element(name = "two") SimpleElementTwo two) {
            this.two = two;
        }

        /**
         * Removing this constructor will make the test successful
         */
        @SuppressWarnings({"UnusedDeclaration"})
        public SimpleElementOne() {
            two = new SimpleElementTwo("default");
        }

    }

    private static class SimpleElementTwo {
        @Attribute(name = "value")
        private final String value;

        public SimpleElementTwo(@Attribute(name = "value") String value) {
            this.value = value;
        }

        public SimpleElementTwo(SimpleElementTwoBuilder builder) {
            this.value=builder.value;
        }
    }

    @Test
    @Ignore("en attente fix")
    /**
     * The stack trace is :
     *  org.simpleframework.xml.core.ConstructorException: No match found for field 'two' private final thirdparty.simplexml.ConstructorInjectionTest$SimpleElementTwo thirdparty.simplexml.ConstructorInjectionTest$SimpleElementOne.two in class thirdparty.simplexml.ConstructorInjectionTest$SimpleElementOne
     *  at org.simpleframework.xml.core.Scanner.validateConstructor(Scanner.java:513)
     *  at org.simpleframework.xml.core.Scanner.validateElements(Scanner.java:452)
     *  at org.simpleframework.xml.core.Scanner.validate(Scanner.java:413)
     *  at org.simpleframework.xml.core.Scanner.scan(Scanner.java:397)
     *  at org.simpleframework.xml.core.Scanner.<init>(Scanner.java:120)
     *  at org.simpleframework.xml.core.ScannerFactory.getInstance(ScannerFactory.java:65)
     *  [...]
     *  at thirdparty.simplexml.ConstructorInjectionTest.testConstructorInjection(ConstructorInjectionTest.java:78)
     */
    public void testConstructorInjection() throws Exception {
        SimpleElementTwo two = new SimpleElementTwoBuilder().defineValue("val").build();
        SimpleElementOne one = new SimpleElementOne(two);
        RootElement root = new RootElement(one);

        Serializer serializer = new Persister();
        File outDir = new File("target/datas/ConstructorInjectionTest");
        if (!outDir.exists()) {
            if (!outDir.mkdirs()) {
                throw new IllegalStateException(String.format("can not create directory %s", outDir.getAbsolutePath()));
            }
        }
        File output = new File(outDir, "testConstructorInjection.xml");
        serializer.write(root, output);
    }
}
