package com.milgo.cubby.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Table(name="TRAININGS")
public class Training {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	public Integer id;
	
	@Size(min=5, max=25, message="Wrong name length (5 to 25 letters)!")
	@Column(name="NAME", unique=true, nullable=false)
	public String name;
	
	@Size(min=5, max=255, message="Wrong description length (5 to 255 letters)!")
	@Column(name="DESCRIPTION", nullable=false)
	public String description;
	
	@Column(name="COST", nullable=false)
	public Integer cost;
	
	@Column(name="ONLINE", nullable=false)
	public Integer online;
	
	@Column(name="URL")
	public String url;
	
	@Column(name="STARTDATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date startDate;
	
	@Column(name="PLACE")
	public String place;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="pk.training", cascade=CascadeType.ALL)
	public Set<UserTraining> userTrainings = new HashSet<UserTraining>();
	
	public Set<UserTraining> getUserTrainings() {
		return userTrainings;
	}

	public void setUserTrainings(Set<UserTraining> userTrainings) {
		this.userTrainings = userTrainings;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    //@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	
}
