package com.example.hfb.model.dto;

import com.example.hfb.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RequestDetail {
    private Integer recipientId;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddr;
    private String supplierPhone;
    private String supplierAddr;
    private Integer supplierId;
    private String supplierName;
    private Integer foodId;
    private String foodName;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private String message;
    private int status;
    private String expirationDate;

    public RequestDetail(Integer recipientId, String recipientName, String recipientPhone, String recipientAddr, Integer supplierId, String supplierName, String supplierPhone, String supplierAddr, Integer foodId, String foodName, long createdAt, long updatedAt, Integer createdBy, Integer updatedBy, String message, int status, Long expirationDate) {
        this.recipientId = recipientId;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.recipientAddr = recipientAddr;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierPhone = supplierPhone;
        this.supplierAddr = supplierAddr;
        this.foodId = foodId;
        this.foodName = foodName;
        this.createdAt = Utilities.convertLongToDate(createdAt);
        this.updatedAt = Utilities.convertLongToDate(updatedAt);
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.message =message;
        this.status = status;
        this.expirationDate = Utilities.convertLongToDate(expirationDate);
    }
}
