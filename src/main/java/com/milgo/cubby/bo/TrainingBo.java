package com.milgo.cubby.bo;

import java.util.List;

import com.milgo.cubby.model.Training;

public interface TrainingBo {

	public void addTraining(Training training);
	public void removeTraining(Integer id);
	public void modifyTraining(Training training);
	public Training getTrainingById(Integer id);
	public List<?> getAllTrainings();
}
