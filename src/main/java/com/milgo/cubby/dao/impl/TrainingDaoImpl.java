package com.milgo.cubby.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.milgo.cubby.dao.TrainingDao;
import com.milgo.cubby.model.Training;
import com.milgo.cubby.model.User;
import com.milgo.cubby.model.UserTraining;

@Repository
public class TrainingDaoImpl implements TrainingDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void addTraining(Training training) {
		sessionFactory.getCurrentSession().save(training);		
	}

	@Transactional
	public void removeTraining(Integer id) {
		
		Training t = getTrainingById(id);
		
		List<?> usersTrainings = sessionFactory.getCurrentSession()
				.createCriteria(UserTraining.class).list();
		
		Iterator<?> i = usersTrainings.iterator();
		while(i.hasNext()){
			UserTraining ut = (UserTraining)i.next();
			if(ut.getTraining().getId() == id){
				
				Iterator<?> ui = ut.getUser().getUserTrainings().iterator();
				while(ui.hasNext()){
					UserTraining uut = (UserTraining)ui.next();
					if(uut.getTraining().getId() == id)
						ui.remove();
				}
					
				Iterator<?> ti = ut.getTraining().getUserTrainings().iterator();
				while(ti.hasNext()){
					UserTraining tut = (UserTraining)ti.next();
					if(tut.getTraining().getId() == id)
						ti.remove();
				}
				
				i.remove();
				sessionFactory.getCurrentSession().delete(ut);
			}
		}
		
		sessionFactory.getCurrentSession().delete(t);	
	}

	@Transactional
	public void modifyTraining(Training training) {
		sessionFactory.getCurrentSession().update(training);
	}

	@Transactional
	public List<?> getAllTrainings() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Training.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@Transactional
	public Training getTrainingById(Integer id) {
		return (Training) sessionFactory.getCurrentSession()
				.createCriteria(Training.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

}
