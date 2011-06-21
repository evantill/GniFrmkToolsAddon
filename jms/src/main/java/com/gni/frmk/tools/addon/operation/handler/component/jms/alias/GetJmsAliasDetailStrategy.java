package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsAliasInfo;
import com.gni.frmk.tools.addon.invoker.service.jms.GetConnectionAliasReport;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAliasDetail;
import com.gni.frmk.tools.addon.model.component.jms.JmsAliasType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;
import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetJmsAliasDetailStrategy
        implements GetComponentDetailStrategy<JmsAliasType, StringId, JmsAliasDetail> {

    private final GetConnectionAliasReport aliases;

    @Inject
    public GetJmsAliasDetailStrategy(GetConnectionAliasReport aliases) {
        this.aliases = aliases;
    }

    @Override
    public JmsAliasType getComponentType() {
        return JmsAliasType.TYPE;
    }

    @Override
    public JmsAliasDetail getDetail(final StringId componentId, InvokeContext context) throws ServiceException {

        ServiceContext serviceContext = context.getServiceContext();

        ListValueOutput<JmsAliasInfo> infos = aliases.invoke(NoInput.singleton, serviceContext);
        JmsAliasInfo info = infos.getSingleValue(new Predicate<JmsAliasInfo>() {
            @Override
            public boolean apply(JmsAliasInfo input) {
                return input != null &&
                       componentId.getValue().equals(input.getAliasName());
            }
        });
        return JmsAliasDetail.build(info.getDescription());
    }

}
