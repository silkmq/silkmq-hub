package org.silkmq.hub.config.internal;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Activator implements BundleActivator {
    private static Logger LOG = LoggerFactory.getLogger(BundleActivator.class);
        
    private ServiceRegistration<Handler> contentHandler;
    
    public void start(BundleContext bundleContext) throws Exception {
        ContextHandler ctxtHandler = new ContextHandler();
        ctxtHandler.setContextPath("/silkmq/config");
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("/tmp");
        resourceHandler.setDirectoriesListed(true);
        ctxtHandler.setHandler(resourceHandler);

        contentHandler = bundleContext.registerService(Handler.class, ctxtHandler, null);
        LOG.info("SilkMQ Hub Config provider service started");
    }

    /**
     * Called when the OSGi framework stops our bundle
     */
    public void stop(BundleContext bc) throws Exception {
        if (contentHandler != null) {
            contentHandler.unregister();
        }
    }

}

