package com.example.hfb.service.serviceimpl;

import com.example.hfb.entity.Donation;
import com.example.hfb.entity.Food;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.dto.DonationDTO;
import com.example.hfb.model.dto.FoodDTO;
import com.example.hfb.repository.DonationRepository;
import com.example.hfb.repository.FoodRepository;
import com.example.hfb.service.DonationService;
import com.example.hfb.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.function.Function;

@Service
public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationRepository donationRepository;


    @Override
    public ResponseEntity<ResponseData> findById(Integer id) {
        Optional<Donation> donation = donationRepository.findById(id);
        if (donation.isPresent()) {
            DonationDTO donationDTO = DonationDTO.donationDTO(donation.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseData(HttpStatus.OK.value(), "Successfully", donationDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find donation with id " + id, ""));
        }
    }

    @Override
    public ResponseEntity<ResponseData> findAll(String name, String phone, Double amount, String startCreated, String endCreated, int status, int page, String sortBy, int limit, String order) {
        Sort.Direction direction = Sort.Direction.DESC;
        if (order.equals("asc")){
            direction = Sort.Direction.ASC;
        }
        Long startCreatedL = -1L;
        if (!startCreated.equals("")) {
            startCreatedL =  Utilities.convertStringToLong(startCreated);
        }
        Long endCreatedL = -1L;
        if (!endCreated.equals("")) {
            endCreatedL =  Utilities.convertStringToLong(endCreated);
        }
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(direction, sortBy));
        if (limit > 0) {
            pageable = PageRequest.of(page, limit, Sort.by(direction, sortBy));
        }
        Page<Donation> donations = donationRepository.findAll(name,
                phone,
                amount,
                startCreatedL, endCreatedL,
                status,
                pageable);
        Page<DonationDTO> dtoPage = donations.map(new Function<Donation, DonationDTO>() {
            @Override
            public DonationDTO apply(Donation donation) {
                DonationDTO dto = DonationDTO.donationDTO(donation);
                return dto;
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successfully", dtoPage));
    }
}
