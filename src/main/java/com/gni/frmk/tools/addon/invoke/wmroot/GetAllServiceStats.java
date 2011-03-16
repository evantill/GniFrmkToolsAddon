package com.gni.frmk.tools.addon.invoke.wmroot;

import com.gni.frmk.tools.addon.invoke.ActionPattern.Action;
import com.gni.frmk.tools.addon.invoke.SetResult;
import com.gni.frmk.tools.addon.invoke.wmroot.GetAllServiceStats.Result;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 20:22
 *
 * @author: e03229
 */
public class GetAllServiceStats implements Action<Result> {

    public static class Result extends SetResult<String> {

        public Result(Set<String> runnings) {
            super(runnings);
        }

        public Set<String> getRunningServices() {
            return getCollection();
        }

        public int getNbrRunningServices() {
            return getRunningServices().size();
        }
    }
}
