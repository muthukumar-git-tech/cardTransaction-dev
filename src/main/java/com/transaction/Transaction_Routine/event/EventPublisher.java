package com.transaction.Transaction_Routine.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
	
	@Autowired
	private StreamBridge streamBridge;
	
	public void publishAccountEvent(AccountEvent event) {
        streamBridge.send("output", event);
    }

    public void publishTransactionEvent(TransactionEvent event) {
        streamBridge.send("output", event);
    }
	

}
