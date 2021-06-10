import java.time.LocalDate;
import java.util.LinkedList;

public class Your_Account {

    public static void selectOption(User user){

        System.out.println(ConsoleColors.GREEN_BOLD + "Here is your personal page, choose what you want to do: ");
        System.out.println(ConsoleColors.YELLOW_BOLD + "1. Add tweet");
        System.out.println("2. Show my tweets");
        System.out.println("3. Edit my account");
        System.out.println("4. Show lists of followers/followings/blocked people");
        System.out.println("5. Info");
        System.out.println("6. Notifications");
        System.out.println("7. Create/Delete a sorting");
        System.out.println("8. Show my sortings");
        System.out.println("9. Show follow requests");
        System.out.println("10. Get back to main menu");
        String respond = MyScanner.GetScanner1().next();
        respond = CheckValidity.numeric(10, respond);
        switch (respond){
            case "1":
                addTweet(user);
                break;
            case "2":
                showTweets(user);
                break;
            case "3":
                edit(user);
                break;
            case "4":
                showLists(user);
                break;
            case "5":
                info(user);
                break;
            case "6":
                showNotifications(user);
                break;
            case "7":
                createSorting(user);
                break;
            case "8":
                showSortings(user);
                break;
            case "9":
                followRequests(user);
                break;
            case "10":
                // =))
                break;
        }

    }

