import java.util.LinkedList;

public class Explorer {

    public static void what_to_do(User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "This is your explorer page, what do you want to do? ");
        System.out.println("1. Search");
        System.out.println("2. Random tweets");
        System.out.println("3. None, get back");
        String input = MyScanner.GetScanner1().next();
        input = CheckValidity.numeric(3, input);
        if(input.equals("1")){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Type down the username: ");
            String username = MyScanner.GetScanner1().nextLine();
            username = CheckValidity.username(username);
            User thisUser = null;
            // search for the user
            for (User user1 : User.getActiveUsers()) {
                if(user1.getUsername().equals(username) && !user1.getUsername().equals(user.getUsername())){
                    thisUser = user1;
                }
            }
            while (thisUser == null){
                System.out.println(ConsoleColors.RED_UNDERLINED + "User not found, wanna try again? (y/n)");
                String respond = MyScanner.GetScanner1().next();
                respond = CheckValidity.yn(respond);
                if(respond.equals("n")){
                    what_to_do(user);
                    break;
                }
                else{
                    System.out.println(ConsoleColors.YELLOW_BOLD + "... : ");
                    username = MyScanner.GetScanner1().nextLine();
                    username = CheckValidity.username(username);
                    // search for the user
                    for (User user1 : User.getActiveUsers()) {
                        if(!user.getUsername().equals(username) && user1.getUsername().equals(username)){
                            thisUser = user1;
                        }
                    }
                }
            }
            try {
                Watch_Someones_Page.show(user, thisUser);
            } catch (Exception e){}
            what_to_do(user);
        }
        else if(input.equals("2")){
            LinkedList<Tweet_Comment> tweetsToBeShown = new LinkedList<>();
            for (User user1: User.getActiveUsers()) {
                if(!user.getFollowingsId().contains(user1.getId()) && user1.isAccountPublic() && user1.isActive() && !user1.getUsername().equals(user.getUsername())){ // else, it is in timeline
                    for (Tweet_Comment tweet: user1.getMyTweets()) {
                        tweetsToBeShown.add(tweet);
                    }
                }
            }
            sortByLike(tweetsToBeShown);
            if(tweetsToBeShown.size() == 0){
                System.out.println(ConsoleColors.YELLOW_BOLD + "There are no tweets to show you here!");
            }
            else {
                generalShow(tweetsToBeShown, 0, user);
            }
            what_to_do(user);
        }
        else{
            // =))
        }

    }

    static void generalShow(LinkedList<Tweet_Comment> tweets, int index, User user){

        int bound = tweets.size();
        try {
            System.out.println(ConsoleColors.CYAN_BOLD + tweets.get(index).getSender().getUsername() + ": ");
            System.out.println(ConsoleColors.WHITE_BOLD + tweets.get(index).getText() + " likes: " + tweets.get(index).getLikes());
        }
        catch (Exception e){
            System.out.println(ConsoleColors.WHITE_BRIGHT + "this action can't be done!");
        }
        if(tweets.size() == 1){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Which one?");
            System.out.println("1. other options");
            System.out.println("2. none, get back");
            String response = MyScanner.GetScanner1().next();
            response = CheckValidity.numeric(2, response);
            if(response.equals("1")){
                otherOptions(tweets, index, user);
            }
            else{
                // =))
            }
        }
        else if(index > 0 && index < bound-1){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Which one?");
            System.out.println("1. previous");
            System.out.println("2. next");
            System.out.println("3. other options");
            System.out.println("4. none, get back");
            String response = MyScanner.GetScanner1().next();
            response = CheckValidity.numeric(4, response);
            if(response.equals("1")){
                generalShow(tweets, index-1, user);
            }
            else if(response.equals("2")){
                generalShow(tweets, index+1, user);
            }
            else if (response.equals("3")){
                otherOptions(tweets, index, user);
            }
            else{
                // =))
            }
        }

        else if(index == 0){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Which one?");
            System.out.println("1. next");
            System.out.println("2. other options");
            System.out.println("3. none, get back");
            String response = MyScanner.GetScanner1().next();
            response = CheckValidity.numeric(3, response);
            if(response.equals("1")){
                generalShow(tweets, index+1, user);
            }
            else if(response.equals("2")){
                otherOptions(tweets, index, user);
            }
            else{
                // =))
            }
        }

        else if(index == bound-1){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Which one?");
            System.out.println("1. previous");
            System.out.println("2. other options");
            System.out.println("3. none, get back");
            String response = MyScanner.GetScanner1().next();
            response = CheckValidity.numeric(3, response);
            if(response.equals("1")){
                generalShow(tweets, index-1, user);
            }
            else if(response.equals("2")){
                otherOptions(tweets, index, user);
            }
            else{
                // =))
            }
        }

    }

