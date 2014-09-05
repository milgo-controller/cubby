package com.milgo.cubby.model;

import javax.persistence.AssociationOverrides;
import javax.persistence.AssociationOverride;
import javax.persistence.Cacheable;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "USER_TRAININGS")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@AssociationOverrides({
        @AssociationOverride(name = "pk.training", 
            joinColumns = @JoinColumn(name = "TRAINING_ID")),
        @AssociationOverride(name = "pk.user", 
            joinColumns = @JoinColumn(name = "USER_ID")) })
public class UserTraining{

	@EmbeddedId
    private UserTrainingId pk = new UserTrainingId();

    @Column(name = "ACTIVE", nullable = false)
    private Integer active;
    
    public UserTrainingId getPk() {
        return pk;
    }

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public void setPk(UserTrainingId pk) {
		this.pk = pk;
	}
	
	public Training getTraining() {
		return pk.getTraining();
	}
	
	public void setTraining(Training training) {
		this.pk.setTraining(training);
	}

	public User getUser() {
		return pk.getUser();
	}
	
	public void setUser(User user) {
		this.pk.setUser(user);
	}
	
}
