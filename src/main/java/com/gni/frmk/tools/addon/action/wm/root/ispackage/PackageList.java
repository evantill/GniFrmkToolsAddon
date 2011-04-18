package com.gni.frmk.tools.addon.action.wm.root.ispackage;

import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.model.component.ImmutableIntegrationServerPackage.MutableIntegrationServerPackage;
import com.gni.frmk.tools.addon.result.SetResult;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:32
 *
 * @author: e03229
 */
public class PackageList
        implements Action<SetResult<MutableIntegrationServerPackage>>,
        UpdatableCollectionAction<MutableIntegrationServerPackage, Set<MutableIntegrationServerPackage>, SetResult<MutableIntegrationServerPackage>> {

    private final Set<MutableIntegrationServerPackage> packages = Sets.newHashSet();

    @Override
    public void setCollection(Collection<MutableIntegrationServerPackage> packages) {
        packages.addAll(packages);
    }

    @Override
    public Set<MutableIntegrationServerPackage> getCollection() {
        return Collections.unmodifiableSet(packages);
    }

    @Override
    public void addToCollection(MutableIntegrationServerPackage element) {
        packages.add(element);
    }

    @Override
    public boolean isUpdate() {
        return packages.size() > 0;
    }
}