//    static void generalShowComment(LinkedList<Comment> comments, int index, User user){
//
//        int bound = comments.size();
//        try {
//            System.out.println(ConsoleColors.CYAN_BOLD + comments.get(index).getSender().getUsername() + ": ");
//            System.out.println(ConsoleColors.WHITE_BOLD + comments.get(index).getText());
//        }
//        catch (Exception e){
//            System.out.println(ConsoleColors.WHITE_BRIGHT + "This action can't be done!");
//        }
//        if(bound == 1){
//            System.out.println("1. Show comments");
//            System.out.println("2. Add comment");
//            System.out.println("3. none get back");
//            String response = MyScanner.GetScanner1().next();
//            response = CheckValidity.numeric(3, response);
//            if(response.equals("3")) {
//                // =))
//            }
//            else if(response.equals("2")){
//                System.out.println(ConsoleColors.YELLOW_BOLD + "Go on then, type the comment: ");
//                String com = MyScanner.GetScanner1().nextLine();
//                Comment newComment = new Comment(com, user.getId());
//                comments.get(index).getComments().add(newComment);
//                System.out.println(ConsoleColors.GREEN_BOLD + "Done!");
//                generalShowComment(comments, index, user);
//            }
//            else{
//                if(comments.get(index).getComments().size() == 0){
//                    System.out.println(ConsoleColors.RED_BOLD + "This comment has no comments!");
//                }
//                else {
//                    generalShowComment(comments.get(index).getComments(), 0, user);
//                }
//            }
//        }
//        else if(index > 0 && index < bound-1){
//            System.out.println(ConsoleColors.YELLOW_BOLD + "Which one?");
//            System.out.println("1. previous");
//            System.out.println("2. next");
//            System.out.println("3. Show comments");
//            System.out.println("4. Add comment");
//            System.out.println("5. none get back");
//            String response = MyScanner.GetScanner1().next();
//            response = CheckValidity.numeric(5, response);
//            if(response.equals("1")){
//                generalShowComment(comments, index-1, user);
//            }
//            else if(response.equals("2")){
//                generalShowComment(comments, index+1, user);
//            }
//            else if(response.equals("3")){
//                if(comments.get(index).getComments().size() == 0){
//                    System.out.println(ConsoleColors.RED_BOLD + "This comment has no comments!");
//                }
//                else {
//                    generalShowComment(comments.get(index).getComments(), 0, user);
//                }
//            }
//            else if(response.equals("4")){
//                System.out.println(ConsoleColors.YELLOW_BOLD + "Go on then, type the comment: ");
//                String com = MyScanner.GetScanner1().nextLine();
//                Comment newComment = new Comment(com, user.getId());
//                comments.get(index).getComments().add(newComment);
//                System.out.println(ConsoleColors.GREEN_BOLD + "Done!");
//                generalShowComment(comments, index, user);
//            }
//            else{
//                // =))
//            }
//        }
//
//        else if(index == 0){
//            System.out.println(ConsoleColors.YELLOW_BOLD + "Which one?");
//            System.out.println("1. next");
//            System.out.println("2. Show comments");
//            System.out.println("3. Add comment");
//            System.out.println("4. none get back");
//            String response = MyScanner.GetScanner1().next();
//            response = CheckValidity.numeric(4, response);
//            if(response.equals("1")){
//                generalShowComment(comments, index+1, user);
//            }
//            else if(response.equals("2")){
//                if(comments.get(index).getComments().size() == 0){
//                    System.out.println(ConsoleColors.RED_BOLD + "This comment has no comments!");
//                }
//                else {
//                    generalShowComment(comments.get(index).getComments(), 0, user);
//                }
//            }
//            else if(response.equals("3")){
//                System.out.println(ConsoleColors.YELLOW_BOLD + "Go on then, type the comment: ");
//                String com = MyScanner.GetScanner1().nextLine();
//                Comment newComment = new Comment(com, user.getId());
//                comments.get(index).getComments().add(newComment);
//                System.out.println(ConsoleColors.GREEN_BOLD + "Done!");
//                generalShowComment(comments, index, user);
//            }
//            else{
//                // =))
//            }
//        }
//
//        else if(index == bound-1){
//            System.out.println(ConsoleColors.YELLOW_BOLD + "Which one?");
//            System.out.println("1. previous");
//            System.out.println("2. Show comments");
//            System.out.println("3. Add comment");
//            System.out.println("4. None, get back");
//            String response = MyScanner.GetScanner1().next();
//            response = CheckValidity.numeric(4, response);
//            if(response.equals("1")){
//                generalShowComment(comments, index-1, user);
//            }
//            else if(response.equals("2")){
//                if(comments.get(index).getComments().size() == 0){
//                    System.out.println(ConsoleColors.RED_BOLD + "This comment has no comments!");
//                }
//                else {
//                    generalShowComment(comments.get(index).getComments(), 0, user);
//                }
//            }
//            else if(response.equals("3")){
//                System.out.println(ConsoleColors.YELLOW_BOLD + "Go on then, type the comment: ");
//                String com = MyScanner.GetScanner1().nextLine();
//                Comment newComment = new Comment(com, user.getId());
//                comments.get(index).getComments().add(newComment);
//                System.out.println(ConsoleColors.GREEN_BOLD + "Done!");
//                generalShowComment(comments, index, user);
//            }
//            else{
//                // =))
//            }
//        }
//
//    }

    static void otherOptions(LinkedList<Tweet_Comment> tweets, int index, User user){

        System.out.println(ConsoleColors.YELLOW_BOLD + "Which one?");
        System.out.println("1. like/dislike");
        System.out.println("2. save");
        System.out.println("3. resend");
        System.out.println("4. forward...");
        System.out.println("5. block user");
        System.out.println("6. mute user");
        System.out.println("7. report user");
        System.out.println("8. view user's page");
        System.out.println("9. add comment");
        System.out.println("10. show comments");
        System.out.println("11. none, get back");
        String response = MyScanner.GetScanner1().next();
        response = CheckValidity.numeric(11, response);
        if(response.equals("1")){
            if(!user.getLikedTweetsId().contains(new Integer(tweets.get(index).getID()))) {
                user.getLikedTweetsId().add(tweets.get(index).getID());
                tweets.get(index).like();
                System.out.println(ConsoleColors.GREEN + "You have liked this tweet!");
            }
            else{
                user.getLikedTweetsId().remove(new Integer(tweets.get(index).getID()));
                tweets.get(index).dislike();
                System.out.println(ConsoleColors.RED + "You have disliked this tweet!");
            }
            SaveNLoad.saveTweet_Comments();
            SaveNLoad.saveUsers();
            otherOptions(tweets, index, user);
        }
        else if(response.equals("2")){
            user.getSavedTweetsId().add(tweets.get(index).getID());
            System.out.println(ConsoleColors.YELLOW_BOLD + "Tweet added to saved tweets!");
            SaveNLoad.saveUsers();
            otherOptions(tweets, index, user);
        }
        else if(response.equals("3")){
            try {
                if (user.getId() == tweets.get(index).getSender().getId()) {
                    System.out.println(ConsoleColors.RED_BOLD + "You can't resend your tweet!");
                } else {
                    user.getMyTweetsId().add(new Tweet_Comment("#resended  " + tweets.get(index).getText(), user.getId()).getID());
                    System.out.println(ConsoleColors.YELLOW_BOLD + "You have resended the tweet!");
                }
            }
            catch (Exception e){
                System.out.println(ConsoleColors.WHITE_BRIGHT + "this action can't be done!");
            }
            SaveNLoad.saveUsers();
            SaveNLoad.saveTweet_Comments();
            otherOptions(tweets, index, user);
        }
        else if(response.equals("4")){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Do you like to forward it for a user or a sorting?");
            System.out.println("1. user");
            System.out.println("2. sorting");
            String ans = MyScanner.GetScanner1().next();
            ans = CheckValidity.numeric(2, ans);
            if(ans.equals("1")) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "For which user do you like to forward this? ");
                String selectedUser = MyScanner.GetScanner1().next();
                selectedUser = CheckValidity.username(selectedUser);
                // checking validity & finding
                User thisUser = null;
                for (int i = 0; i < User.getActiveUsers().size(); i++) {
                    if (User.getActiveUsers().get(i).getUsername().equals(selectedUser)) { // user found
                        thisUser = User.getActiveUsers().get(i);
                        break;
                    }
                }
                while (thisUser == null) { // invalid input
                    System.out.println(ConsoleColors.RED_UNDERLINED + "User not found, do you want to continue? (y/n)");
                    String respond2 = MyScanner.GetScanner1().next();
                    if (respond2.equals("n")) {
                        otherOptions(tweets, index, user);
                    }
                    System.out.println(ConsoleColors.RED_BOLD + "try again: ");
                    selectedUser = MyScanner.GetScanner1().next();
                    selectedUser = CheckValidity.username(selectedUser);
                    for (int i = 0; i < User.getActiveUsers().size(); i++) {
                        if (User.getActiveUsers().get(i).getUsername().equals(selectedUser)) { // user found
                            thisUser = User.getActiveUsers().get(i);
                            break;
                        }
                    }
                }
                if (user.getFollowingsId().contains(thisUser.getId()) || user.getFollowersId().contains(thisUser.getId())) {
                    SendingMassage.sendMassage1(user, thisUser, tweets.get(index).getText());
                    System.out.println(ConsoleColors.YELLOW_BOLD + "tweet is forwarded!");
                } else {
                    System.out.println(ConsoleColors.RED_BOLD + "this action can't be done!");
                }
            }
            else{
                if (user.getMySortings().size() == 0){
                    System.out.println(ConsoleColors.RED_UNDERLINED + "You have not created any sortings yet!");
                }
                else {
                    System.out.println(ConsoleColors.YELLOW_BOLD + "For which sorting do you like to forward this?");
                    String sorting = MyScanner.GetScanner1().nextLine();
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
                        SendingMassage.sendMassage1(user, user1, tweets.get(index).getText());
                        if (!user1.getMutedId().contains(user.getId())){
                            user1.getMyNotifs().add(new Notification("User " + user.getUsername() + " has forwarded a tweet to you"));
                        }
                    }
                    System.out.println(ConsoleColors.GREEN_BOLD + "Done!");
                }
            }
            SaveNLoad.saveUsers();
            SaveNLoad.loadChats();
            otherOptions(tweets, index, user);
        }
        else if(response.equals("5")){
            if(user.getId() == tweets.get(index).getSender().getId()){
                System.out.println(ConsoleColors.RED_BOLD + "You can't block yourself!");
            }
            else {
                if (!user.getBlackListId().contains(tweets.get(index).getSender().getId())) {
                    user.getBlackListId().add(tweets.get(index).getSender().getId());
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "user is blocked!");
            }
            SaveNLoad.saveUsers();
            otherOptions(tweets, index, user);
        }
        else if(response.equals("6")){
            if(user.getId() == tweets.get(index).getSender().getId()){
                System.out.println(ConsoleColors.RED_BOLD + "You can't mute yourself!");
            }
            else {
                if (!user.getMutedId().contains(tweets.get(index).getSender().getId())) {
                    user.getMutedId().add(tweets.get(index).getSender().getId());
                }
                System.out.println(ConsoleColors.YELLOW_BOLD + "user is muted!");
            }
            SaveNLoad.saveUsers();
            otherOptions(tweets, index, user);
        }
        else if(response.equals("7")){
            if(user.getId() == tweets.get(index).getSender().getId()){
                System.out.println(ConsoleColors.RED_BOLD + "You can't report yourself!");
            }
            else {
                tweets.get(index).getSender().Report();
                System.out.println(ConsoleColors.YELLOW_BOLD + "User is reported!");
            }
            SaveNLoad.saveTweet_Comments();
            otherOptions(tweets, index, user);
        }
        else if(response.equals("8")){
            Watch_Someones_Page.show(user, tweets.get(index).getSender());
            otherOptions(tweets, index, user);
        }
        else if(response.equals("9")){
            System.out.println(ConsoleColors.YELLOW_BOLD + "type down the comment: ");
            String comment = MyScanner.GetScanner1().nextLine();
            Tweet_Comment newComment = new Tweet_Comment(comment, user.getId());
            tweets.get(index).getCommentsId().add(newComment.getID());
            System.out.println(ConsoleColors.GREEN_BOLD + "Comment added successfully!");
            otherOptions(tweets, index, user);
        }
        else if(response.equals("10")){
            Tweet_Comment thisTweet = tweets.get(index);
            if(thisTweet.getComments().size() == 0){
                System.out.println(ConsoleColors.YELLOW_BOLD + "This tweets has no comment!");
            }
            else{
                generalShow(thisTweet.getComments(), 0, user);
                System.out.println(ConsoleColors.YELLOW_BOLD + "that's it!");
            }
            SaveNLoad.save();
            otherOptions(tweets, index, user);
        }
        else{
            // =))
        }

    }

    static void sortByLike(LinkedList<Tweet_Comment> tweets){

        for (int i = 0; i < tweets.size()-1; i++) {
            for (int j = i+1; j < tweets.size(); j++) {
                if(tweets.get(i).getLikes() < tweets.get(j).getLikes()){
                    Tweet_Comment arts = tweets.get(i);
                    tweets.set(i, tweets.get(j));
                    tweets.set(j, arts);
                    i = -1;
                    break;
                }
            }
        }

    }

}