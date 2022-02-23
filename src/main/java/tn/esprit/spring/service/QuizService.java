package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.entities.Training;
import tn.esprit.spring.repository.QuizRepository;

@Service
public class QuizService implements IQuizService{

	@Autowired
	QuizRepository repo;
	
	@Override
	public Quiz add(Quiz b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public Quiz update(Quiz b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public List<Quiz> list() {
		// TODO Auto-generated method stub
		return (List<Quiz>) repo.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Quiz retrieve(Long id) {
		// TODO Auto-generated method stub
		return  repo.findById(id).get();
	}

}
