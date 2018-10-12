package org.ikhripunov.pancake.impl;

import org.ikhripunov.pancake.api.CustomersApi;
import org.ikhripunov.pancake.controller.CustomersController;
import org.ikhripunov.pancake.model.Customer;
import org.ikhripunov.pancake.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class CustomersApiImpl implements CustomersApi {

    @Autowired
    private CustomersController customersController;

    @Override
    public List<Customer> customersGet(String name) throws Exception {
        return customersController.findCustomers(name);
    }

    @Override
    public void customersPost(@Valid Customer customer) throws Exception {
        customersController.createCustomer(customer);
    }

    @Override
    public void customersCustomerIdDelete(UUID customerId) throws Exception {
        customersController.deleteCustomer(customerId);
    }

    @Override
    public Customer customersCustomerIdGet(UUID customerId) throws Exception {
        return customersController.getCustomer(customerId);
    }

    @Override
    public void customersCustomerIdPut(UUID customerId, @Valid Customer customer) throws Exception {
        customersController.updateCustomer(customerId, customer);
    }

    @Override
    public void customersCustomerIdNotesPost(UUID customerId, @Valid Note note) throws Exception {
        customersController.addNoteForCustomer(customerId, note);
    }

    @Override
    public void customersNotesNoteIdDelete(UUID noteId) throws Exception {
        customersController.deleteNoteForCustomer(noteId);
    }

    @Override
    public void customersNotesNoteIdPut(UUID noteId, @Valid Note note) throws Exception {
        customersController.updateNoteForCustomer(noteId, note);
    }
}
