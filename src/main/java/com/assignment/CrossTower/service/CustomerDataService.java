package com.assignment.CrossTower.service;

import com.assignment.CrossTower.entity.CustomersData;
import com.assignment.CrossTower.model.ExchangeModel;
import com.assignment.CrossTower.repository.CustomerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class CustomerDataService {

    @Autowired
    private CustomerDataRepository customerDataRepository;

    //Show Account Balance function
    public CustomersData getBalance(long accNbr) {

        CustomersData obj = null;

        Optional<CustomersData> customersDataDb = this.customerDataRepository.findById(accNbr);

        if (customersDataDb.isPresent()) {
            return customersDataDb.get();
        }
        return obj;
    }

    //New Account function
    public CustomersData addAccount(CustomersData obj) {

        return customerDataRepository.save(obj);
    }

    //Deposit function
    public CustomersData depositInAccount(CustomersData data) {

        Optional<CustomersData> customersDataDb = this.customerDataRepository.findById(data.getAccountNumber());

        if (customersDataDb.isPresent()) {
            CustomersData customersDataUpdate = customersDataDb.get();
            customersDataUpdate.setAssetAmount(data.getAssetAmount().add(customersDataUpdate.getAssetAmount()));
            customerDataRepository.save(customersDataUpdate);
            return customersDataUpdate;
        }
        return data;
    }

    //Withdraw function
    public CustomersData withdrawFromAccount(CustomersData data) {

        Optional<CustomersData> customersDataDb = this.customerDataRepository.findById(data.getAccountNumber());

        if (customersDataDb.isPresent()) {
            CustomersData customersDataUpdate = customersDataDb.get();
            customersDataUpdate.setAssetAmount(customersDataUpdate.getAssetAmount().subtract(data.getAssetAmount()));
            customerDataRepository.save(customersDataUpdate);
            return customersDataUpdate;
        }
        return data;
    }


    /*Exchange Rate
      1 BTC = $100
      1 ETH = $10
      1 BTC = 10 ETH */
    //Exchange function
    public CustomersData exchangeCurrency(ExchangeModel data) {

        Optional<CustomersData> customersDataDbFrom = this.customerDataRepository.findById(data.getFromAccNbr());
        Optional<CustomersData> customersDataDbTo = this.customerDataRepository.findById(data.getToAccNbr());

        if (customersDataDbFrom.isPresent()) {
            CustomersData customersDataExchangeFrom = customersDataDbFrom.get();
            CustomersData customersDataExchangeTo = customersDataDbTo.get();

            if (customersDataExchangeFrom.getAssetType() != customersDataExchangeTo.getAssetType()) {
                if (customersDataExchangeFrom.getAssetType().equals("Dollar")) {
                    switch (customersDataExchangeTo.getAssetType()) {
                        case "BTC":
                            customersDataExchangeFrom.setAssetAmount(customersDataExchangeFrom.getAssetAmount().subtract(data.getFromAssetAmount()));
                            customerDataRepository.save(customersDataExchangeFrom);
                            customersDataExchangeTo.setAssetAmount((data.getFromAssetAmount().divide(BigDecimal.valueOf(100))).add(customersDataExchangeTo.getAssetAmount()));
                            customerDataRepository.save(customersDataExchangeTo);
                            break;

                        case "ETH":
                            customersDataExchangeFrom.setAssetAmount(customersDataExchangeFrom.getAssetAmount().subtract(data.getFromAssetAmount()));
                            customerDataRepository.save(customersDataExchangeFrom);
                            customersDataExchangeTo.setAssetAmount((data.getFromAssetAmount().divide(BigDecimal.valueOf(10))).add(customersDataExchangeTo.getAssetAmount()));
                            customerDataRepository.save(customersDataExchangeTo);
                            break;
                    }
                } else if (customersDataExchangeFrom.getAssetType().equals("BTC")) {

                    switch (customersDataExchangeTo.getAssetType()) {
                        case "Dollar":
                            customersDataExchangeFrom.setAssetAmount(customersDataExchangeFrom.getAssetAmount().subtract(data.getFromAssetAmount()));
                            customerDataRepository.save(customersDataExchangeFrom);
                            customersDataExchangeTo.setAssetAmount((data.getFromAssetAmount().multiply(BigDecimal.valueOf(100))).add(customersDataExchangeTo.getAssetAmount()));
                            customerDataRepository.save(customersDataExchangeTo);
                            break;

                        case "ETH":
                            customersDataExchangeFrom.setAssetAmount(customersDataExchangeFrom.getAssetAmount().subtract(data.getFromAssetAmount()));
                            customerDataRepository.save(customersDataExchangeFrom);
                            customersDataExchangeTo.setAssetAmount((data.getFromAssetAmount().multiply(BigDecimal.valueOf(10))).add(customersDataExchangeTo.getAssetAmount()));
                            customerDataRepository.save(customersDataExchangeTo);
                            break;
                    }
                } else if (customersDataExchangeFrom.getAssetType().equals("ETH")) {

                    switch (customersDataExchangeTo.getAssetType()) {
                        case "Dollar":
                            customersDataExchangeFrom.setAssetAmount(customersDataExchangeFrom.getAssetAmount().subtract(data.getFromAssetAmount()));
                            customerDataRepository.save(customersDataExchangeFrom);
                            customersDataExchangeTo.setAssetAmount((data.getFromAssetAmount().multiply(BigDecimal.valueOf(10))).add(customersDataExchangeTo.getAssetAmount()));
                            customerDataRepository.save(customersDataExchangeTo);
                            break;

                        case "BTC":
                            customersDataExchangeFrom.setAssetAmount(customersDataExchangeFrom.getAssetAmount().subtract(data.getFromAssetAmount()));
                            customerDataRepository.save(customersDataExchangeFrom);
                            customersDataExchangeTo.setAssetAmount((data.getFromAssetAmount().divide(BigDecimal.valueOf(10))).add(customersDataExchangeTo.getAssetAmount()));
                            customerDataRepository.save(customersDataExchangeTo);
                            break;
                    }

                }

            } else {
                customersDataExchangeFrom.setAssetAmount(customersDataExchangeFrom.getAssetAmount().subtract(data.getFromAssetAmount()));
                customerDataRepository.save(customersDataExchangeFrom);
                customersDataExchangeTo.setAssetAmount(customersDataExchangeTo.getAssetAmount().add(data.getFromAssetAmount()));
                customerDataRepository.save(customersDataExchangeTo);
            }
        }
        return null;
    }
}
