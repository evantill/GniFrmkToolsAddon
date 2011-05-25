package com.gni.frmk.tools.addon.invoker.service.jms;

import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsAliasInfo;
import com.gni.frmk.tools.addon.invoker.service.AbstractServiceTest;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 16:30
 *
 * @author: e03229
 */
public class GetConnectionAliasReportTest extends AbstractServiceTest {

    @Test
    public void testInvoke() throws Exception {
        GetConnectionAliasReport service = new GetConnectionAliasReport();
        ListValueOutput<JmsAliasInfo> output = service.invoke(NoInput.singleton, getContext());
        Assert.assertEquals(27, output.getValues().size());
        JmsAliasInfo expected = JmsAliasInfo.builder()
                                            .aliasName("BadJMSAlias")
                                            .enabled(false)
                                            .connected(false)
                                            .description("bas jms alias")
                                            .build();
        Assert.assertEquals(expected, output.getValues().get(0));
    }
}
