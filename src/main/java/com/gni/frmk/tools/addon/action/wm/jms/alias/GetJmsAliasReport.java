package com.gni.frmk.tools.addon.action.wm.jms.alias;

import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.result.ListResult;
import com.gni.frmk.tools.addon.model.component.JmsAlias;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 17:13
 *
 * @author: e03229
 */
public class GetJmsAliasReport
        implements Action<ListResult<JmsAlias>>,
        UpdatableCollectionAction<JmsAlias, List<JmsAlias>, ListResult<JmsAlias>> {

    private final List<JmsAlias> aliases = Lists.newArrayList();

    @Override
    public void setCollection(Collection<JmsAlias> jmsAliases) {
        aliases.addAll(jmsAliases);
    }

    @Override
    public List<JmsAlias> getCollection() {
        return Collections.unmodifiableList(aliases);
    }

    @Override
    public void addToCollection(JmsAlias element) {
        aliases.add(element);
    }

    @Override
    public boolean isUpdate() {
        return aliases.size() > 0;
    }
}
