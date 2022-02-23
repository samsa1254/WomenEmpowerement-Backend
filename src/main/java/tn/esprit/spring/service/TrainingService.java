package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Training;
import tn.esprit.spring.repository.TrainingRepository;

@Service
public class TrainingService implements ITrainingService{

	@Autowired
	TrainingRepository repo;
	
	@Override
	public Training add(Training b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public Training update(Training b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public List<Training> list() {
		// TODO Auto-generated method stub
		return (List<Training>) repo.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Training retrieve(Long id) {
		// TODO Auto-generated method stub
		return  repo.findById(id).get();
	}


}
