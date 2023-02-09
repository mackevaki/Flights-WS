package com.mycompany.flights.handlers;

import jakarta.xml.ws.LogicalMessage;
import jakarta.xml.ws.handler.LogicalHandler;
import jakarta.xml.ws.handler.LogicalMessageContext;
import jakarta.xml.ws.handler.MessageContext;

public class NewLogicalHandler implements LogicalHandler<LogicalMessageContext> {
    
    public boolean handleMessage(LogicalMessageContext messageContext) {
        LogicalMessage msg = messageContext.getMessage();
        return true;
    }
    
    public boolean handleFault(LogicalMessageContext messageContext) {
        return true;
    }
    
    public void close(MessageContext context) {
    }
    
}
