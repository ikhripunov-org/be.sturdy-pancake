package org.ikhripunov.pancake.controller;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.ikhripunov.pancake.jooq.tables.daos.CustomerDao;
import org.ikhripunov.pancake.jooq.tables.daos.NoteDao;
import org.ikhripunov.pancake.jooq.tables.pojos.Customer;
import org.ikhripunov.pancake.jooq.tables.pojos.Note;
import org.joda.time.DateTime;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.ikhripunov.pancake.jooq.tables.Customer.CUSTOMER;

@Controller
public class CustomersController {

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private DozerBeanMapper mapper;

    public List<org.ikhripunov.pancake.model.Customer> findCustomers(String name) {
        List<Condition> conditions = new ArrayList<>();
        if (StringUtils.isNotEmpty(name)) {
            conditions.add(CUSTOMER.NAME.likeIgnoreCase(name));
        }
        return dslContext.select().from(CUSTOMER).where(conditions.toArray(new Condition[0]))
                .fetchInto(Customer.class).stream()
                .map(customer -> mapper.map(customer, org.ikhripunov.pancake.model.Customer.class))
                .collect(Collectors.toList());
    }

    public void createCustomer(org.ikhripunov.pancake.model.Customer customer) {
        customer.setCreated(DateTime.now().getMillis());
        customer.setId(UUID.randomUUID());
        customerDao.insert(mapper.map(customer, Customer.class));
    }

    public void deleteCustomer(UUID userId) {
        customerDao.fetchByExternalId(userId.toString()).forEach(customer -> customerDao.delete(customer));
    }


    public org.ikhripunov.pancake.model.Customer getCustomer(UUID customerId) {
        return mapper.map(customerDao.fetchOneByExternalId(customerId.toString()), org.ikhripunov.pancake.model.Customer.class);
    }

    public void updateCustomer(UUID customerId, org.ikhripunov.pancake.model.Customer customer) {
        customer.setId(customerId);
        customerDao.update(mapper.map(customer, Customer.class));
    }

    public void addNoteForCustomer(UUID customerId, org.ikhripunov.pancake.model.Note note) {
        Customer customer = customerDao.fetchOneByExternalId(customerId.toString());
        noteDao.insert(mapper.map(note, Note.class).setCustomerId(customer.getId()));
    }

    public void deleteNoteForCustomer(UUID noteId) {
        noteDao.delete(noteDao.fetchByExternalId(noteId.toString()));
    }

    public void updateNoteForCustomer(UUID noteId, org.ikhripunov.pancake.model.Note note) {
        noteDao.update(mapper.map(note, Note.class).setId(noteDao.fetchOneByExternalId(noteId.toString()).getId()));
    }
}
