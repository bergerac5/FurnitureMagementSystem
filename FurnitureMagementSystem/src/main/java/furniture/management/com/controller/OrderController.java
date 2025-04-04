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

import furniture.management.com.model.Order;
import furniture.management.com.response.MessageResponse;
import furniture.management.com.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService OrderService;

    @PostMapping(value = "/saveOrder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveOrder(@RequestBody Order Order) {
        MessageResponse response = OrderService.saveOrder(Order);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Order saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Order":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updateOrder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateOrder(@RequestBody Order Order) {
        MessageResponse response = OrderService.updateOrder(Order);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Order not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Order updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Order":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deleteOrder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteOrder(@RequestBody Order Order) {
        MessageResponse response = OrderService.deleteOrder(Order);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "Order not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "Order deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid Order":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> Orders = OrderService.getAllOrders();
        return new ResponseEntity<>(Orders, HttpStatus.OK);
    }

    @GetMapping(value = "/getOrderById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order Order = OrderService.getOrderById(id);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }
}
