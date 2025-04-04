package furniture.management.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furniture.management.com.model.Order;
import furniture.management.com.repository.OrderRepository;
import furniture.management.com.response.MessageResponse;

@Service
public class OrderService {
    @Autowired
    private OrderRepository OrderRep;

    // save the Order
    public MessageResponse saveOrder(Order Order) {
        MessageResponse resp = new MessageResponse();
        if (Order != null) {

            OrderRep.save(Order);
            resp.setMessage("Order saved successfully");

        } else {
            resp.setMessage("Invalid Order");
        }
        return resp;
    }

    // update the Order
    public MessageResponse updateOrder(Order Order) {
        MessageResponse resp = new MessageResponse();
        if (Order != null) {
            boolean checkOrderId = OrderRep.existsById(Order.getId());
            if (checkOrderId == true) {
                OrderRep.save(Order);
                resp.setMessage("Order updated successfully");
                ;
            } else {
                resp.setMessage("Order not exists");
            }
        } else {
            resp.setMessage("Invalid Order");
        }
        return resp;
    }

    // delete the Order
    public MessageResponse deleteOrder(Order Order) {
        MessageResponse resp = new MessageResponse();
        if (Order != null) {
            boolean checkOrderId = OrderRep.existsById(Order.getId());
            if (checkOrderId == true) {
                OrderRep.delete(Order);
                resp.setMessage("Order deleted successfully");

            } else {
                resp.setMessage("Order not exists");
            }
        } else {
            resp.setMessage("Invalid Order");
        }
        return resp;
    }

    // get all Orders
    public List<Order> getAllOrders() {
        return OrderRep.findAll();
    }

    // get Order by id
    public Order getOrderById(Long id) {
        return OrderRep.findById(id).orElse(null);
    }
}
