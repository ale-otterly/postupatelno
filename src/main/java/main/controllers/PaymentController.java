package main.controllers;

import main.model.entities.Payment;
import main.model.entities.enums.PaymentMethod;
import main.model.repositories.PaymentRepository;
import main.model.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/payments")
    public ResponseEntity<Payment> add(int studentId, int amount, String dateTime, PaymentMethod method) {
        Payment payment = new Payment();
        payment.setStudent(studentRepository.findById(studentId).get());
        payment.setAmount(amount);
        payment.setDateTime(dateTime);
        payment.setPaymentMethod(method);
        paymentRepository.save(payment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@GetMapping("/payments")
    public List<Payment> getAll() {
        Iterable<Payment> paymentIterable = paymentRepository.findAll();
        ArrayList<Payment> paymentList = new ArrayList<>();
        for (Payment payment : paymentIterable) {
            paymentList.add(payment);
        }
        return paymentList;
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getById(@PathVariable int id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if(paymentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(paymentOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        ResponseEntity<?> responseEntity = ResponseEntity.status(getById(id).getStatusCode()).body(null);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            paymentRepository.deleteById(id);
        }
        return responseEntity;
    }

    //TODO UPDATE method for payment

    @GetMapping("/payments")
    public String page(Model model) {
        model.addAttribute("paymentsData", getAll());
        return "payments_index";
    }
}
