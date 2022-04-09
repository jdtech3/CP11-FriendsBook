module ca.j0e.friendsbook {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.j0e.friendsbook to javafx.fxml;
    exports ca.j0e.friendsbook;
}
