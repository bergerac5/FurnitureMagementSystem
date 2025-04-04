package furniture.management.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import furniture.management.com.model.Customer;
import furniture.management.com.response.MessageResponse;
import furniture.management.com.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/saveCustomer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveCustomer(@RequestBody Customer customer) {
        MessageResponse response = customerService.saveCustomer(customer);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Customer saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Customer":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updateCustomer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateCustomer(@RequestBody Customer customer) {
        MessageResponse response = customerService.updateCustomer(customer);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Customer not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Customer updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Customer":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deleteCustomer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteCustomer(@RequestBody Customer customer) {
        MessageResponse response = customerService.deleteCustomer(customer);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Customer not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Customer deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Customer":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllCustomers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/getCustomerById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
