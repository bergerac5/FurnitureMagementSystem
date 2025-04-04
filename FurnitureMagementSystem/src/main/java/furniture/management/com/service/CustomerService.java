package furniture.management.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furniture.management.com.model.Customer;
import furniture.management.com.repository.CustomerRepository;
import furniture.management.com.response.MessageResponse;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository CustomerRep;

    // save the Customer
    public MessageResponse saveCustomer(Customer customer) {
        MessageResponse resp = new MessageResponse();
        if (customer != null) {

            CustomerRep.save(customer);
            resp.setMessage("Customer saved successfully");

        } else {
            resp.setMessage("Invalid Customer");
        }
        return resp;
    }

    // update the Customer
    public MessageResponse updateCustomer(Customer customer) {
        MessageResponse resp = new MessageResponse();
        if (customer != null) {
            boolean checkCustomerId = CustomerRep.existsById(customer.getId());
            if (checkCustomerId == true) {
                CustomerRep.save(customer);
                resp.setMessage("Customer updated successfully");
                ;
            } else {
                resp.setMessage("Customer not exists");
            }
        } else {
            resp.setMessage("Invalid Customer");
        }
        return resp;
    }

    // update the Customer
    public MessageResponse deleteCustomer(Customer customer) {
        MessageResponse resp = new MessageResponse();
        if (customer != null) {
            boolean checkCustomerId = CustomerRep.existsById(customer.getId());
            if (checkCustomerId == true) {
                CustomerRep.delete(customer);
                resp.setMessage("Customer deleted successfully");

            } else {
                resp.setMessage("Customer not exists");
            }
        } else {
            resp.setMessage("Invalid Customer");
        }
        return resp;
    }

    // get all Customers
    public List<Customer> getAllCustomers() {
        return CustomerRep.findAll();
    }

    // get Customer by id
    public Customer getCustomerById(Long id) {
        return CustomerRep.findById(id).orElse(null);
    }
}
