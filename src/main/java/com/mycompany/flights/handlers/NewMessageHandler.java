package com.mycompany.flights.handlers;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPHeader;
import java.util.Collections;
import java.util.Set;
import javax.xml.namespace.QName;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewMessageHandler implements SOAPHandler<SOAPMessageContext> {
    
    public boolean handleMessage(SOAPMessageContext messageContext) {
        SOAPMessage msg = messageContext.getMessage();
        boolean outMessage = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        if (outMessage) {
            try {
                SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
                SOAPFactory factory = SOAPFactory.newInstance();
                SOAPElement element = factory.createElement(new QName("env", "UUID"));
                element.addTextNode(UUID.randomUUID().toString());
                
                SOAPHeader header = envelope.getHeader();
                header.addChildElement(element);
                
                msg.writeTo(System.out);
                
            } catch (SOAPException ex) {
                Logger.getLogger(NewMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NewMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    public void close(MessageContext context) {
    }
    
}
