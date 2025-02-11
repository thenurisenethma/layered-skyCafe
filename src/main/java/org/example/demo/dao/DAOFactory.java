package org.example.demo.dao;
import org.example.demo.dao.custom.SupplierDAO;
import org.example.demo.dao.custom.impl.*;


public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        EMPLOYEE, SUPPLIER, SCHEDULE, CUSTOMER, INVENTORY, INGREDIENT
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SCHEDULE:
                return new ScheduleDAOImpl();
            case INGREDIENT:
                return new IngredientDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            default:
                return null;
        }
    }

}
