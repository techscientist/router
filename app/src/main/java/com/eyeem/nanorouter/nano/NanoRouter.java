package com.eyeem.nanorouter.nano;

import com.eyeem.router.AbstractPluggableBuilder;
import com.eyeem.router.AbstractRouter;
import com.eyeem.router.AbstractRouterLoader;
import com.eyeem.router.Plugin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by vishna on 22/06/16.
 */
public class NanoRouter extends AbstractRouter<ResponseWrapper, NanoHTTPD.IHTTPSession> {

   public static Loader prepare() { return new Loader(); }

   public static class Loader extends AbstractRouterLoader<ResponseWrapper, NanoHTTPD.IHTTPSession> {

      @Override
      public AbstractPluggableBuilder<ResponseWrapper, NanoHTTPD.IHTTPSession> createPluggableBuilder(
         Serializable params, HashMap<String, com.eyeem.router.Plugin<ResponseWrapper, NanoHTTPD.IHTTPSession>> plugins) {
         return new PluggableBuilder(params, plugins);
      }

      @Override public AbstractRouter<ResponseWrapper, NanoHTTPD.IHTTPSession> createRouter() {
         return new NanoRouter();
      }

      @Override
      public Loader plugin(com.eyeem.router.Plugin<ResponseWrapper, NanoHTTPD.IHTTPSession> plugin) {
         return (Loader) super.plugin(plugin);
      }

      @Override
      public NanoRouter load(Map<String, Object> routerMap) {
         return (NanoRouter) super.load(routerMap);
      }
   }

   public static class PluggableBuilder extends AbstractPluggableBuilder<ResponseWrapper, NanoHTTPD.IHTTPSession> {

      PluggableBuilder(Serializable params, HashMap<String, com.eyeem.router.Plugin<ResponseWrapper, NanoHTTPD.IHTTPSession>> plugins) {
         super(params, plugins);
      }

      @Override protected ResponseWrapper createOutputInstance() {
         return new ResponseWrapper();
      }
   }

   public static abstract class Plugin extends com.eyeem.router.Plugin<ResponseWrapper, NanoHTTPD.IHTTPSession> {
      public Plugin(String node) {
         super(node);
      }
   }
}
