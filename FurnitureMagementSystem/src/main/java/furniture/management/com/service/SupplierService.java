package furniture.management.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furniture.management.com.model.Supplier;
import furniture.management.com.repository.SupplierRepository;
import furniture.management.com.response.MessageResponse;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRep;

    // save the Supplier
    public MessageResponse saveSupplier(Supplier supplier) {
        MessageResponse resp = new MessageResponse();
        if (supplier != null) {

            supplierRep.save(supplier);
            resp.setMessage("Supplier saved successfully");

        } else {
            resp.setMessage("Invalid Supplier");
        }
        return resp;
    }

    // update the Supplier
    public MessageResponse updateSupplier(Supplier supplier) {
        MessageResponse resp = new MessageResponse();
        if (supplier != null) {
            boolean checkSupplierId = supplierRep.existsById(supplier.getId());
            if (checkSupplierId == true) {
                supplierRep.save(supplier);
                resp.setMessage("Supplier updated successfully");
                ;
            } else {
                resp.setMessage("Supplier not exists");
            }
        } else {
            resp.setMessage("Invalid Supplier");
        }
        return resp;
    }

    // update the Supplier
    public MessageResponse deleteSupplier(Supplier supplier) {
        MessageResponse resp = new MessageResponse();
        if (supplier != null) {
            boolean checkSupplierId = supplierRep.existsById(supplier.getId());
            if (checkSupplierId == true) {
                supplierRep.delete(supplier);
                resp.setMessage("Supplier deleted successfully");

            } else {
                resp.setMessage("Supplier not exists");
            }
        } else {
            resp.setMessage("Invalid Supplier");
        }
        return resp;
    }

    // get all Suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRep.findAll();
    }

    // get Supplier by id
    public Supplier getSupplierById(Long id) {
        return supplierRep.findById(id).orElse(null);
    }
}
