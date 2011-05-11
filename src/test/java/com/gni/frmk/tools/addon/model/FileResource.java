package com.gni.frmk.tools.addon.model;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.rules.ExternalResource;

import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:16
 *
 * @author: e03229
 */
public class FileResource extends ExternalResource {

   private final Class clazz;
   private final String resourceName;
   private final Charset charset;
   private String content;

   public FileResource(Class clazz, String resourceName) {
       this(clazz, resourceName, Charsets.UTF_8);
   }

   public FileResource(Class clazz, String resourceName, Charset charset) {
       this.clazz = clazz;
       this.resourceName = resourceName;
       this.charset = charset;
   }

   @Override
   protected void before() throws Throwable {
       content = Resources.toString(clazz.getResource(resourceName), charset);
   }

   @Override
   protected void after() {
       content = null;
   }

   public String getContent() {
       return content;
   }
   }
