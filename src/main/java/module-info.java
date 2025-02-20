module ananpn.siistitjavajutut {
    requires javafx.controls;
    requires javafx.fxml;


    opens ananpn.siistitjavajutut to javafx.fxml;
    exports ananpn.siistitjavajutut;
}