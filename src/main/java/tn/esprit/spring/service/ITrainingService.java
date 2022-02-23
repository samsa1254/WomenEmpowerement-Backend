package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Training;

public interface ITrainingService {
	public Training add (Training b);
	public Training update (Training b);
	public List<Training> list();
	void delete(Long id);
	Training retrieve(Long id);

}
