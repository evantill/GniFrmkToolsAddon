package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 15:40
 *
 * @author: e03229
 */
public interface ComponentType<T extends ComponentType> extends Comparable<T> {
    public static enum IOType {
        INPUT(true, false, false),
        OUTPUT(false, true, false),
        CORE(false, false, true);

        private final boolean input;
        private final boolean output;
        private final boolean core;

        IOType(boolean input, boolean output, boolean core) {
            this.input = input;
            this.output = output;
            this.core = core;
        }

        public boolean isInput() {
            return input;
        }

        public boolean isOutput() {
            return output;
        }

        public boolean isCore() {
            return core;
        }
    }

    ComponentType.IOType getIOType();

    Class<? extends Component> getType();
}
