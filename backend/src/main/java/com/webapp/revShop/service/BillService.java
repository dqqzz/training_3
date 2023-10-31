package com.webapp.revShop.service;

import com.webapp.revShop.entity.Bill;

import java.util.List;

public interface BillService {

    List<Bill> getAllBills();

    Bill getBillById(Integer billId);

    Bill createBill(Bill bill);

    Bill updateBill(Integer billId, Bill bill);

    void deleteBill(Integer billId);
}
