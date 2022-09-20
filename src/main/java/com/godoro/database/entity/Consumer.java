package com.godoro.database.entity;

public class Consumer {
	private long consumerId;
	private String consumerName;
	private String consumerPassword;

	public Consumer(long consumerId, String consumerName, String consumerPassword) {
		this.consumerId = consumerId;
		this.consumerName = consumerName;
		this.consumerPassword = consumerPassword;
	}
	
	public Consumer() {
		
	}

	public long getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(long consumerId) {
		this.consumerId = consumerId;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getConsumerPassword() {
		return consumerPassword;
	}

	public void setConsumerPassword(String consumerPassword) {
		this.consumerPassword = consumerPassword;
	}

	
	
	
	
	
}
