package ev.frmk.tools.plateform.model.xxxxx;

import ev.frmk.tools.plateform.model.xxxxx.EnableComponentState.MutableEnableComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/05/11
 * Time: 08:58
 *
 * @author: e03229
 */
public class EnableComponentState implements ComponentState<EnableComponentState, MutableEnableComponentState> {
    private final boolean enabled;

    public EnableComponentState(ComponentState<EnableComponentState, MutableEnableComponentState> source) {
        enabled=source.isEnabled();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public static class MutableEnableComponentState implements MutableComponentState<EnableComponentState, MutableEnableComponentState> {
        private boolean enabled;

        public MutableEnableComponentState() {
        }

        public MutableEnableComponentState(ComponentState<EnableComponentState, MutableEnableComponentState> source) {
        }

        @Override
        public EnableComponentState asImmutable() {
            return new EnableComponentState(this);
        }

        @Override
        public MutableEnableComponentState asMutable() {
            return new MutableEnableComponentState(this);
        }
    }
}