    private static void addTweet(User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "Please write your tweet down here: ");
        String tweet = MyScanner.GetScanner1().nextLine();
        Tweet_Comment thisTweet = new Tweet_Comment(tweet, user.getId());
        user.getMyTweetsId().add(thisTweet.getID());
        System.out.println(ConsoleColors.GREEN + "Tweet has created successfully!" + ConsoleColors.RESET);
        SaveNLoad.save();
        selectOption(user);

    }

    private static void showTweets(User user){

        LinkedList<Tweet_Comment> tweets = user.getMyTweets();
        if(tweets.size() == 0){
            System.out.println(ConsoleColors.RED_UNDERLINED + "You don't have any tweets!");
        }
        else{
            System.out.println(ConsoleColors.YELLOW_BOLD + "Here are all your tweets: ");
            for (Tweet_Comment tweet: tweets) {
                System.out.println(ConsoleColors.RESET + tweet.getText());
            }
            System.out.println(ConsoleColors.YELLOW_BOLD + "That's it!");
        }
        selectOption(user);

    }

    private static void edit(User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "What do you want to change about your account?");
        System.out.println("1. Name");
        System.out.println("2. Family name");
        System.out.println("3. Username");
        System.out.println("4. Birthday");
        System.out.println("5. Email");
        System.out.println("6. Telephone number");
        System.out.println("7. Bio");
        System.out.println("8. Get back to my personal page");
        String response = MyScanner.GetScanner1().next();
        CheckValidity.numeric(8, response);
        switch (response){
            case "1":

                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "This is your current name: " + ConsoleColors.WHITE_BOLD_BRIGHT + user.getFirstName());
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to change it? (y/n)");
                String respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your new name: ");
                    String name = MyScanner.GetScanner1().next();
                    name = CheckValidity.name(name);
                    user.setFirstName(name);
                    System.out.println(ConsoleColors.GREEN_BOLD + "Name has changed successfully!");
                    SaveNLoad.save();
                }
                selectOption(user);

                break;
            case "2":

                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "This is your current lastname: " + ConsoleColors.WHITE_BOLD_BRIGHT + user.getLastName());
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to change it? (y/n)");
                respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "This is your current lastname: " + ConsoleColors.WHITE_BOLD_BRIGHT + user.getLastName());
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your new last name: ");
                    String lastname = MyScanner.GetScanner1().next();
                    lastname = CheckValidity.name(lastname);
                    user.setLastName(lastname);
                    System.out.println(ConsoleColors.GREEN_BOLD + "Lastname has changed successfully!");
                    SaveNLoad.save();
                }
                selectOption(user);

                break;
            case "3":

                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "This is your current username: " + ConsoleColors.WHITE_BOLD_BRIGHT + user.getUsername());
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to change it? (y/n)");
                respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your new username: ");
                    String username = MyScanner.GetScanner1().nextLine();
                    if(username.equals(user.getUsername())){
                        System.out.println(ConsoleColors.BLUE_BOLD + "This is your current username :|");
                    }
                    else {
                        username = CheckValidity.newUsername(username);
                        user.setUsername(username);
                        System.out.println(ConsoleColors.GREEN_BOLD + "Username has changed successfully!");
                        SaveNLoad.saveUsers();
                    }
                }
                selectOption(user);

                break;
            case "4":

                if(user.getBirthday() != null){
                    System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "This is your current birthday: " + ConsoleColors.WHITE_BOLD_BRIGHT + user.getBirthday());
                }
                else{
                    System.out.println(ConsoleColors.BLUE_BOLD + "You have not added your birthday :(");
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to change it? (y/n)");
                respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your birth day: ");
                    int day = MyScanner.GetScanner1().nextInt();
                    // checking validity
                    while (day <= 0 || day > 30 || !CheckValidity.isNumberValid(Integer.toString(day))) {
                        System.out.println(ConsoleColors.RED_UNDERLINED + "invalid input, try again: ");
                        day = MyScanner.GetScanner1().nextInt();
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your birth month(in number): ");
                    int month = MyScanner.GetScanner1().nextInt();
                    // checking validity
                    while (month <= 0 || month > 12 || !CheckValidity.isNumberValid(Integer.toString(month))) {
                        System.out.println(ConsoleColors.RED_UNDERLINED + "invalid input, try again: ");
                        month = MyScanner.GetScanner1().nextInt();
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your birth year: ");
                    int year = MyScanner.GetScanner1().nextInt();
                    int currentYear = LocalDate.now().getYear();
                    // checking validity
                    while (year <= 0 || year > currentYear || !CheckValidity.isNumberValid(Integer.toString(month))) {
                        System.out.println(ConsoleColors.RED_UNDERLINED + "invalid input, try again: ");
                        year = MyScanner.GetScanner1().nextInt();
                    }
                    user.setBirthday(LocalDate.of(year, month, day));
                    System.out.println(ConsoleColors.GREEN_BOLD + "Birthday has changed successfully!");
                    SaveNLoad.save();
                }
                selectOption(user);

                break;
            case "5":

                if(user.getEmail() != null) {
                    System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "This is your current Email: " + ConsoleColors.WHITE_BOLD_BRIGHT + user.getEmail());
                }
                else {
                    System.out.println(ConsoleColors.BLUE_BOLD + "You have not added your Email :(");
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to change it? (y/n)");
                respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "This is your current Email: " + ConsoleColors.WHITE_BOLD_BRIGHT + user.getEmail());
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your new email address: ");
                    String email = MyScanner.GetScanner1().next();
                    email = CheckValidity.email(email);
                    user.setEmail(email);
                    System.out.println(ConsoleColors.GREEN_BOLD + "Email has changed successfully");
                    SaveNLoad.save();
                }
                selectOption(user);

                break;
            case "6":

                if(user.getPhoneNumber() != null) {
                    System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "This is your current phone number: " + ConsoleColors.WHITE_BOLD_BRIGHT + user.getPhoneNumber());
                }
                else {
                    System.out.println(ConsoleColors.BLUE_BOLD + "You have not added your phone number :(");
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to change it? (y/n)");
                respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your phone number");
                    String phonenumber = MyScanner.GetScanner1().next();
                    phonenumber = CheckValidity.phoneNumber(phonenumber);
                    user.setPhoneNumber(phonenumber);
                    System.out.println(ConsoleColors.GREEN_BOLD + "Phone number has changed successfully!");
                    SaveNLoad.save();
                }
                selectOption(user);

                break;
            case "7":

                if(user.getBio() != null) {
                    System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "This is your current bio: " + ConsoleColors.WHITE_BOLD_BRIGHT + user.getBio());
                }
                else{
                    System.out.println(ConsoleColors.BLUE_BOLD + "You have not added your bio :(");
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to change it? (y/n)");
                respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your bio: ");
                    String bio = MyScanner.GetScanner1().nextLine();
                    user.setBio(bio);
                    System.out.println(ConsoleColors.GREEN_BOLD + "Bio has changed successfully!");
                    SaveNLoad.save();
                }
                selectOption(user);

                break;
            case "8":

                selectOption(user);

                break;
        }

    }

    private static void showLists(User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "Which list do you wanna see?");
        System.out.println("1. Followers");
        System.out.println("2. Followings");
        System.out.println("3. Blacklist");
        System.out.println("4. Muted");
        System.out.println("5. None, get back to my personal page");
        String respond = MyScanner.GetScanner1().next();
        respond = CheckValidity.numeric(5, respond);
        switch (respond) {
            case "1":

                if(user.getFollowersId().size() == 0){
                    System.out.println(ConsoleColors.WHITE_BOLD + "You don't have any followers you fucking poor little dumb ass XD");
                }
                else{
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Here are your followers: ");
                    for (User follower: user.getFollowers()) {
                        System.out.println(ConsoleColors.WHITE + follower.getUsername());
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + "That's it, Do you want to see the info of any of them? (y/n)");
                    String respond1 = MyScanner.GetScanner1().next();
                    respond1 = CheckValidity.yn(respond1);
                    if(respond1.equals("y")){
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the username then: ");
                        String selectedUser = MyScanner.GetScanner1().next();
                        selectedUser = CheckValidity.username(selectedUser);
                        // checking validity & finding
                        User follower = null;
                        for (int i = 0; i<User.getActiveUsers().size(); i++) {
                            if(User.getActiveUsers().get(i).getUsername().equals(selectedUser)){ // user found
                                follower = User.getActiveUsers().get(i);
                                break;
                            }
                        }
                        while(follower == null){ // invalid input
                            System.out.println(ConsoleColors.RED_UNDERLINED + "User not found, do you want to continue? (y/n)");
                            String respond2 = MyScanner.GetScanner1().next();
                            if(respond2.equals("n")){
                                selectOption(user);
                            }
                            System.out.println(ConsoleColors.RED_BOLD + "try again: ");
                            selectedUser = MyScanner.GetScanner1().next();
                            selectedUser = CheckValidity.username(selectedUser);
                            for (int i = 0; i<User.getActiveUsers().size(); i++) {
                                if (User.getActiveUsers().get(i).getUsername().equals(selectedUser)) { // user found
                                    follower = User.getActiveUsers().get(i);
                                    break;
                                }
                            }
                        }
                        Watch_Someones_Page.show(user, follower);
                    }
                }
                showLists(user);

                break;
            case "2":

                if(user.getFollowingsId().size() == 0){
                    System.out.println(ConsoleColors.WHITE_BOLD + "You have not followed anyone yet you so called (shakhe majazi) :|");
                }
                else{
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Here are the people that you have followed: ");
                    for (User following: user.getFollowings()) {
                        System.out.println(ConsoleColors.WHITE + following.getUsername());
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + "That's it, Do you want to see the info of any of them? (y/n)");
                    String respond1 = MyScanner.GetScanner1().next();
                    respond1 = CheckValidity.yn(respond1);
                    if(respond1.equals("y")){
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the username then: ");
                        String selectedUser = MyScanner.GetScanner1().next();
                        selectedUser = CheckValidity.username(selectedUser);
                        // checking validity & finding
                        User following = null;
                        for (int i = 0; i<User.getActiveUsers().size(); i++) {
                            if(User.getActiveUsers().get(i).getUsername().equals(selectedUser)){ // user found
                                following = User.getActiveUsers().get(i);
                                break;
                            }
                        }
                        while(following == null){ // invalid input
                            System.out.println(ConsoleColors.RED_UNDERLINED + "User not found, do you want to continue? (y/n)");
                            String respond2 = MyScanner.GetScanner1().next();
                            if(respond2.equals("n")){
                                selectOption(user);
                            }
                            System.out.println(ConsoleColors.RED_BOLD + "try again: ");
                            selectedUser = MyScanner.GetScanner1().next();
                            selectedUser = CheckValidity.username(selectedUser);
                            for (int i = 0; i<User.getActiveUsers().size(); i++) {
                                if (User.getActiveUsers().get(i).getUsername().equals(selectedUser)) { // user found
                                    following = User.getActiveUsers().get(i);
                                    break;
                                }
                            }
                        }
                        Watch_Someones_Page.show(user, following);
                    }
                }
                showLists(user);

                break;
            case "3":

                if(user.getBlackListId().size() == 0){
                    System.out.println(ConsoleColors.WHITE_BOLD + "You have not blocked anyone; GOD BLESS YOU!");
                }
                else{
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Here are the people that you have blocked: ");
                    for (User blocked: user.getBlackList()) {
                        System.out.println(ConsoleColors.WHITE + blocked.getUsername());
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + "That's it, Do you want to see the info of any of them? (y/n)");
                    String respond1 = MyScanner.GetScanner1().next();
                    respond1 = CheckValidity.yn(respond1);
                    if(respond1.equals("y")){
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the username then: ");
                        String selectedUser = MyScanner.GetScanner1().next();
                        selectedUser = CheckValidity.username(selectedUser);
                        // checking validity & finding
                        User blocked = null;
                        for (int i = 0; i<User.getActiveUsers().size(); i++) {
                            if(User.getActiveUsers().get(i).getUsername().equals(selectedUser)){ // user found
                                blocked = User.getActiveUsers().get(i);
                                break;
                            }
                        }
                        while(blocked == null){ // invalid input
                            System.out.println(ConsoleColors.RED_UNDERLINED + "User not found, do you want to continue? (y/n)");
                            String respond2 = MyScanner.GetScanner1().next();
                            if(respond2.equals("n")){
                                selectOption(user);
                            }
                            System.out.println(ConsoleColors.RED_BOLD + "try again: ");
                            selectedUser = MyScanner.GetScanner1().next();
                            selectedUser = CheckValidity.username(selectedUser);
                            for (int i = 0; i<User.getActiveUsers().size(); i++) {
                                if (User.getActiveUsers().get(i).getUsername().equals(selectedUser)) { // user found
                                    blocked = User.getActiveUsers().get(i);
                                    break;
                                }
                            }
                        }
                        Watch_Someones_Page.show(user, blocked);
                    }
                }
                showLists(user);

                break;
            case "4":

                if(user.getMutedId().size() == 0){
                    System.out.println(ConsoleColors.WHITE_BOLD + "You have not blocked anyone; GOD BLESS YOU!");
                }
                else{
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Here are the people that you have blocked: ");
                    for (User muted: user.getMuted()) {
                        System.out.println(ConsoleColors.WHITE + muted.getUsername());
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + "That's it, Do you want to see the info of any of them? (y/n)");
                    String respond1 = MyScanner.GetScanner1().next();
                    respond1 = CheckValidity.yn(respond1);
                    if(respond1.equals("y")){
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the username then: ");
                        String selectedUser = MyScanner.GetScanner1().next();
                        selectedUser = CheckValidity.username(selectedUser);
                        // checking validity & finding
                        User muted = null;
                        for (int i = 0; i<User.getActiveUsers().size(); i++) {
                            if(User.getActiveUsers().get(i).getUsername().equals(selectedUser)){ // user found
                                muted = User.getActiveUsers().get(i);
                                break;
                            }
                        }
                        while(muted == null){ // invalid input
                            System.out.println(ConsoleColors.RED_UNDERLINED + "User not found, do you want to continue? (y/n)");
                            String respond2 = MyScanner.GetScanner1().next();
                            if(respond2.equals("n")){
                                selectOption(user);
                            }
                            System.out.println(ConsoleColors.RED_BOLD + "try again: ");
                            selectedUser = MyScanner.GetScanner1().next();
                            selectedUser = CheckValidity.username(selectedUser);
                            for (int i = 0; i<User.getActiveUsers().size(); i++) {
                                if (User.getActiveUsers().get(i).getUsername().equals(selectedUser)) { // user found
                                    muted = User.getActiveUsers().get(i);
                                    break;
                                }
                            }
                        }
                        Watch_Someones_Page.show(user, muted);
                    }
                }
                showLists(user);

                break;
            case "5":

                selectOption(user);

                break;
        }

    }

    private static void info(User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "Here is your info: ");
        System.out.println(ConsoleColors.CYAN_BOLD + "Firstname: " + ConsoleColors.WHITE + user.getFirstName());
        System.out.println(ConsoleColors.CYAN_BOLD + "Lastname: " + ConsoleColors.WHITE + user.getLastName());
        System.out.println(ConsoleColors.CYAN_BOLD + "Username: " + ConsoleColors.WHITE + user.getUsername());
        System.out.println(ConsoleColors.CYAN_BOLD + "Email address: " + ConsoleColors.WHITE + user.getEmail());
        if(user.getBirthday() != null){
            System.out.println(ConsoleColors.CYAN_BOLD + "Birthday: " + ConsoleColors.WHITE + user.getBirthday());
        }
        if(user.getPhoneNumber() != null){
            System.out.println(ConsoleColors.CYAN_BOLD + "Phone number: " + ConsoleColors.WHITE + user.getPhoneNumber());
        }
        if(user.getBio() != null){
            System.out.println(ConsoleColors.CYAN_BOLD + "Bio: " + ConsoleColors.WHITE + user.getBio());
        }
        System.out.println(ConsoleColors.YELLOW_BOLD + "That's it!");
        selectOption(user);

    }

    private static void showNotifications(User user){

        if(user.getMyNotifs().size() == 0){
            System.out.println(ConsoleColors.YELLOW_BOLD + "You don't have any notifications!");
        }
        else{
            System.out.println(ConsoleColors.YELLOW_BOLD + "Here are your notifications: ");
            for (Notification notification: user.getMyNotifs()) {
                System.out.println(ConsoleColors.PURPLE + notification.getNotif() + " " + notification.getDateTime());
            }
            user.getMyNotifs().clear();
            System.out.println(ConsoleColors.YELLOW_BOLD + "That's it!");
            SaveNLoad.save();
        }
        selectOption(user);

    }

    private static void createSorting(User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "Here you can create a group of people from your followings for yourself!");
        if(user.getFollowingsId().size() == 0){
            System.out.println(ConsoleColors.RED_BOLD + "You have not followed anyone yet dude!");
            selectOption(user);
        }
        else if(user.getFollowingsId().size() == 1){
            System.out.println(ConsoleColors.RED_BOLD + "You can't create a group consisting of only one member dude!");
            selectOption(user);
        }
        else{
            System.out.println(ConsoleColors.YELLOW_BOLD + "Here are your followings: ");
            for (User following: user.getFollowings()){
                System.out.println(ConsoleColors.WHITE_BOLD + following.getUsername());
            }
            System.out.println(ConsoleColors.YELLOW_BOLD + "What do you wanna do?");
            System.out.println("1. create a sorting");
            System.out.println("2. remove a sorting");
            System.out.println("3. add/remove a user from a sorting");
            String respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.numeric(3, respond);
            if(respond.equals("1")) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "Now type down the group name, after that enter the name of the users that you want to add" + System.lineSeparator() + "to this list. when you are done type /done/. remember that the group size must be at least two!");
                String name = MyScanner.GetScanner1().nextLine();
                // checking validity
                while(user.getMySortings().keySet().contains(name)){
                    System.out.println(ConsoleColors.RED_UNDERLINED + "This name already exists, try again: ");
                    name = MyScanner.GetScanner1().nextLine();
                }
                LinkedList<Integer> membersid = new LinkedList<>();
                String username = MyScanner.GetScanner1().next();
                while (!username.equals("done")) {
                    // checking validity
                    int counter = 0;
                    for (User user1 : user.getFollowings()) {
                        if (user1.getUsername().equals(username)) {
                            membersid.add(user1.getId());
                            counter = 0;
                            break;
                        } else {
                            counter++;
                        }
                    }
                    if (counter == user.getFollowingsId().size()) {
                        System.out.println(ConsoleColors.RED_UNDERLINED + "User is not there! if you want to stop it type /done/");
                    }
                    else {
                        System.out.println(ConsoleColors.YELLOW_BOLD + "User added! if you want to stop it type /done/");
                    }
                    username = MyScanner.GetScanner1().next();
                }
                if (membersid.size() <= 1) {
                    System.out.println(ConsoleColors.RED_UNDERLINED + "Group is not valid!");
                } else {
                    user.getMySortings().put(name, membersid);
                    System.out.println(ConsoleColors.GREEN_BOLD + "Task done successfully!");
                }
            }
            else if(respond.equals("2")){
                System.out.println(ConsoleColors.YELLOW_BOLD + "Enter the name of the sorting: ");
                String name = MyScanner.GetScanner1().nextLine();
                // checking validity
                while(!user.getMySortings().keySet().contains(name)){
                    System.out.println(ConsoleColors.RED_UNDERLINED + "Sorting not found, try again: ");
                    name = MyScanner.GetScanner1().nextLine();
                }
                user.getMySortings().remove(name, user.getMySortings().get(name));
                System.out.println(ConsoleColors.GREEN_BOLD + "Task done successfully!");
            }
            else{
                if(user.getMySortings().size() == 0){
                    System.out.println(ConsoleColors.RED_BOLD + "You don't have any sorting");
                }
                else{
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Here are all your sortings: ");
                    for (String name : user.getMySortings().keySet()) {
                        System.out.println(name);
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Which sorting do you like to change?");
                    String sorting = MyScanner.GetScanner1().next();
                    // checking validity
                    while (!user.getMySortings().keySet().contains(sorting)) {
                        System.out.println(ConsoleColors.RED_UNDERLINED + "There is no sorting in this name, try again: ");
                        sorting = MyScanner.GetScanner1().nextLine();
                    }
                    LinkedList<User> users = new LinkedList<>();
                    for (Integer user1id : user.getMySortings().get(sorting)) {
                        for (User user1 : User.getActiveUsers()) {
                            if (user1.getId() == user1id) {
                                users.add(user1);
                            }
                        }
                    }
                    for (User user1 : users) {
                        System.out.println(ConsoleColors.YELLOW_BOLD + user1.getUsername());
                    }
                    System.out.println("1. add user");
                    System.out.println("2. delete user");
                    System.out.println("3. none");
                    String ans = MyScanner.GetScanner1().next();
                    ans = CheckValidity.numeric(3, ans);
                    if(ans.equals("1")){
                        for (User user1 : user.getFollowings()) {
                            System.out.println(user1.getUsername());
                        }
                        System.out.println("Which user do you like to add?");
                        String username = MyScanner.GetScanner1().next();
                        User toBeAdded = null;
                        int counter = 0;
                        while(toBeAdded == null) {
                            for (User user1 : user.getFollowings()) {
                                if (user1.getUsername().equals(username)) {
                                    toBeAdded = user1;
                                    counter = 0;
                                    break;
                                } else {
                                    counter++;
                                }
                            }
                            if (counter == user.getFollowingsId().size()) {
                                System.out.println(ConsoleColors.RED_UNDERLINED + "User is not there! try again: ");
                                username = MyScanner.GetScanner1().next();
                            } else {
                                System.out.println(ConsoleColors.YELLOW_BOLD + "User added!");
                            }
                        }
                        user.getMySortings().get(sorting).add(toBeAdded.getId());
                        SaveNLoad.saveUsers();
                    }
                    else if(ans.equals("2")){
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the index of user: ");
                        String index = MyScanner.GetScanner1().next();
                        index = CheckValidity.number(index);
                        int indx = Integer.parseInt(index);
                        while (indx > user.getMySortings().get(sorting).size() || indx < 1){
                            System.out.println(ConsoleColors.RED_BOLD + "Wrong input, try again: ");
                            index = MyScanner.GetScanner1().next();
                            index = CheckValidity.number(index);
                            indx = Integer.parseInt(index);
                        }
                        user.getMySortings().get(sorting).remove(indx - 1);
                        System.out.println(ConsoleColors.GREEN_BOLD + "Done");
                    }
                }
            }
            SaveNLoad.saveUsers();
            selectOption(user);
        }

    }

    private static void showSortings(User user){

        if(user.getMySortings().size() == 0){
            System.out.println(ConsoleColors.RED_BOLD + "You don't have any sortings yet!");
        }
        else{
            for (String name : user.getMySortings().keySet()) {
                System.out.println(ConsoleColors.CYAN_BOLD + name + ": " + ConsoleColors.WHITE_BOLD + user.getMySortings().get(name).size() + "users");
            }
            System.out.println(ConsoleColors.YELLOW_BOLD + "That's it. Do you want to change any of them? (y/n)");
            String respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.yn(respond);
            if(respond.equals("y")){
                System.out.println(ConsoleColors.YELLOW_BOLD + "Enter the list's name: ");
                String name = MyScanner.GetScanner1().nextLine();
                // checking validity
                while(!user.getMySortings().keySet().contains(name)){
                    System.out.println(ConsoleColors.RED_UNDERLINED + "Sorting not found, try again: ");
                    name = MyScanner.GetScanner1().nextLine();
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "This is the sorting: ");
                System.out.println(ConsoleColors.WHITE_BOLD + name + ": ");
                for (Integer user1id : user.getMySortings().get(name)) {
                    for (User user1 : User.getActiveUsers()) {
                        if(user1.getId() == user1id){
                            System.out.println(ConsoleColors.CYAN_BOLD + user1.getUsername() + " ");
                            break;
                        }
                    }
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "What do you wanna do?");
                System.out.println("1. remove a user");
                System.out.println("2. add a user");
                String ans = MyScanner.GetScanner1().next();
                ans = CheckValidity.numeric(2, ans);
                if(ans.equals("1")){
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the username: ");
                    String username = MyScanner.GetScanner1().nextLine();
                    // checking validity
                    User thisUser = null;
                    first:while (thisUser == null) {
                        for (Integer user1id : user.getMySortings().get(name)) {
                            for (User user1 : User.getActiveUsers()) {
                                if(user1.getId() == user1id && user1.getUsername().equals(username) && !user1.getUsername().equals(username)) {
                                    thisUser = user1;
                                    break first;
                                }
                            }
                        }
                        System.out.println(ConsoleColors.RED_UNDERLINED + "User not found try again: ");
                        username = MyScanner.GetScanner1().nextLine();
                    }
                    user.getMySortings().get(name).remove(new Integer(thisUser.getId()));
                    System.out.println(ConsoleColors.GREEN_BOLD + "Task done successfully!");
                }
                else{
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the username: ");
                    String username = MyScanner.GetScanner1().nextLine();
                    // checking validity
                    User thisUser = null;
                    first:while (thisUser == null) {
                        for (User user1 : user.getFollowings()) {
                            if(user1.getUsername().equals(username)) {
                                thisUser = user1;
                                break first;
                            }
                        }
                        System.out.println(ConsoleColors.RED_UNDERLINED + "User not found try again: ");
                        username = MyScanner.GetScanner1().nextLine();
                    }
                    if(!user.getMySortings().get(name).contains(thisUser.getId())) {
                        user.getMySortings().get(name).add(thisUser.getId());
                    }
                    System.out.println(ConsoleColors.GREEN_BOLD + "Task done successfully!");
                }
                SaveNLoad.save();
            }
        }
        selectOption(user);

    }

    private static void followRequests(User user){

        if(user.getRequesters().size() == 0){
            System.out.println(ConsoleColors.YELLOW_BOLD + "You don't have any follow requests you fuckface XD");
        }
        else {
            System.out.println(ConsoleColors.YELLOW_BOLD + "Here are your follow requests: ");
            for (User requester : user.getRequesters()) {
                System.out.println(ConsoleColors.WHITE_BOLD + requester.getUsername());
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you accept? (y/n)");
                String respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if(respond.equals("y")){
                    user.getFollowersId().add(requester.getId());
                    user.getRequestersId().remove(new Integer(requester.getId()));
                    requester.getMyNotifs().add(new Notification("User " + user.getUsername() + " has accepted your follow request!"));
                    requester.getFollowingsId().add(user.getId());
                }
                else{
                    user.getRequestersId().remove(new Integer(requester.getId()));
                    requester.getMyNotifs().add(new Notification("User " + user.getUsername() + " has rejected your follow request XD"));
                }
            }
            SaveNLoad.save();
        }
        selectOption(user);

    }

}