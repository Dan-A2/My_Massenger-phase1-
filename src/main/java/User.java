import com.google.gson.annotations.Expose;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class User {

    static private int lastId = 0;
    @Expose(serialize = false, deserialize = false)
    static private LinkedList<User> activeUsers = new LinkedList<>();
    @Expose(serialize = false, deserialize = false)
    static private LinkedList<User> inactiveUsers = new LinkedList<>();
    private String firstName;
    private String lastName;
    private String username;
    private int id;
    private int reported;
    private String password;
    private boolean isAccountPublic;
    private String whoCanSeeLastSeen;
    private String bio;
    private String Email;
    private String birthday;
    private String phoneNumber;
    private boolean isActive;
    private boolean isOnline;
    private String lastSeen;
    private final LinkedList<Integer> friendsId = new LinkedList<>();
    private final LinkedList<Integer> followersId = new LinkedList<>();
    private final LinkedList<Integer> followingsId = new LinkedList<>();
    private final LinkedList<Integer> blackListId = new LinkedList<>();
    private final LinkedList<Integer> mutedId = new LinkedList<>();
    private final LinkedList<Integer> unseenChatsId = new LinkedList<>();
    private final LinkedList<Integer> myChatsId = new LinkedList<>();
    private final LinkedList<Integer> savedTweetsId = new LinkedList<>();
    private final LinkedList<Integer> savedMassagesId = new LinkedList<>();
    private final LinkedList<Integer> savedTextsOfMeId = new LinkedList<>();
    private final LinkedList<Integer> myTweetsId = new LinkedList<>();
    private final LinkedList<Integer> requestersId = new LinkedList<>();
    private final LinkedList<Integer> LikedTweetsId = new LinkedList<>();
    private final LinkedList<Notification> myNotifs = new LinkedList<>();
    private final HashMap <String, LinkedList<Integer>> mySortings = new HashMap<>();

    public User(String firstName, String lastName, String username, String password, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.Email = email;
        lastId++;
        this.id = lastId;
        activeUsers.add(this);
        this.setOnline(true);
        this.setAccountPublic(true);
        this.setActive(true);
        this.setWhoCanSeeLastSeen("everybody");
        SaveNLoad.saveUsers();

    }

    public static LinkedList<User> getActiveUsers() {
        return activeUsers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountPublic() {
        return isAccountPublic;
    }

    public void setAccountPublic(boolean accountPublic) {
        isAccountPublic = accountPublic;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        User.lastId = lastId;
    }

    public int getReported() {
        return reported;
    }

    public void Report() {
        this.reported++;
    }

    public String getWhoCanSeeLastSeen() {
        return whoCanSeeLastSeen;
    }

    public void setWhoCanSeeLastSeen(String whoCanSeeLastSeen) {
        this.whoCanSeeLastSeen = whoCanSeeLastSeen;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday.toString();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen() {
        LocalDateTime lastSennMolayye = LocalDateTime.now();
        this.lastSeen = lastSennMolayye.toString();
    }

    public LinkedList<User> getFriends() {
        LinkedList<User> friends = new LinkedList<>();
        for (Integer id : this.friendsId) {
            for (User user : User.getActiveUsers()) {
                if(user.getId() == id){
                    friends.add(user);
                }
            }
        }
        return friends;
    }

    public LinkedList<User> getFollowers() {
        LinkedList<User> followers = new LinkedList<>();
        for (Integer id : this.followersId) {
            for (User user : User.getActiveUsers()) {
                if(user.getId() == id){
                    followers.add(user);
                }
            }
        }
        return followers;
    }

    public LinkedList<User> getFollowings() {
        LinkedList<User> followings = new LinkedList<>();
        for (Integer id : this.followingsId) {
            for (User user : User.getActiveUsers()) {
                if(user.getId() == id){
                    followings.add(user);
                }
            }
        }
        return followings;
    }

    public LinkedList<User> getBlackList() {
        LinkedList<User> blackList = new LinkedList<>();
        for (Integer id : this.blackListId) {
            for (User user : User.getActiveUsers()) {
                if(user.getId() == id){
                    blackList.add(user);
                }
            }
        }
        return blackList;
    }

    public LinkedList<User> getMuted() {
        LinkedList<User> muted = new LinkedList<>();
        for (Integer id : this.mutedId) {
            for (User user : User.getActiveUsers()) {
                if(user.getId() == id){
                    muted.add(user);
                }
            }
        }
        return muted;
    }

    public LinkedList<Chat> getUnseenChats() {
        LinkedList<Chat> unseenChats = new LinkedList<>();
        for (Integer id : unseenChatsId) {
            for (Chat chat : Chat.getAllChats()) {
                if(chat.getID() == id){
                    unseenChats.add(chat);
                }
            }
        }
        return unseenChats;
    }

    public LinkedList<Tweet_Comment> getSavedTweets() {
        LinkedList<Tweet_Comment> savedTweets = new LinkedList<>();
        for (Integer id : savedTweetsId) {
            for (Tweet_Comment tweet : Tweet_Comment.getAll()) {
                if(tweet.getID() == id){
                    savedTweets.add(tweet);
                }
            }
        }
        return savedTweets;
    }

    public LinkedList<Tweet_Comment> getMyTweets() {
        LinkedList<Tweet_Comment> myTweets = new LinkedList<>();
        for (Integer id : myTweetsId) {
            for (Tweet_Comment tweet : Tweet_Comment.getAll()) {
                if(id.equals(tweet.getID())){
                    myTweets.add(tweet);
                }
            }
        }
        return myTweets;
    }

    public LinkedList<User> getRequesters() {
        LinkedList<User> requesters = new LinkedList<>();
        for (Integer id : requestersId) {
            for (User user : getActiveUsers()) {
                if(user.getId() == id){
                    requesters.add(user);
                }
            }
        }
        return requesters;
    }

    public LinkedList<Tweet_Comment> getLiked() {
        LinkedList<Tweet_Comment> Liked = new LinkedList<>();
        for (Integer id : LikedTweetsId) {
            for (Tweet_Comment tweet : Tweet_Comment.getAll()) {
                if(tweet.getID() == id){
                    Liked.add(tweet);
                }
            }
        }
        return Liked;
    }

    public LinkedList<Notification> getMyNotifs() {
        return myNotifs;
    }

    public LinkedList<Chat> getMyChats() {
        LinkedList<Chat> myChats = new LinkedList<>();
        for (Integer id : myChatsId) {
            for (Chat chat : Chat.getAllChats()) {
                if(chat.getID() == id){
                    myChats.add(chat);
                }
            }
        }
        return myChats;
    }

    public LinkedList<Massage> getSavedMassages() {
        LinkedList<Massage> savedMassages = new LinkedList<>();
        for (Integer id : savedMassagesId) {
            for (Massage massage : Massage.getAllMassages()) {
                if(massage.getID() == id){
                    savedMassages.add(massage);
                }
            }
        }
        return savedMassages;
    }

    public LinkedList<Massage> getSavedTextsOfMe() {
        LinkedList<Massage> savedTextsOfMe = new LinkedList<>();
        for (Integer id : savedTextsOfMeId) {
            for (Massage massage : Massage.getAllMassages()) {
                if(massage.getID() == id){
                    savedTextsOfMe.add(massage);
                }
            }
        }
        return savedTextsOfMe;
    }

    public HashMap<String, LinkedList<Integer>> getMySortings() {
        return mySortings;
    }

    public static LinkedList<User> getInactiveUsers() {
        return inactiveUsers;
    }

    public LinkedList<Integer> getUnseenChatsId() {
        return unseenChatsId;
    }

    public LinkedList<Integer> getMyChatsId() {
        return myChatsId;
    }

    public LinkedList<Integer> getSavedTweetsId() {
        return savedTweetsId;
    }

    public LinkedList<Integer> getSavedMassagesId() {
        return savedMassagesId;
    }

    public LinkedList<Integer> getSavedTextsOfMeId() {
        return savedTextsOfMeId;
    }

    public LinkedList<Integer> getMyTweetsId() {
        return myTweetsId;
    }

    public LinkedList<Integer> getRequestersId() {
        return requestersId;
    }

    public LinkedList<Integer> getLikedTweetsId() {
        return LikedTweetsId;
    }

    public LinkedList<Integer> getFriendsId() {
        return friendsId;
    }

    public LinkedList<Integer> getFollowersId() {
        return followersId;
    }

    public LinkedList<Integer> getFollowingsId() {
        return followingsId;
    }

    public LinkedList<Integer> getBlackListId() {
        return blackListId;
    }

    public LinkedList<Integer> getMutedId() {
        return mutedId;
    }

}