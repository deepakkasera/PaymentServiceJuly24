package com.example.paymentservicejuly24.controllers;

import com.example.paymentservicejuly24.dtos.GeneratePaymentLinkRequestDto;
import com.example.paymentservicejuly24.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(@Qualifier("stripePaymentGateway") PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("Added print statement to test git");
    }

    //POST -> http://localhost:8080/payments
    @PostMapping()
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto requestDto) throws RazorpayException, StripeException {
        //Ideally we should handle the exception in the Controller using Controller Advices.
        return paymentService.generatePaymentLink(requestDto.getOrderId());
    }

    @PostMapping("/webhook")
    public void handleWebhookEvent(@RequestBody Object object) {
        System.out.println("Webhook triggered");
    }
}
