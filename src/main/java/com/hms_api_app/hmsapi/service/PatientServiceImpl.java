package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.PatientDto;
import com.hms_api_app.hmsapi.dto.PatientResponse;
import com.hms_api_app.hmsapi.entity.Patient;
import com.hms_api_app.hmsapi.errorHandler.ResourceNotFoundException;
import com.hms_api_app.hmsapi.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService{

    //other class objects
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientRepository patientRepo;


    //entity-dto conversion methods
    public Patient mapToEntity(PatientDto patientDto) {
        Patient newPatient = modelMapper.map(patientDto, Patient.class);
        return newPatient;
    }

    public PatientDto mapToDto(Patient patient) {
        PatientDto newPatientDto = modelMapper.map(patient, PatientDto.class);
        return newPatientDto;
    }


    //implementation methods.
    @Override
    public PatientDto createPatient(PatientDto patientDto) {

        Patient newPatient = mapToEntity(patientDto);
        Patient savedPatient = patientRepo.save(newPatient);
        PatientDto convertedPatient = mapToDto(savedPatient);
        return convertedPatient;
    }

    //this method is gone to bottom.
//    @Override
//    public List<PatientDto> getAllPatient() {
//        List<Patient> listPatient = patientRepo.findAll();
//        List<PatientDto> listPatientDto = listPatient.stream().map(this::mapToDto).collect(Collectors.toList());
//        return listPatientDto;
//
//    }

    @Override
    public List<PatientDto> getOnePatientById(long id) {
//        Optional<Patient> byId = patientRepo.findById(id);
//        if(byId.isPresent()){
//            Patient patient = byId.get();
//            PatientDto patientDto = mapToDto(patient);
//            List<PatientDto> listDto = new ArrayList<>();
//            listDto.add(patientDto);
//            return listDto;
//        }
//        else{
//            return Collections.emptyList();
//        }

        Patient patient = patientRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Pateint", "id", id));

        PatientDto patientDto = mapToDto(patient);

        List<PatientDto> listDto = new ArrayList<>();
        listDto.add(patientDto);
        return listDto;
    }

    @Override
    public List<PatientDto> updateThePatient(long id, PatientDto updatedPatient) {
//        Optional<Patient> byId = patientRepo.findById(id);
//        if(byId.isPresent()){
//            Patient patient = byId.get();
//            patient.setAddress(updatedPatient.getAddress());
//            patient.setEmail(updatedPatient.getEmail());
//            patient.setFirstName(updatedPatient.getFirstName());
//            patient.setLastName(updatedPatient.getLastName());
//            patient.setMobileNumber(updatedPatient.getMobileNumber());
//            patient.setDateOfBirth(updatedPatient.getDateOfBirth());
//            patient.setId(id);
//
//            PatientDto updatedNewPatient = mapToDto(patientRepo.save(patient));
//            List<PatientDto> listDto = new ArrayList<>();
//            listDto.add(updatedNewPatient);
//            return listDto;
//        }
//        else {
//            return Collections.emptyList();
//        }

        Patient patient = patientRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                "Patient", "id", id));

            patient.setAddress(updatedPatient.getAddress());
            patient.setEmail(updatedPatient.getEmail());
            patient.setFirstName(updatedPatient.getFirstName());
            patient.setLastName(updatedPatient.getLastName());
            patient.setMobileNumber(updatedPatient.getMobileNumber());
            patient.setDateOfBirth(updatedPatient.getDateOfBirth());
            patient.setId(id);
            PatientDto updatedNewPatient = mapToDto(patientRepo.save(patient));

            List<PatientDto> listDto = new ArrayList<>();
            listDto.add(updatedNewPatient);
            return listDto;

    }

    @Override
    public String deleteById(long id) {
//        if(patientRepo.findById(id).isPresent()){
//            patientRepo.deleteById(id);
//            return "Patient information deleted successfully.";
//        }
//        else{
//            return "Patient information is not present.";
//        }

        Patient patient = patientRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                "Pateint", "id", id
        ));
        patientRepo.deleteById(id);
        return "Patient information deleted successfully.";
    }

    @Override
    public PatientResponse getAllPatients(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Patient> all = patientRepo.findAll(pageable);
        List<Patient> content = all.getContent();
        List<PatientDto> collect = content.stream().map(patient -> mapToDto(patient)).collect(Collectors.toList());

        PatientResponse patientRespo = new PatientResponse();

        patientRespo.setListPatientResp(collect);
        patientRespo.setPageNo(all.getNumber());
        patientRespo.setPageSize(all.getSize());
        patientRespo.setTotalPages(all.getTotalPages());
        patientRespo.setTotalElements(all.getTotalElements());
        patientRespo.setLastPage(all.isLast());

        return patientRespo;
    }


}
