package com.internet.shop.products.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;
import java.util.Map;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
    private final ExceptionHandler wrapped;

    CustomExceptionHandler(ExceptionHandler exception) {
        super(exception);
        this.wrapped = exception;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {

        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context =
                    (ExceptionQueuedEventContext) event.getSource();

            // get the exception from context
            Throwable t = context.getException();

            final FacesContext facesContext = FacesContext.getCurrentInstance();
            final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
            final NavigationHandler nav = facesContext.getApplication().getNavigationHandler();

            //here you do what ever you want with exception
            try {

                requestMap.put("exceptionMessage", t.getMessage());
                nav.handleNavigation(facesContext, null, "/error");
                requestMap.put("error-stack", t.getStackTrace());
                facesContext.renderResponse();
                facesContext.addMessage(t.getMessage(), new FacesMessage(FacesMessage.SEVERITY_INFO.toString(), "Email exist"));

            } finally {
                i.remove();
            }
        }
        getWrapped().handle();
    }
//
//    private void handleException(ViewExpiredException vee) {
////        LOG.log(Level.INFO, " handling viewExpiredException...");
//        FacesContext context = FacesContext.getCurrentInstance();
//        String viewId = vee.getViewId();
////        LOG.log(Level.INFO, "view id @" + viewId);
//        NavigationHandler nav
//                = context.getApplication().getNavigationHandler();
//        nav.handleNavigation(context, null, viewId);
//        context.renderResponse();
//    }
//
//    private void handleNotFoundException(Exception e) {
////        LOG.log(Level.INFO, "handling exception:...");
//        FacesContext context = FacesContext.getCurrentInstance();
//        String viewId = "/error.xhtml";
////        LOG.log(Level.INFO, "view id @" + viewId);
//        NavigationHandler nav
//                = context.getApplication().getNavigationHandler();
//        nav.handleNavigation(context, null, viewId);
//        context.getViewRoot().getViewMap(true).put("ex", e);
//        context.renderResponse();
//    }
}