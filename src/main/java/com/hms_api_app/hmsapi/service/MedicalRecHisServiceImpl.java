package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.Medical_Record_HistoryDto;
import com.hms_api_app.hmsapi.entity.Medical_Record_History;
import com.hms_api_app.hmsapi.errorHandler.ResourceNotFoundException;
import com.hms_api_app.hmsapi.repository.MedicalRecHisRepository;
import com.hms_api_app.hmsapi.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalRecHisServiceImpl implements MedicalRecHisService {

    //other class objects.
    @Autowired
    private MedicalRecHisRepository medRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientRepository patientRepo;


    //entity-dto conversion methods.
    private Medical_Record_History mapToEntity(Medical_Record_HistoryDto medDto){
        Medical_Record_History medEntity = modelMapper.map(medDto, Medical_Record_History.class);
        return medEntity;
    }

    private Medical_Record_HistoryDto mapToDto(Medical_Record_History medEntity){
        Medical_Record_HistoryDto medDto = modelMapper.map(medEntity, Medical_Record_HistoryDto.class);
        return medDto;
    }


    //other implementation methods
    @Override
    public List<Medical_Record_HistoryDto> createMedRecHis(Medical_Record_HistoryDto medDto) {
        Medical_Record_History medEntity = mapToEntity(medDto);
        Medical_Record_History newMedEntity = medRepo.save(medEntity);
        Medical_Record_HistoryDto savedMed = mapToDto(newMedEntity);

        List<Medical_Record_HistoryDto> listMed = new ArrayList<>();
        listMed.add(savedMed);

        return listMed;
    }

    @Override
    public List<Medical_Record_HistoryDto> getRecordsById(long patientId) {
//        List<Medical_Record_History> byPatientId = medRepo.findByPatientId(patientId);
//        List<Medical_Record_HistoryDto> collect = byPatientId.stream().map(this::mapToDto).collect(Collectors.toList());
//        return collect;

        patientRepo.findById(patientId).orElseThrow(()->new ResourceNotFoundException(
                "Patient", "patient id", patientId
        ));
        List<Medical_Record_History> byPatientId = medRepo.findByPatientId(patientId);
        List<Medical_Record_HistoryDto> collect = byPatientId.stream().map(this::mapToDto).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String deleteMedRecHisById(long id) {
//        Optional<Medical_Record_History> byId = medRepo.findById(id);
//        if(byId.isPresent()){
//            medRepo.deleteById(id);
//            return "Medical_Record_History is deleted successfully.";
//        }
//        else{
//            return "Medical_Record_History not found.";
//        }

        medRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Medical_Record_History", "id", id
        ));
        medRepo.deleteById(id);
        return "Medical_Record_History is deleted successfully.";
    }


}
