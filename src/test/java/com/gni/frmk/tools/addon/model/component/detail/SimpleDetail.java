package com.gni.frmk.tools.addon.model.component.detail;

import com.gni.frmk.tools.addon.model.BaseComponent.AbstractDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:17
 *
 * @author: e03229
 */
public class SimpleDetail extends AbstractDetail {
   private String description;

   public SimpleDetail() {
   }

   public SimpleDetail(String description) {
       this.description = description;
   }

   public String getDescription() {
       return description;
   }

   public void setDescription(String description) {
       this.description = description;
   }
   }
