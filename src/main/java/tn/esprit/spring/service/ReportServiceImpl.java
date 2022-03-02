package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.ReportRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportRepository reportRep ; 
	@Autowired
	private UserRepository uRep ;
	@Autowired
	private AppointmentRepository aRep ;
	
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

	@Override
	public Appointment treataReportbyMakingappointment(Long id ,int iduser1 , int iduser2,Appointment a) {
	 User u1=uRep.findById(iduser1).get();
	 User u2=uRep.findById(iduser2).get();
	 a.setUser(u1);
	 a.setUserexpert(u2);;
	 aRep.save(a);
     Report r= reportRep.findById(id).get();	
     r.setStatus("treated");
     reportRep.save(r);
     return a;
	}

}
