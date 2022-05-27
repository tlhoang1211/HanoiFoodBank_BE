package com.example.hfb.Controller;

import com.example.hfb.config.UrlConfig;
import com.example.hfb.config.PaypalPaymentIntent;
import com.example.hfb.config.PaypalPaymentMethod;
import com.example.hfb.entity.Donation;
import com.example.hfb.model.ResponseData;
import com.example.hfb.service.serviceimpl.PaypalService;
import com.example.hfb.utilities.Utilities;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@RestController
@RequestMapping(value = UrlConfig.END_POINT_PAY)
@CrossOrigin(origins = "*")
public class PaymentController {
    public static final String URL_PAYPAL_SUCCESS = UrlConfig.END_POINT_PAY + "/success";
    public static final String URL_PAYPAL_CANCEL = UrlConfig.END_POINT_PAY + "/cancel";
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PaypalService paypalService;

    @PostMapping("")
    public Payment pay(HttpServletRequest request,
                       @RequestParam("name") String name,
                       @RequestParam("phone") String phone,
                       @RequestParam("amount") double amount,
                       @RequestParam("content") String content){
        String cancelUrl = Utilities.getBaseURL(request)  + URL_PAYPAL_CANCEL;
        String successUrl = Utilities.getBaseURL(request)  + URL_PAYPAL_SUCCESS;

//        log.error(successUrl.toString());
//        log.error(cancelUrl.toString());
//        log.error(Utilities.getBaseURL(request).toString());
        Donation donation = new Donation();
        donation.setName(name);
        donation.setPhone(phone);
        donation.setContent(content);
        donation.setAmount(amount);
        donation.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        donation.setStatus(1);
        Donation donationSave =  paypalService.saveDonation(donation);
        try {
            Payment payment = paypalService.createPayment(
                    donationSave,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
//            log.error(payment.toString());
            return payment;
        } catch (PayPalRESTException e) {
//            log.error(e.getMessage());
            return null;
        }

    }
    @GetMapping("/cancel")
    public ResponseEntity<ResponseData> cancelPay(){
        return ResponseEntity.ok(new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), "Cancel", ""));
    }
    @GetMapping("/success")
    public ResponseEntity<Payment> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                Transaction transaction = payment.getTransactions().get(0);
                int id = Integer.parseInt(transaction.getItemList().getItems().get(0).getSku());
                Donation donation = paypalService.findById(id);
                if (donation != null) {
                    donation.setStatus(2);
                    paypalService.saveDonation(donation);
                }
                return ResponseEntity.ok(payment);
            }
        } catch (PayPalRESTException e) {
//            log.error(e.getMessage());
        }
        return ResponseEntity.ok(null);
    }
}
