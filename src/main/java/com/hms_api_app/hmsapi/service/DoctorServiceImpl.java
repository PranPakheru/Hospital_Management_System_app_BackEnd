package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.DoctorDto;
import com.hms_api_app.hmsapi.entity.Doctor;
import com.hms_api_app.hmsapi.errorHandler.ResourceNotFoundException;
import com.hms_api_app.hmsapi.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService{

    //other class object.
    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private ModelMapper modelMapper;


    //entity-dto conversion for doctor.
    private Doctor mapToEntity(DoctorDto doctorDto){
        Doctor doctorEntity = modelMapper.map(doctorDto, Doctor.class);
        return doctorEntity;
    }

    private DoctorDto mapToDto(Doctor doctor){
        DoctorDto doctorDto = modelMapper.map(doctor, DoctorDto.class);
        return doctorDto;
    }


    //all CRUD and rest related methods.
    @Override
    public List<DoctorDto> createDoctor(DoctorDto doctordto) {
        Doctor doctor = mapToEntity(doctordto);
        Doctor savedDoctor = doctorRepo.save(doctor);
        DoctorDto newDoctorDto = mapToDto(savedDoctor);

        List<DoctorDto> listDoctorDto = new ArrayList<>();
        listDoctorDto.add(newDoctorDto);
        return listDoctorDto;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> allDoc = doctorRepo.findAll();
        List<DoctorDto> listDoctors = allDoc.stream().map(this::mapToDto).collect(Collectors.toList());
        return listDoctors;
    }

    @Override
    public List<DoctorDto> getOneDoctor(long id) {
//        Optional<Doctor> byId = doctorRepo.findById(id);
//        List<DoctorDto> listDoctor = new ArrayList<>();
//        listDoctor.add(mapToDto(byId.get()));
//        return listDoctor;

        Doctor doctor = doctorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Doctor", "id", id
        ));

        List<DoctorDto> listDoctor = new ArrayList<>();
        listDoctor.add(mapToDto(doctor));
        return listDoctor;
    }

    @Override
    public List<DoctorDto> updateTheDoctor(long id, DoctorDto doctorDto) {
//        Optional<Doctor> byId = doctorRepo.findById(id);
//
//        if(byId.isPresent()){
//            Doctor doctor = byId.get();
//            doctor.setId(id);
//            doctor.setDoctorName(doctorDto.getDoctorName());
//            doctor.setDesignation(doctorDto.getDesignation());
//            doctor.setEmail(doctorDto.getEmail());
//            doctor.setMobileNumber(doctorDto.getMobileNumber());
//            doctor.setDateOfBirth(doctorDto.getDateOfBirth());
//            DoctorDto updatedDoctorDto = mapToDto(doctorRepo.save(doctor));
//
//            List<DoctorDto> newDoc = new ArrayList<>();
//            newDoc.add(updatedDoctorDto);
//            return newDoc;
//        }
//        else{
//            return Collections.emptyList();
//        }

        Doctor doctor = doctorRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Doctor", "id", id
        ));
            doctor.setId(id);
            doctor.setDoctorName(doctorDto.getDoctorName());
            doctor.setDesignation(doctorDto.getDesignation());
            doctor.setEmail(doctorDto.getEmail());
            doctor.setMobileNumber(doctorDto.getMobileNumber());
            doctor.setDateOfBirth(doctorDto.getDateOfBirth());
            DoctorDto updatedDoctorDto = mapToDto(doctorRepo.save(doctor));

            List<DoctorDto> newDoc = new ArrayList<>();
            newDoc.add(updatedDoctorDto);
            return newDoc;


    }

    @Override
    public String deleteTheDoctor(long id) {
//        if(doctorRepo.findById(id).isPresent()){
//            doctorRepo.deleteById(id);
//            return "Doctor data deleted successfullt.";
//        }
//        else {
//            return "Doctor data not found.";
//        }

        doctorRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Doctor", "id", id
        ));
        doctorRepo.deleteById(id);
        return "Doctor data deleted successfully.";
    }
}
