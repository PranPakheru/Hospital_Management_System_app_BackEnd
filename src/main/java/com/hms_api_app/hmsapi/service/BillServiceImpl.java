package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.BillDto;
import com.hms_api_app.hmsapi.entity.Bill;
import com.hms_api_app.hmsapi.errorHandler.ResourceNotFoundException;
import com.hms_api_app.hmsapi.repository.BillRepository;
import com.hms_api_app.hmsapi.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService{

    //other class objects.
    @Autowired
    private BillRepository billRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientRepository patientRepo;


    //entity-dto conversion methods.
    private Bill mapToEntity(BillDto billDto){
        Bill bills = modelMapper.map(billDto, Bill.class);
        return bills;
    }

    private BillDto mapToDto(Bill bill){
        BillDto billDto = modelMapper.map(bill, BillDto.class);
        return billDto;
    }


    //implementation methods.
    @Override
    public List<BillDto> createBillPatient(BillDto billDto) {
        Bill bill = mapToEntity(billDto);
        Bill savedBill = billRepo.save(bill);
        BillDto savedDto = mapToDto(savedBill);

        List<BillDto> listBill = new ArrayList<>();
        listBill.add(savedDto);
        return listBill;
    }

    @Override
    public List<BillDto> getBills(long patientId) {
        patientRepo.findById(patientId).orElseThrow(()->new ResourceNotFoundException(
                "Patient", "id", patientId
        ));

        List<Bill> billsByPatientId = billRepo.getBillsByPatientId(patientId);
        List<BillDto> collect = billsByPatientId.stream().map(this::mapToDto).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String deleteBillById(long id) {
//        Optional<Bill> byId = billRepo.findById(id);
//        if(byId.isPresent()){
//            billRepo.deleteById(id);
//            return "Bill deleted successfully.";
//        }
//        else{
//            return "Bill not found!";
//        }

        billRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Bill", "id", id
        ));
        billRepo.deleteById(id);
        return "Bill deleted successfully.";
    }
}
