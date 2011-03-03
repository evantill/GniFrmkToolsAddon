package thirdparty.jaxb.registry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
public class StatusEnable implements Status{
    public static enum FlagEnable{
        ENABLED,DISABLED
    }

    private FlagEnable enabled;

    public StatusEnable(FlagEnable enabled) {
        this.enabled = enabled;
    }

    public StatusEnable() {
    }

    public FlagEnable getEnabled() {
        return enabled;
    }

    private void setEnabled(FlagEnable enabled) {
        this.enabled = enabled;
    }
}
