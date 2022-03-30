package ma.enset.hospital;

import ma.enset.hospital.entity.*;
import ma.enset.hospital.repository.ConsultationRepository;
import ma.enset.hospital.repository.MedecinRepository;
import ma.enset.hospital.repository.PatientRepository;
import ma.enset.hospital.repository.RendezVousRepository;
import ma.enset.hospital.service.HospitalServiceImplement;
import ma.enset.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	// Charge mo cette methode au demarrage
	CommandLineRunner start(IHospitalService hospitalService, PatientRepository patientRepository,RendezVousRepository rendezVousRepository, MedecinRepository medecinRepository) {
		return args -> {
			//patientRepository.save(new Patient(null, "Hassan", new Date(), false, null));

			Stream.of("Mohamed", "Hassan", "Najat")
					.forEach(name -> {
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						hospitalService.savePatient(patient);
					});
			Stream.of("aymane", "Hanane", "yasmine")
					.forEach(name -> {
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name + "@gmail.com");
						medecin.setSpecialite(Math.random() > 0.5 ? "Cardio" : "Dentiste");
						hospitalService.saveMedecin(medecin);

					});
			//Patient patient = patientRepository.findById(1L).orElse(null);
			Patient patient = patientRepository.findById(1L).orElse(null);
			//	Patient patient1 = patientRepository.findByNom("Najat");

			Medecin medecin = medecinRepository.findByNom("yasmine");

			RendezVous rendezVous = new RendezVous();
			rendezVous.setDateRendezVous(new Date());
			rendezVous.setStatutsRDV(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			hospitalService.saveRDV(rendezVous);

			RendezVous rendezVous1 = rendezVousRepository.findAll().get(0);
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de consultation");
			hospitalService.saveConsultation(consultation);

		};
	}

}
