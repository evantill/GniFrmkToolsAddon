package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.PortInfo;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 17:40
 *
 * @author: e03229
 */
public class ListListenersTest extends AbstractServiceTest {
    @Test
    public void testInvoke() throws Exception {
        ListListeners service = new ListListeners();

        ListValueOutput<PortInfo> output = service.invoke(NoInput.singleton, getContext());
        List<PortInfo> infos = output.getValues();
        assertEquals(46, infos.size());

        PortInfo expected = PortInfo.builder()
                                    .key("HTTPSListener@6528")
                                    .packageName("WmRoot")
                                    .protocol("HTTPS")
                                    .port("6528")
                                    .primary(false)
                                    .status("Inactive")
                                    .enabled(false)
                                    .listening(false)
                                    .suspended(false)
                                    .build();
        PortInfo actual = infos.get(0);
        assertEquals(expected, actual);
    }
}
