package notebook.view;

import notebook.controller.UserController;
import notebook.logger.Log;
import notebook.model.User;
import notebook.util.Commands;

import java.util.logging.Logger;
import java.util.Scanner;
import java.util.logging.Level;

public class UserView {
    private final UserController userController;
    private final static Logger log = Log.log(UserView.class.getName());

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Enter command: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    log.log(Level.INFO, "New note is created: " + u);
                    break;
                case READ:
                    String id = prompt("Enter user ID: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                        log.log(Level.INFO, "Note is read: " + user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Enter user ID: ");
                    userController.updateUser(userId, createUser());
                    log.log(Level.INFO, "Note is updated, id: " + userId);
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Name: ");
        String lastName = prompt("Surname: ");
        String phone = prompt("Phone number: ");
        return new User(firstName, lastName, phone);
    }
}
