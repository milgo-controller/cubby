package com.milgo.cubby.bo.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milgo.cubby.bo.TrainingBo;
import com.milgo.cubby.dao.TrainingDao;
import com.milgo.cubby.model.Training;

@Service
public class TrainingBoImpl implements TrainingBo{

	@Autowired
	private TrainingDao trainingDao;
	
	public void addTraining(Training training) {
		trainingDao.addTraining(training);		
	}

	public void removeTraining(Integer id) {
		trainingDao.removeTraining(id);
	}

	public void modifyTraining(Training training) {
		trainingDao.modifyTraining(training);
	}

	public List<?> getAllTrainings() {
		return trainingDao.getAllTrainings();
	}

	@Override
	public Training getTrainingById(Integer id) {
		return trainingDao.getTrainingById(id);
	}

}
