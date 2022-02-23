package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Training;
import tn.esprit.spring.repository.QuestionRepository;
@Service
public class QuestionService implements IQuestionService{

	@Autowired
	QuestionRepository repo;
	@Override
	public Question add(Question b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public Question update(Question b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public List<Question> list() {
		// TODO Auto-generated method stub
		return (List<Question>) repo.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Question retrieve(Long id) {
		// TODO Auto-generated method stub
		return  repo.findById(id).get();
		
	}

}
