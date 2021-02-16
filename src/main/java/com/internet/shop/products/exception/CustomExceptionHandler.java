package com.internet.shop.products.exception;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;
import java.util.Map;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
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

            Throwable e = context.getException();

            final FacesContext facesContext = FacesContext.getCurrentInstance();
            final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
            final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

            try {
                requestMap.put("exceptionMessage", e.getMessage());
                navigationHandler.handleNavigation(facesContext, null, "/error");
                requestMap.put("error-stack", e.getStackTrace());
                facesContext.renderResponse();
                facesContext.addMessage(e.getMessage(), new FacesMessage(FacesMessage.SEVERITY_INFO.toString()));
            } finally {
                i.remove();
            }
        }
        getWrapped().handle();
    }
}
