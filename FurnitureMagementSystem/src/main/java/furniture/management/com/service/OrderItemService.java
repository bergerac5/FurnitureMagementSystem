package furniture.management.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furniture.management.com.model.OrderItem;
import furniture.management.com.repository.OrderItemRepository;
import furniture.management.com.response.MessageResponse;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository OrderItemRep;

    // save the OrderItem
    public MessageResponse saveOrderItem(OrderItem OrderItem) {
        MessageResponse resp = new MessageResponse();
        if (OrderItem != null) {

            OrderItemRep.save(OrderItem);
            resp.setMessage("OrderItem saved successfully");

        } else {
            resp.setMessage("Invalid OrderItem");
        }
        return resp;
    }

    // update the OrderItem
    public MessageResponse updateOrderItem(OrderItem OrderItem) {
        MessageResponse resp = new MessageResponse();
        if (OrderItem != null) {
            boolean checkOrderItemId = OrderItemRep.existsById(OrderItem.getId());
            if (checkOrderItemId == true) {
                OrderItemRep.save(OrderItem);
                resp.setMessage("OrderItem updated successfully");
                ;
            } else {
                resp.setMessage("OrderItem not exists");
            }
        } else {
            resp.setMessage("Invalid OrderItem");
        }
        return resp;
    }

    // delete the OrderItem
    public MessageResponse deleteOrderItem(OrderItem OrderItem) {
        MessageResponse resp = new MessageResponse();
        if (OrderItem != null) {
            boolean checkOrderItemId = OrderItemRep.existsById(OrderItem.getId());
            if (checkOrderItemId == true) {
                OrderItemRep.delete(OrderItem);
                resp.setMessage("OrderItem deleted successfully");

            } else {
                resp.setMessage("OrderItem not exists");
            }
        } else {
            resp.setMessage("Invalid OrderItem");
        }
        return resp;
    }

    // get all OrderItems
    public List<OrderItem> getAllOrderItems() {
        return OrderItemRep.findAll();
    }

    // get OrderItem by id
    public OrderItem getOrderItemById(Long id) {
        return OrderItemRep.findById(id).orElse(null);
    }

}
