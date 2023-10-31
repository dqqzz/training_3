package com.webapp.revShop.service;

import com.webapp.revShop.entity.Bill;
import com.webapp.revShop.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Integer billId) {
        Optional<Bill> optionalBill = billRepository.findById(billId);
        return optionalBill.orElse(null);
    }

    @Override
    @Transactional
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    @Transactional
    public Bill updateBill(Integer billId, Bill updatedBill) {
        Optional<Bill> optionalBill = billRepository.findById(billId);
        if (optionalBill.isPresent()) {
            Bill existingBill = optionalBill.get();
            // Update the fields you want to update here
            existingBill.setBill_total(updatedBill.getBill_total());
            existingBill.setBill_payment_method(updatedBill.getBill_payment_method());
            // Save the updated bill
            return billRepository.save(existingBill);
        } else {
            return null; // Bill with the given ID not found
        }
    }

    @Override
    @Transactional
    public void deleteBill(Integer billId) {
        billRepository.deleteById(billId);
    }
}
