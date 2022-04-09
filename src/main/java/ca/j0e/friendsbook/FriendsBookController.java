package ca.j0e.friendsbook;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FriendsBookController implements Initializable {
    @FXML
    private ListView<Friend> friendsList;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Button deleteFriendButton;

    // Listener that listens for new selected friend and updates friend info labels accordingly
    private final ChangeListener<Friend> selectionChangeListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue observable, Friend oldSel, Friend newSel) {
            // If no new selection, prompt user to select something
            if (newSel != null) {
                nameLabel.setText("Name: " + newSel.getName());
                phoneNumberLabel.setText("Phone number: " + newSel.getPhoneNumber());
                emailLabel.setText("Email: " + newSel.getEmail());

                deleteFriendButton.setDisable(false);   // friend selected, enable delete friend button
            }
            else {
                nameLabel.setText("Select/create a friend!");
                phoneNumberLabel.setText("");
                emailLabel.setText("");

                deleteFriendButton.setDisable(true);    // no friend selected, so disable delete friend button
            }
        }
    };

    // Event handler for add friend button
    @FXML
    protected void addFriend() {
        // Get info from fields to construct Friend obj
        String name = nameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();
        Friend newFriend = new Friend(name, phoneNumber, email);

        // Add Friend to list
        friendsList.getItems().add(newFriend);

        // Clear fields
        nameField.clear();
        phoneNumberField.clear();
        emailField.clear();

        // Select/focus new friend
        friendsList.getSelectionModel().select(newFriend);
        friendsList.requestFocus();
    }

    // Event handler for delete friend button
    @FXML
    protected void deleteFriend() {
        Friend selected = friendsList.getSelectionModel().getSelectedItem();
        friendsList.getItems().remove(selected);
    }

    // Initialization of view
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set change listener
        friendsList.getSelectionModel().selectedItemProperty().addListener(selectionChangeListener);
    }
}
