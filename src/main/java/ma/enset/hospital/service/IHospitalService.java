package ma.enset.hospital.service;

import ma.enset.hospital.entity.Consultation;
import ma.enset.hospital.entity.Medecin;
import ma.enset.hospital.entity.Patient;
import ma.enset.hospital.entity.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);


}
