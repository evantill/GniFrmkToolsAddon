package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.SetValueOutput;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.gni.frmk.tools.addon.util.IntegrationServerUtil;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import com.wm.data.*;

import java.util.Set;

import static com.google.common.collect.Sets.filter;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 18:43
 *
 * @author: e03229
 */
public class GetAllServiceStats extends WmService<NoInput, SetValueOutput<String>> {

    private class PackageFilterPredicate implements Predicate<String> {
        private final String packageNameToIgnore;

        public PackageFilterPredicate(String packageNameToIgnore) {this.packageNameToIgnore = packageNameToIgnore;}

        @Override
        public boolean apply(/*@javax.annotation.Nullable*/ String input) {
            if (input == null) {
                return false;
            } else if (packageNameToIgnore == null) {
                return true;
            } else {
                return !input.startsWith(packageNameToIgnore);
            }
        }
    }

    private final Predicate<? super String> filter;

    public GetAllServiceStats() {
        this(null);
    }

    public GetAllServiceStats(String packageNameToIgnore) {
        super("wm.server.query:getAllServiceStats");
        if (packageNameToIgnore == null) {
            filter = Predicates.alwaysTrue();
        } else {
            filter = new PackageFilterPredicate(packageNameToIgnore);
        }
    }

    @Override
    protected IData prepareInput(NoInput input) throws ServiceInputException {
        return EMPTY_IDATA;
    }

    @Override
    protected SetValueOutput<String> prepareOutput(IData output) throws ServiceOutputException {
        Set<String> values = Sets.newHashSet();
        IData[] stats = extractIDataArray(checkDataExist(output), "SvcStats", false, EMPTY_IDATA_ARRAY);
        for (IData stat : stats) {
            IData checkedStat = checkDataExist(stat);
            String sRunning = extractStringValue(checkedStat, "sRunning", true, null);
            String name = extractStringValue(checkedStat, "name", true, null);
            if (!IntegrationServerUtil.EMPTY_VALUE_NBSP.equals(sRunning)) {
                values.add(name);
            }
        }
        return SetValueOutput.newInstance(filter(values, filter));
    }
}
