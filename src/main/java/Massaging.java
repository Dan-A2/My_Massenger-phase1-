public class Massaging {

    public static void showOptions(User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "This is your massaging page, what do you want to do?");
        System.out.println("1. Show saved tweets");
        System.out.println("2. Show saved massages");
        System.out.println("3. Show my saved massages");
        System.out.println("4. Show my chats");
        System.out.println("5. Send massage to a sorting");
        System.out.println("6. None, get back");
        String respond = MyScanner.GetScanner1().next();
        respond = CheckValidity.numeric(6, respond);
        if(respond.equals("1")){
            if(user.getSavedTweetsId().size() == 0){
                System.out.println(ConsoleColors.RED_BOLD + "You have not saved any saved tweets!");
            }
            else {
                System.out.println(ConsoleColors.CYAN_BOLD + "Here are your saved tweets: ");
                for (Tweet_Comment tweet : user.getSavedTweets()) {
                    System.out.println(ConsoleColors.CYAN_BOLD + tweet.getSender().getUsername() + ": " + System.lineSeparator() + ConsoleColors.WHITE_BOLD + tweet.getText());
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "That's it!");
            }
            showOptions(user);
        }
        else if(respond.equals("2")){
            if(user.getSavedMassages().size() == 0){
                System.out.println(ConsoleColors.RED_BOLD + "You have not saved any saved massages!");
            }
            else{
                System.out.println(ConsoleColors.CYAN_BOLD + "Here are your saved massages: ");
                for (Massage massage : user.getSavedMassages()) {
                    if (massage.getSender() != null) {
                        System.out.println(ConsoleColors.CYAN_BOLD + massage.getSender().getUsername() + ": " + "/n" + ConsoleColors.WHITE_BOLD + massage.getMassage());
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD + "This action can't be done because the user is inactive!");
                    }
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "That's it!");
            }
            showOptions(user);
        }
        else if(respond.equals("3")){
            if(user.getSavedTextsOfMe().size() == 0){
                System.out.println(ConsoleColors.RED_BOLD + "You have not saved any saved massages!");
            }
            else{
                System.out.println(ConsoleColors.CYAN_BOLD + "Here are your saved massages: ");
                for (Massage massage : user.getSavedTextsOfMe()) {
                    System.out.println(ConsoleColors.WHITE_BOLD + massage.getMassage() + " " +ConsoleColors.CYAN_BOLD + massage.getDateTime());
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "That's it!");
            }
            System.out.println(ConsoleColors.YELLOW_BOLD + "Do you wanna add any massage? (y/n)");
            respond = MyScanner.GetScanner1().next();
            respond = CheckValidity.yn(respond);
            if(respond.equals("y")){
                System.out.println(ConsoleColors.YELLOW_BOLD + "Go on then print the text: ");
                String txt = MyScanner.GetScanner1().nextLine();
                Massage save = new Massage(txt, user.getId(), user.getId());
                user.getSavedTextsOfMeId().add(save.getID());
                System.out.println(ConsoleColors.GREEN_BOLD + "Text saved successfully!");
            }
            if(user.getSavedTextsOfMe().size() > 0) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "Do you wanna delete any massage? (y/n)");
                respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if (respond.equals("y")) {
                    System.out.println(ConsoleColors.YELLOW_BOLD + "type the index of the massage: ");
                    String index = MyScanner.GetScanner1().nextLine();
                    index = CheckValidity.number(index);
                    int idx = Integer.parseInt(index);
                    while (idx < 1 || idx > user.getSavedTextsOfMe().size()) {
                        System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid input try again: ");
                        index = MyScanner.GetScanner1().nextLine();
                        index = CheckValidity.number(index);
                        idx = Integer.parseInt(index);
                    }
                    user.getSavedTextsOfMeId().remove(idx - 1);
                    System.out.println(ConsoleColors.GREEN_BOLD + "Text deleted successfully!");
                }
            }
            SaveNLoad.save();
            showOptions(user);
        }
        else if(respond.equals("4")){
            if(user.getMyChats().size() == 0){
                System.out.println(ConsoleColors.RED_BOLD + "You don't have any chats!");
            }
            else{
                int counter = 1;
                if(user.getUnseenChatsId().size() != 0){
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Here are your unseen chats: ");
                }
                int counter1 = 0;
                for (Chat chat : user.getUnseenChats()) {
                    if(chat.getUser1() == null || chat.getUser2() == null){
                        counter1++;
                        System.out.println(ConsoleColors.RED_BOLD + "This chat is not accessible because one user is not active!");
                    }
                    else if(chat.getUser1().getUsername().equals(user.getUsername())){
                        System.out.println(ConsoleColors.WHITE_BOLD + counter + ". " + Chat.findTheOtherUser(chat, user).getUsername() + " " + chat.getUser1UnseenMassages() + " massages!");
                    }
                    else{
                        System.out.println(ConsoleColors.WHITE_BOLD + counter + ". " + Chat.findTheOtherUser(chat, user).getUsername() + " " + chat.getUser2UnseenMassages() + " massages!");
                    }
                    counter++;
                }
                if(user.getMyChatsId().size() > user.getUnseenChatsId().size()){
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Here are your chats except the Unseen ones: ");
                    for (Chat chat : user.getMyChats()) {
                        if(!user.getUnseenChatsId().contains(chat.getID())) {
                            if (chat.getUser1() == null || chat.getUser2() == null) {
                                counter1++;
                                System.out.println(ConsoleColors.RED_BOLD + "This chat is not accessible because one user is not active!");
                            } else if (!user.getUnseenChatsId().contains(chat.getID())) {
                                System.out.println(ConsoleColors.WHITE_BOLD + counter + ". " + Chat.findTheOtherUser(chat, user).getUsername());
                                counter++;
                            }
                        }
                    }
                }
                if(counter1 == user.getMyChats().size()){
                }
                else {
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Which user do you like to chat with? ");
                    String sadat = MyScanner.GetScanner1().nextLine();
                    sadat = CheckValidity.username(sadat);
                    try {
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Looking for user");
                        Thread.sleep(500);
                        System.out.print(".");
                        Thread.sleep(500);
                        System.out.print(".");
                        Thread.sleep(500);
                        System.out.print(".");
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                    Chat selectedChat = null;
                    // checking validity
                    for (Chat chat : user.getMyChats()) {
                        try {
                            if (Chat.findTheOtherUser(chat, user).getUsername().equals(sadat)) {
                                selectedChat = chat;
                                break;
                            }
                        } catch (Exception e){}
                    }
                    while (selectedChat == null) {
                        System.out.println(ConsoleColors.RED_UNDERLINED + "User not found, Wanna try again? (y/n)");
                        String inp = MyScanner.GetScanner1().next();
                        inp = CheckValidity.yn(inp);
                        if (inp.equals("n")) {
                            showOptions(user);
                        } else {
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the username then: ");
                            sadat = MyScanner.GetScanner1().nextLine();
                            sadat = CheckValidity.username(sadat);
                            try {
                                System.out.println(ConsoleColors.YELLOW_BOLD + "Looking for user");
                                Thread.sleep(500);
                                System.out.print(".");
                                Thread.sleep(500);
                                System.out.print(".");
                                Thread.sleep(500);
                                System.out.print(".");
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            for (Chat chat : user.getMyChats()) {
                                if (Chat.findTheOtherUser(chat, user).getUsername().equals(sadat)) {
                                    selectedChat = chat;
                                    break;
                                }
                            }
                        }
                    }
                    user.getUnseenChatsId().remove(new Integer(selectedChat.getID()));
                    for (Massage massage : selectedChat.getMassages()) {
                        if (massage.getSender() != null) {
                            System.out.println(ConsoleColors.WHITE_BOLD + massage.getMassage() + " -" + massage.getSender().getUsername() + "   " + massage.getDateTime());
                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "This action can't be done because the user is inactive!");
                        }
                    }
                    if (selectedChat.getUser1() != null && selectedChat.getUser1().getUsername().equals(user.getUsername())) {
                        selectedChat.setUser1UnseenMassages(0);
                    } else if (selectedChat.getUser2() != null && selectedChat.getUser2().getUsername().equals(user.getUsername())) {
                        selectedChat.setUser2UnseenMassages(0);
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Do you want to save any massage? (y/n)");
                    respond = MyScanner.GetScanner1().next();
                    respond = CheckValidity.yn(respond);
                    if (respond.equals("y")) {
                        int index = -1;
                        while (index == -1) {
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Which one do you like to save? (integer index)");
                            try {
                                index = MyScanner.GetScanner1().nextInt();
                                if (index < 0 || index > selectedChat.getMassages().size()) {
                                    index = -1;
                                    System.out.println(ConsoleColors.RED_UNDERLINED + "Index not available!");
                                }
                            } catch (Exception e) {
                                System.out.println(ConsoleColors.RED_UNDERLINED + "Input format is false :|");
                            }
                        }
                        user.getSavedMassagesId().add(selectedChat.getMassages().get(index-1).getID());
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Massage saved!");
                    }
                    SaveNLoad.save();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Do you want to enter his PV? (y/n)");
                    respond = MyScanner.GetScanner1().next();
                    respond = CheckValidity.yn(respond);
                    if (respond.equals("y")) {
                        Watch_Someones_Page.show(user, Chat.findTheOtherUser(selectedChat, user));
                    }
                }
            }
            showOptions(user);
        }
        else if(respond.equals("5")){
            if(user.getMySortings().size() == 0){
                System.out.println(ConsoleColors.RED_BOLD + "You don't have any sortings yet!");
            }
            else {
                System.out.println(ConsoleColors.YELLOW_BOLD + "Here are your sorts: ");
                for (String name : user.getMySortings().keySet()) {
                    System.out.println(ConsoleColors.CYAN_BOLD + name + ": " + ConsoleColors.WHITE_BOLD + user.getMySortings().get(name));
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "Which sorting do you want to send massage to?");
                String sorting = MyScanner.GetScanner1().nextLine();
                // checking validity
                while(!user.getMySortings().keySet().contains(sorting)){
                    System.out.println(ConsoleColors.RED_UNDERLINED + "There is no sorting in this name, try again: ");
                    sorting = MyScanner.GetScanner1().nextLine();
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the massage then: ");
                String massage = MyScanner.GetScanner1().nextLine();
                for (Integer user1id : user.getMySortings().get(sorting)) {
                    for (User user1 : User.getActiveUsers()) {
                        if(user1.getId() == user1id) {
                            SendingMassage.sendMassage1(user, user1, massage);
                            user1.getMyNotifs().add(new Notification("User " + user.getUsername() + " has sent a massage to you"));
                        }
                    }
                }
                System.out.println(ConsoleColors.GREEN_BOLD + "Done!");
                SaveNLoad.save();
            }
            showOptions(user);
        }
        else{
            MainMenu.showOptions(user);
        }

    }

}