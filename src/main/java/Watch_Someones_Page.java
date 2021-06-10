import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Watch_Someones_Page {

    static private final Logger logger = (Logger) LogManager.getLogger(Entrance.class);

    public static void show(User showTo, User showFrom) {

        System.out.println(ConsoleColors.CYAN_BOLD + "Firstname: " + ConsoleColors.WHITE + showFrom.getFirstName());
        System.out.println(ConsoleColors.CYAN_BOLD + "Lastname: " + ConsoleColors.WHITE + showFrom.getLastName());
        System.out.println(ConsoleColors.CYAN_BOLD + "Username: " + ConsoleColors.WHITE + showFrom.getUsername());
        // age follow shode bashi mitoni last seen ro  bbni
        if (showFrom.getWhoCanSeeLastSeen().equals("everybody") || (showFrom.getWhoCanSeeLastSeen().equals("followers") && showTo.getFollowersId().contains(showFrom.getId()))) {
            System.out.println(ConsoleColors.CYAN_BOLD + "lastseen: " + ConsoleColors.WHITE + showFrom.getLastSeen());
        } else {
            System.out.println(ConsoleColors.CYAN_BOLD + "lastseen: " + ConsoleColors.WHITE + "recently...");
        }
        if (showTo.getFollowingsId().contains(showFrom.getId())) {
            System.out.println(ConsoleColors.WHITE + "You have followed this user!");
            System.out.println(ConsoleColors.RED + "Do you want to unfollow? (y/n)");
            String respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.yn(respond);
            if (respond.equals("y")) {
                showTo.getFollowingsId().remove(showFrom.getId());
                showFrom.getFollowersId().remove(showTo.getId());
                if (!showFrom.getMutedId().contains(showTo.getId())) {
                    showFrom.getMyNotifs().add(new Notification("User " + showTo.getUsername() + " has unfollowed you :("));
                }
                logger.info(showTo.getUsername() + " has unfollowed " + showFrom.getUsername());
                System.out.println(ConsoleColors.RESET + "You have unfollowed this user ;(");
            }
        } else {
            System.out.println(ConsoleColors.WHITE + "You have not followed this user!");
            if (showFrom.getBlackListId().contains(showTo.getId())) {
                System.out.println(ConsoleColors.RED_UNDERLINED + "You can't follow this user >:(");
            } else {
                System.out.println(ConsoleColors.GREEN_BOLD + "Do you want to follow? (y/n)");
                String respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    if (showFrom.isAccountPublic()) {
                        showTo.getFollowingsId().add(showFrom.getId());
                        showFrom.getFollowersId().add(showTo.getId());
                        if (!showFrom.getMutedId().contains(showTo.getId())) {
                            showFrom.getMyNotifs().add(new Notification(ConsoleColors.BLUE + "User " + showTo.getUsername() + " has followed you :)"));
                        }
                        logger.info(showTo.getUsername() + " has followed " + showFrom.getUsername());
                        System.out.println(ConsoleColors.PURPLE + "You have followed this user :D");
                    } else {
                        showFrom.getRequestersId().add(showTo.getId());
                        if (!showFrom.getMutedId().contains(showTo.getId())) {
                            showFrom.getMyNotifs().add(new Notification(ConsoleColors.GREEN_BOLD + "User " + showTo.getUsername() + " has requested to follow you..."));
                        }
                        System.out.println(ConsoleColors.RESET + "You have to wait until his/her response...");
                    }
                }
            }
        }
        if (showTo.getFollowingsId().contains(showFrom.getId()) && !showTo.getBlackListId().contains(showFrom.getId()) && !showFrom.getBlackListId().contains(showTo.getId())) {
            System.out.println(ConsoleColors.YELLOW_BOLD + "Do you wanna text this user? (y/n)");
            String respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.yn(respond);
            while (respond.equals("y")) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "Go on then print the text: ");
                String massage = MyScanner.GetScanner1().nextLine();
                SendingMassage.sendMassage1(showTo, showFrom, massage);
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you wanna continue? (y/n)");
                respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
            }
        }
        if (showTo.getBlackListId().contains(showFrom.getId())) {
            System.out.println(ConsoleColors.GREEN_BOLD + "Do you want to unblock this user? (y/n)");
            String respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.yn(respond);
            if (respond.equals("y")) {
                showTo.getBlackListId().remove(new Integer(showFrom.getId()));
                System.out.println(ConsoleColors.GREEN + "User is unblocked!");
            }
        } else {
            System.out.println(ConsoleColors.RED_BOLD + "Do you want to block this user? (y/n)");
            String respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.yn(respond);
            if (respond.equals("y")) {
                showTo.getBlackListId().add(showFrom.getId());
                System.out.println(ConsoleColors.RESET + "User is blocked!");
            }
        }
        System.out.println(ConsoleColors.YELLOW_BOLD + "Do you want to report the user? (y/n)");
        String respond = MyScanner.GetScanner1().next();
        respond = CheckValidity.yn(respond);
        if (respond.equals("y")) {
            showFrom.Report();
            logger.info(showTo.getUsername() + " has reported " + showFrom.getUsername());
            System.out.println(ConsoleColors.RESET + "Thanks for your feedback; we will take care!");
        }
        if(showTo.getMutedId().contains(showFrom.getId())){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Do you want to unmute the user? (y/n)");
            respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.yn(respond);
            if (respond.equals("y")) {
                showTo.getMutedId().remove(showFrom.getId());
                logger.info(showTo.getUsername() + " has unmuted " + showFrom.getUsername());
                System.out.println(ConsoleColors.WHITE + "User is unmuted");
            }
        }
        else {
            System.out.println(ConsoleColors.YELLOW_BOLD + "Do you want to mute the user? (y/n)");
            respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.yn(respond);
            if (respond.equals("y")) {
                showTo.getMutedId().add(showFrom.getId());
                logger.info(showTo.getUsername() + " has muted " + showFrom.getUsername());
                System.out.println(ConsoleColors.WHITE + "User is muted");
            }
        }
        SaveNLoad.save();

    }

}