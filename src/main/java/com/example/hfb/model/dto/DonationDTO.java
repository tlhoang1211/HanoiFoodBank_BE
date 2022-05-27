package com.example.hfb.model.dto;

import com.example.hfb.entity.Donation;
import com.example.hfb.utilities.Utilities;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DonationDTO {
    private Integer id;
    private String name;
    private String phone;
    private Double amount;
    private String content;
    private String createdAt;
    private int status;

    public static DonationDTO donationDTO(Donation donation){
        DonationDTO tmp = new DonationDTO();
        tmp.setId(donation.getId());
        tmp.setName(donation.getName());
        tmp.setPhone(donation.getPhone());
        tmp.setAmount(donation.getAmount());
        tmp.setContent(donation.getContent());
        tmp.setCreatedAt(Utilities.convertLongToDate(donation.getCreatedAt()));
        tmp.setStatus(donation.getStatus());
        return tmp;
    }
}
