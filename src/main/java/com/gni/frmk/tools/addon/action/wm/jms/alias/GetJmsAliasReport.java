package com.gni.frmk.tools.addon.action.wm.jms.alias;

import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.model.component.ImmutableJmsAlias.MutableJmsAlias;
import com.gni.frmk.tools.addon.result.ListResult;
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
        implements Action<ListResult<MutableJmsAlias>>,
        UpdatableCollectionAction<MutableJmsAlias, List<MutableJmsAlias>, ListResult<MutableJmsAlias>> {

    private final List<MutableJmsAlias> aliases = Lists.newArrayList();

    @Override
    public void setCollection(Collection<MutableJmsAlias> jmsAliases) {
        aliases.addAll(jmsAliases);
    }

    @Override
    public List<MutableJmsAlias> getCollection() {
        return Collections.unmodifiableList(aliases);
    }

    @Override
    public void addToCollection(MutableJmsAlias element) {
        aliases.add(element);
    }

    @Override
    public boolean isUpdate() {
        return aliases.size() > 0;
    }
}
