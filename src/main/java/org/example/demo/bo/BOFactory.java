package org.example.demo.bo;

import org.example.demo.bo.custom.impl.*;
public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        EMPLOYEE, SUPPLIER, SCHEDULE, CUSTOMER, INVENTORY, PRODUCT, INGREDIENT,RESERVATION,ORDERS
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SCHEDULE:
                return new ScheduleBOImpl();
            case INGREDIENT:
                return new IngredientBOImpl();
            case INVENTORY:
                return new InventoryBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
                case ORDERS:
                    return new OrdersBOImpl();
            default:
                return null;
        }
    }
}
