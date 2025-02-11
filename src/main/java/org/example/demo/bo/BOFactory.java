package org.example.demo.bo;

import org.example.demo.bo.custom.SupplierBO;
import org.example.demo.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }

    public enum BOType {
        EMPLOYEE, SUPPLIER, SCHEDULE, CUSTOMER,INGREDIENT
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
            default:
                return null;
        }
    }
}
