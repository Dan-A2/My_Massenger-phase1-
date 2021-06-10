import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Setting {

    static private final Logger logger = (Logger) LogManager.getLogger(Setting.class);

    public static void showSettings(User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "This is your settings, what do you wanna do?");
        System.out.println("1. Privacy settings");
        System.out.println("2. Delete account");
        System.out.println("3. Log out");
        System.out.println("4. None, get back");
        String respond = MyScanner.GetScanner1().next();
        respond = CheckValidity.numeric(4, respond);
        if(respond.equals("1")){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Here is your privacy settings. What do you wanna change?");
            System.out.println("1. public/private");
            System.out.println("2. online/last seen");
            System.out.println("3. activate/inactivate");
            System.out.println("4. password");
            System.out.println("5. None, get back to main menu");
            respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.numeric(5, respond);
            if(respond.equals("1")){
                privacy(user);
                SaveNLoad.save();
                showSettings(user);
            }
            else if(respond.equals("2")){
                onlineLastSeen(user);
                SaveNLoad.save();
                showSettings(user);
            }
            else if(respond.equals("3")){
                activation(user);
                SaveNLoad.save();
                showSettings(user);
            }
            else if(respond.equals("4")){
                passwordCheck(user);
                SaveNLoad.save();
                showSettings(user);
            }
            else{
                // =))
            }
        }
        else if(respond.equals("2")) {
            if (user.isActive()) {
                System.out.println(ConsoleColors.RED_BOLD + "Are you sure you want to delete your account? (y/n)");
                respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    for (User user1 : User.getActiveUsers()) {
                        user1.getFriendsId().remove(new Integer(user.getId()));
                        user1.getFollowingsId().remove(new Integer(user.getId()));
                        user1.getFollowersId().remove(new Integer(user.getId()));
                        for (int i = 0; i < user1.getMyChats().size(); i++) {
                            Chat tmp = user1.getMyChats().get(i);
                            if (tmp.getUser1() != null && tmp.getUser2() != null) {
                                if (tmp.getUser1().getUsername().equals(user.getUsername()) || tmp.getUser2().getUsername().equals(user.getUsername())) {
                                    user1.getMyChatsId().remove(new Integer(tmp.getID()));
                                    i = -1;
                                }
                            }
                        }
                        for (int i = 0; i < user1.getSavedMassages().size(); i++) {
                            if (user1.getSavedMassages().get(i).getSender() != null) {
                                if (user1.getSavedMassages().get(i).getSender().getUsername().equals(user.getUsername())) {
                                    user1.getSavedMassagesId().remove(i);
                                    i = -1;
                                }
                            } else {
                                System.out.println(ConsoleColors.RED_BOLD + "This user has inactivated his/her account :/");
                            }
                        }
                        for (int i = 0; i < user1.getSavedTweets().size(); i++) {
                            if (user1.getSavedTweets().get(i).getSender().getUsername().equals(user.getUsername())) {
                                user1.getSavedTweetsId().remove(i);
                                i = -1;
                            }
                        }
                        for (String key : user1.getMySortings().keySet()) {
                            for (int i = 0; i < user1.getMySortings().get(key).size(); i++) {
                                int userid = user1.getMySortings().get(key).get(i);
                                if(userid == user.getId()){
                                    user1.getMySortings().get(key).remove(i);
                                    i = -1;
                                }
                            }
                        }
                    }
                    for (User user1 : User.getInactiveUsers()) {
                        user1.getFriendsId().remove(new Integer(user.getId()));
                        user1.getFollowingsId().remove(new Integer(user.getId()));
                        user1.getFollowersId().remove(new Integer(user.getId()));
                        for (int i = 0; i < user1.getMyChats().size(); i++) {
                            Chat tmp = user1.getMyChats().get(i);
                            if (tmp.getUser1() != null && tmp.getUser2() != null) {
                                if (tmp.getUser1().getUsername().equals(user.getUsername()) || tmp.getUser2().getUsername().equals(user.getUsername())) {
                                    user1.getMyChatsId().remove(new Integer(tmp.getID()));
                                    i = -1;
                                }
                            }
                        }
                        for (int i = 0; i < user1.getSavedMassages().size(); i++) {
                            if (user1.getSavedMassages().get(i).getSender() != null) {
                                if (user1.getSavedMassages().get(i).getSender().getUsername().equals(user.getUsername())) {
                                    user1.getSavedMassagesId().remove(i);
                                    i = -1;
                                }
                            } else {
                                System.out.println(ConsoleColors.RED_BOLD + "This user has inactivated his/her account :/");
                            }
                        }
                        for (int i = 0; i < user1.getSavedTweets().size(); i++) {
                            if (user1.getSavedTweets().get(i).getSender().getUsername().equals(user.getUsername())) {
                                user1.getSavedTweetsId().remove(i);
                                i = -1;
                            }
                        }
                        for (String key : user1.getMySortings().keySet()) {
                            for (int i = 0; i < user1.getMySortings().get(key).size(); i++) {
                                int userid = user1.getMySortings().get(key).get(i);
                                if(userid == user.getId()){
                                    user1.getMySortings().get(key).remove(i);
                                    i = -1;
                                }
                            }
                        }
                    }
                    for (int i = 0; i < Tweet_Comment.getAll().size(); i++) {
                        if (Tweet_Comment.getAll().get(i).getSender().getUsername().equals(user.getUsername())) {
                            Tweet_Comment.getAll().remove(i);
                            i = -1;
                        }
                    }
                    for (int i = 0; i < Chat.getAllChats().size(); i++) {
                        if (Chat.getAllChats().get(i).getUser1().getId() == user.getId() || Chat.getAllChats().get(i).getUser2().getId() == user.getId()) {
                            if (Chat.getAllChats().get(i).getUser1().getId() == user.getId()) {
                                Chat.getAllChats().get(i).getUser1().getMyChatsId().remove(new Integer(Chat.getAllChats().get(i).getID()));
                            } else {
                                Chat.getAllChats().get(i).getUser2().getMyChatsId().remove(new Integer(Chat.getAllChats().get(i).getID()));
                            }
                            Chat.getAllChats().remove(i);
                            i = -1;
                        }
                    }
                    User.getActiveUsers().remove(user);
                    User.getInactiveUsers().remove(user);
                    System.out.println(ConsoleColors.PURPLE_BOLD + "Account deleted successfully...");
                    SaveNLoad.save();
                    Entrance.login();
                } else {
                    SaveNLoad.save();
                    showSettings(user);
                }
            }
            else{
                System.out.println(ConsoleColors.RED_BOLD + "You have to activate your account first!");
                showSettings(user);
            }
        }
        else if(respond.equals("3")){
            user.setLastSeen();
            System.out.println(ConsoleColors.YELLOW_BOLD + "You are logged out");
            Entrance.login();
        }
        else if(respond.equals("4")){
            MainMenu.showOptions(user);
        }

    }

    static void privacy(User user){

        if(user.isAccountPublic()){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Your account is now public.");
        }
        else{
            System.out.println(ConsoleColors.YELLOW_BOLD + "Your account is now private.");
        }
        System.out.println(ConsoleColors.YELLOW_BOLD + "Do you want to change it? (y/n)");
        String respond = MyScanner.GetScanner1().next();
        respond = CheckValidity.yn(respond);
        if(respond.equals("y")){
            logger.info("user " + user.getUsername() + " has changed public/private");
            user.setAccountPublic(!user.isAccountPublic());
            System.out.println(ConsoleColors.GREEN_BOLD + "Operation done successfully!");
            SaveNLoad.save();
        }

    }

    static void onlineLastSeen(User user){

        if(user.getWhoCanSeeLastSeen().equals("everybody")){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Currently, everyone can see your lastseen and stuff");
        }
        else if(user.getWhoCanSeeLastSeen().equals("followers")){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Currently, your followers can see your lastseen and stuff");
        }
        else{
            System.out.println(ConsoleColors.YELLOW_BOLD + "Currently, nobody can see your lastseen and stuff");
        }
        System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to change it? (y/n)");
        String respond = MyScanner.GetScanner1().next();
        respond = CheckValidity.yn(respond);
        if(respond.equals("y")){
            System.out.println(ConsoleColors.YELLOW_BOLD + "What would you like it to be?");
            System.out.println("1. everybody");
            System.out.println("2. followers");
            System.out.println("3. nobody");
            respond = MyScanner.GetScanner1().next();
            respond =CheckValidity.numeric(3, respond);
            if(respond.equals("1")){
                user.setWhoCanSeeLastSeen("everybody");
            }
            else if(respond.equals("2")){
                user.setWhoCanSeeLastSeen("followers");
            }
            else{
                user.setWhoCanSeeLastSeen("nobody");
            }
            logger.info("user has changed whocanseelastseen and stuff");
            System.out.println(ConsoleColors.GREEN_BOLD + "Task done successfully");
        }

    }

    static void activation(User user){

        if(user.isActive()){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Your account is currently active");
        }
        else{
            System.out.println(ConsoleColors.YELLOW_BOLD + "Your account is currently inactive");
        }
        System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to change it? (y/n)");
        String respond = MyScanner.GetScanner1().next();
        respond = CheckValidity.yn(respond);
        if(respond.equals("y")){
            if(user.isActive()){
                User.getActiveUsers().remove(user);
                User.getInactiveUsers().add(user);
            }
            else{
                User.getInactiveUsers().remove(user);
                User.getActiveUsers().add(user);
            }
            user.setActive(!user.isActive());
            logger.info("user " + user.getUsername() + " has changed it's active status");
            System.out.println(ConsoleColors.GREEN_BOLD + "Task done successfully!");
        }

    }

    static void passwordCheck(User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "Your current password is " + user.getPassword());
        System.out.println(ConsoleColors.YELLOW_BOLD + "Do you wanna change it? (y/n)");
        String respond = MyScanner.GetScanner1().next();
        respond = CheckValidity.yn(respond);
        if(respond.equals("y")){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Type down your new password: ");
            String pass = MyScanner.GetScanner1().nextLine();
            user.setPassword(pass);
            logger.info("user " + user.getUsername() + " has changed it's password");
            System.out.println(ConsoleColors.GREEN_BOLD + "Task done successfully!");
        }

    }

}