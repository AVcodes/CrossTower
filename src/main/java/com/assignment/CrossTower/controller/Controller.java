package com.assignment.CrossTower.controller;

import com.assignment.CrossTower.entity.CustomersData;
import com.assignment.CrossTower.model.ExchangeModel;
import com.assignment.CrossTower.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private CustomerDataService customerDataService;

    //to get Account Balance
    @GetMapping("/account/{accNbr}")
    public ResponseEntity<CustomersData> getAccBalance(@PathVariable("accNbr") long accNbr) {

        return ResponseEntity.ok().body(customerDataService.getBalance(accNbr));
    }

    //to add new Account
    @PostMapping("/account")
    public ResponseEntity<CustomersData> addAcc(@RequestBody CustomersData data) {
        return ResponseEntity.ok().body(this.customerDataService.addAccount(data));
    }

    //to deposit in Account
    @PutMapping("/account/deposit/{accNbr}")
    public ResponseEntity<CustomersData> accDeposit(@RequestBody CustomersData data, @PathVariable("accNbr") long accNbr) {
        data.setAccountNumber(accNbr);
        return ResponseEntity.ok().body(this.customerDataService.depositInAccount(data));
    }

    //to withdraw from Account
    @PutMapping("/account/withdraw/{accNbr}")
    public ResponseEntity<CustomersData> accWithdraw(@RequestBody CustomersData data, @PathVariable("accNbr") long accNbr) {
        data.setAccountNumber(accNbr);
        return ResponseEntity.ok().body(this.customerDataService.withdrawFromAccount(data));
    }

    //to transfer currency
    @PutMapping("/account/exchange")
    public ResponseEntity<CustomersData> exchange(@RequestBody ExchangeModel data) {

        return ResponseEntity.ok().body(this.customerDataService.exchangeCurrency(data));
    }

}
