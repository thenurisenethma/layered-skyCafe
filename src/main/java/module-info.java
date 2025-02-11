module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
    exports org.example.demo.controller;
    opens org.example.demo.controller to javafx.fxml;
    exports org.example.demo.dao;
    opens org.example.demo.dao to javafx.fxml;
    exports org.example.demo.dto;
    opens org.example.demo.dto to javafx.fxml;
    exports org.example.demo.tdm;
    opens org.example.demo.tdm to javafx.fxml;

}