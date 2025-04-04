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

import furniture.management.com.model.OrderItem;
import furniture.management.com.response.MessageResponse;
import furniture.management.com.service.OrderItemService;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    @Autowired
    private OrderItemService OrderItemService;

    @PostMapping(value = "/saveOrderItem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveOrderItem(@RequestBody OrderItem OrderItem) {
        MessageResponse response = OrderItemService.saveOrderItem(OrderItem);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "OrderItem saved successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid OrderItem":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/updateOrderItem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateOrderItem(@RequestBody OrderItem OrderItem) {
        MessageResponse response = OrderItemService.updateOrderItem(OrderItem);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "OrderItem not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "OrderItem updated successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid OrderItem":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping(value = "/deleteOrderItem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteOrderItem(@RequestBody OrderItem OrderItem) {
        MessageResponse response = OrderItemService.deleteOrderItem(OrderItem);
        HttpStatus httpStatus;

        switch (response.getMessage()) {
            case "OrderItem not exists":
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case "OrderItem deleted successfully":
                httpStatus = HttpStatus.CREATED;
                break;
            case "Invalid OrderItem":
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getAllOrderItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> OrderItems = OrderItemService.getAllOrderItems();
        return new ResponseEntity<>(OrderItems, HttpStatus.OK);
    }

    @GetMapping(value = "/getOrderItemById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem OrderItem = OrderItemService.getOrderItemById(id);
        return new ResponseEntity<>(OrderItem, HttpStatus.OK);

    }

}
