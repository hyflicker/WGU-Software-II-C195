module com.arobertson.software_ii__c195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens arobertson.C195 to javafx.fxml;
    exports arobertson.C195;
    exports arobertson.C195.Controllers;
    exports arobertson.C195.Models;
    opens arobertson.C195.Controllers to javafx.fxml;
    exports arobertson.C195.DAO;
    opens arobertson.C195.DAO to javafx.fxml;
    opens arobertson.C195.Models to javafx.fxml;
}