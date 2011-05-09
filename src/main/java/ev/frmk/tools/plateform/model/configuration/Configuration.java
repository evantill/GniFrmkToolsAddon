package ev.frmk.tools.plateform.model.configuration;

import ev.frmk.tools.plateform.api.immutable.ImmutableObject;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/04/11
 * Time: 13:48
 *
 * @author: e03229
 */
public final class Configuration
        extends BaseConfiguration
        implements ImmutableObject<Configuration, MutableConfiguration>,
        ReadableConfiguration {

    public Configuration(ReadableConfiguration source) {
        super(source);
    }

    @Override
    public Configuration toImmutable() {
        return this;
    }
}