package com.milgo.cubby.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UserTrainingId implements java.io.Serializable{

	/**
	 * 
	 */
	
	@ManyToOne
	private Training training;
	
	@ManyToOne
	private User user;
	
	
	public Training getTraining() {
		return training;
	}
	
	public void setTraining(Training training) {
		this.training = training;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}
