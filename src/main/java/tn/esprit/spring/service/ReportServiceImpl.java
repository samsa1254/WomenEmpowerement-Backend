package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Report;
import tn.esprit.spring.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportRepository reportRep ; 
	@Override
	public Report addReport(Report rep) {
		reportRep.save(rep);
		return rep;
	}

	@Override
	public Report updatereport(Report rep) {
		reportRep.save(rep);
		return rep;
	}

	@Override
	public void deleterepById(Long id) {
		reportRep.deleteById(id);
		
	}

	@Override
	public Report retrievebyId(Long id) {
		Report p = reportRep.findById(id).get();
		return p ;
		}

	@Override
	public List<Report> retrieveallreps() {
		List<Report> reps = (List<Report>) reportRep.findAll();
		return reps;
	}

}
