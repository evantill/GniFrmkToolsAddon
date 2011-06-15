package com.gni.frmk.tools.addon.operation.action.component.root.service;

import com.gni.frmk.tools.addon.operation.action.component.root.service.GetRunningServices.Result;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 20:22
 *
 * @author: e03229
 */
public class GetRunningServices implements Action<Result> {

    private final Predicate<? super String> filter;

    public GetRunningServices(String packageNameToIgnore) {
        if (packageNameToIgnore == null) {
            filter = Predicates.alwaysTrue();
        } else {
            filter = new PackageFilterPredicate(packageNameToIgnore);
        }
    }

    public GetRunningServices() {
        filter = Predicates.alwaysTrue();
    }

    public Predicate<? super String> getFilter() {
        return filter;
    }

    public static class Result extends SetResult<String> {
        public Result(Set<String> strings) {
            super(strings);
        }

        public Set<String> getRunningServices() {
            return getCollection();
        }

        public int getNbrRunningServices() {
            return getRunningServices().size();
        }
    }

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
}
