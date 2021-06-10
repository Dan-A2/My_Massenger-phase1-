import java.util.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeLine {

    static private final Logger logger = (Logger) LogManager.getLogger(TimeLine.class);

    public static void startTimeline(User user){

        LinkedList<Tweet_Comment> followingTweets = new LinkedList<>();
        LinkedList<Tweet_Comment> likedTweets = new LinkedList<>();
        if(user.getFollowingsId().size() == 0){
            System.out.println(ConsoleColors.RED_BOLD + "You have not followed anyone yet; do it in order so see your followings different tweets here!");
            MainMenu.showOptions(user);
        }
        for (User user1: user.getFollowings()) {
            for (Tweet_Comment tweet: user1.getMyTweets()) {
                followingTweets.add(tweet);
            }
            for (Tweet_Comment tweet: user1.getLiked()) {
                likedTweets.add(tweet);
            }
        }
        System.out.println(ConsoleColors.YELLOW_BOLD + "This is your timeline page, which list do you wanna see? ");
        System.out.println("1. Tweets from your followings");
        System.out.println("2. Tweets that your followings have liked");
        System.out.println("3. Or you wanna get back");
        String response = MyScanner.GetScanner1().next();
        response = CheckValidity.numeric(3, response);
        switch (response){
            case "1":
                showFollowing(followingTweets, user);
                startTimeline(user);
                break;
            case "2":
                showLiked(likedTweets, user);
                startTimeline(user);
                break;
            case "3":
                // =))
                break;
        }

    }

    static void showFollowing(LinkedList<Tweet_Comment> followings, User user) {

        if(followings.size() == 0){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Your followings have not posted any tweets yet!");
            startTimeline(user);
        }
        System.out.println(ConsoleColors.YELLOW_BOLD + "Your followings have posted " + followings.size() + " tweets!");
        Explorer.generalShow(followings, 0, user);

    }

    static void showLiked(LinkedList<Tweet_Comment> liked, User user){

        if(liked.size() == 0){
            System.out.println(ConsoleColors.YELLOW_BOLD + "Your followings have not liked any tweets yet!");
            startTimeline(user);
        }
        System.out.println(ConsoleColors.YELLOW_BOLD + "Your followings have posted " + liked.size() + " tweets!");
        Explorer.generalShow(liked, 0, user);

    }

}