package ca.j0e.friendsbook;

import javafx.scene.control.ListView;

import java.io.*;

public class IOHandler {
    /*
        File spec:
            .txt file
            essentially simplified CSV
            each line represents a friend: name,phone email,email
     */

    /**
     * Loads friends from a File into provided ListView
     * @param file File to load from
     * @param friendsList ListView to load to
     * @return Status/error message
     */
    public static String loadFriends(File file, ListView<Friend> friendsList) {
        if (file == null) {
            return "No file chosen!";
        }

        friendsList.getItems().clear();     // first clear the list

        try {
            // Open the file
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            // Read line by line, split by comma delimiter, construct Friend obj, and add to list
            String line;
            String[] entry;
            while ((line = br.readLine()) != null) {
                entry = line.split(",");
                friendsList.getItems().add(new Friend(entry[0], entry[1], entry[2]));
            }

            br.close();     // close readers

            return "Loaded friends from " + file.getName();     // return status message
        }
        catch (Exception e) {
            // TODO: check for specific errors (e.g. IO, parsing)

            return "Error: " + e.getMessage();  // rather than failing silently, pass on error message
        }
    }

    /**
     * Saves friends from provided ListView to a file
     * @param file File to save to
     * @param friendsList ListView to save from
     * @return Status/error message
     */
    public static String saveFriends(File file, ListView<Friend> friendsList) {
        if (file == null) {
            return "No file chosen!";
        }

        try {
            // Open the file
            FileWriter fr = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fr);

            // Write each friend into file line by line
            for (Friend friend : friendsList.getItems()) {
                br.write(String.format("%s,%s,%s", friend.getName(), friend.getPhoneNumber(), friend.getEmail()));
                br.newLine();
            }

            br.close();     // close readers

            return "Saved friends to " + file.getName();    // return status message
        }
        catch (Exception e) {
            // TODO: check for specific errors (e.g. IO, parsing)

            return "Error: " + e.getMessage();  // rather than failing silently, pass on error message
        }
    }
}
