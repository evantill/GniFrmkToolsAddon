package com.gni.frmk.tools.addon.model.configuration;

import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 11:19
 *
 * @author: e03229
 */
@XmlType(propOrder = {"id",
                      "packageName"})
public class ConfigurationId {
    private String id;
    private String packageName;

    public ConfigurationId() {
    }

    public ConfigurationId(String id, String packageName) {
        this.id = id;
        this.packageName = packageName;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationId that = (ConfigurationId) o;
        return Objects.equal(getId(), that.getId()) && Objects.equal(getPackageName(), that.getPackageName());
    }

}
