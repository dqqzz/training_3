package com.webapp.revShop.service;

import com.webapp.revShop.entity.Bill;
import com.webapp.revShop.repository.BillRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class BillServiceImplTest {

    @InjectMocks
    private BillServiceImpl billService;

    @Mock
    private BillRepository billRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllBills() {

        List<Bill> bills = new ArrayList<>();

        when(billRepository.findAll()).thenReturn(bills);

        List<Bill> result = billService.getAllBills();

        assertEquals(bills, result);
    }

    @Test
    void getBillById_ExistingBill() {

        Integer billId = 1;
        Bill expectedBill = new Bill();

        when(billRepository.findById(billId)).thenReturn(Optional.of(expectedBill));

        Bill result = billService.getBillById(billId);

        assertEquals(expectedBill, result);
    }

    @Test
    void getBillById_NonExistingBill() {

        Integer billId = 1;

        when(billRepository.findById(billId)).thenReturn(Optional.empty());

        Bill result = billService.getBillById(billId);

        assertNull(result);
    }

    @Test
    void createBill() {

        Bill billToCreate = new Bill();

        when(billRepository.save(billToCreate)).thenReturn(billToCreate);

        Bill result = billService.createBill(billToCreate);

        assertEquals(billToCreate, result);
    }

    @Test
    void updateBill_ExistingBill() {
        Integer billId = 1;
        Bill updatedBill = new Bill();
        Bill existingBill = new Bill();
        when(billRepository.findById(billId)).thenReturn(Optional.of(existingBill));
        when(billRepository.save(existingBill)).thenReturn(existingBill);

        Bill result = billService.updateBill(billId, updatedBill);

        assertEquals(existingBill, result);

        assertEquals(updatedBill.getBill_total(), existingBill.getBill_total());
        assertEquals(updatedBill.getBill_payment_method(), existingBill.getBill_payment_method());
    }

    @Test
    void updateBill_NonExistingBill() {

        Integer billId = 1;
        Bill updatedBill = new Bill();

        when(billRepository.findById(billId)).thenReturn(Optional.empty());

        Bill result = billService.updateBill(billId, updatedBill);

        assertNull(result);
    }

    @Test
    void deleteBill() {

        Integer billId = 1;

        billService.deleteBill(billId);

        verify(billRepository, times(1)).deleteById(billId);
    }
}
