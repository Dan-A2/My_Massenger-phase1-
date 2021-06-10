public class CheckValidity {

    public static boolean isNameValid(String name){
        for (int i = 0; i < name.length(); i++) {
            if(!Character.isAlphabetic(name.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isUserNameValid(String username){

        for (int i = 0; i < username.length(); i++) {
            if(username.charAt(i) == ' '){
                return false;
            }
        }
        return true;

    }

    public static boolean isUsernameRepeated(String username){

        for (int i = 0; i < User.getActiveUsers().size(); i++) {
            if (User.getActiveUsers().get(i).getUsername().equals(username)) {
                return true;
            }
        }
        for (int i = 0; i < User.getInactiveUsers().size(); i++) {
            if(User.getInactiveUsers().get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;

    }

    public static boolean isPhoneNumberRepeated(String phoneNumber){

        for (int i = 0; i < User.getActiveUsers().size(); i++) {
            try {
                if (User.getActiveUsers().get(i).getPhoneNumber().equals(phoneNumber)) {
                    return true;
                }
            } catch (Exception e) {}
        }
        for (int i = 0; i < User.getInactiveUsers().size(); i++) {
            try {
                if (User.getInactiveUsers().get(i).getPhoneNumber().equals(phoneNumber)) {
                    return true;
                }
            } catch (Exception e) {}
        }
        return false;

    }

    public static boolean isEmailRepeated(String email){

        for (int i = 0; i < User.getActiveUsers().size(); i++) {
            if (User.getActiveUsers().get(i).getEmail().equals(email)) {
                return true;
            }
        }
        for (int i = 0; i < User.getInactiveUsers().size(); i++) {
            if(User.getInactiveUsers().get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;

    }

    public static boolean isNumberValid(String phoneNumber){
        for (int i = 0; i < phoneNumber.length(); i++) {
            if(!Character.isDigit(phoneNumber.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static String yn(String respond){

        while(!respond.equals("y") && !respond.equals("n")) {
            System.out.println(ConsoleColors.RED_UNDERLINED + "invalid input, try again: ");
            respond = MyScanner.GetScanner1().next();
        }
        return respond;

    }

    public static String username(String username){

        while (!isUserNameValid(username)){
            System.out.println(ConsoleColors.RED_UNDERLINED + "Spaces are not allowed, try again: ");
            username = MyScanner.GetScanner1().nextLine();
        }
        return username;

    }

    public static String newUsername(String username){

        while (!isUserNameValid(username) || isUsernameRepeated(username)) {
            if(!isUserNameValid(username)){
                System.out.println(ConsoleColors.RED_UNDERLINED + "Spaces are not allowed, try again: ");
                username = MyScanner.GetScanner1().nextLine();
            }
            else if(isUsernameRepeated(username)){
                System.out.println(ConsoleColors.RED_UNDERLINED + "Username already taken, try again: ");
                username = MyScanner.GetScanner1().nextLine();
            }
        }
        return username;

    }

    public static String numeric(int bound, String ans){

        if(bound == 1){
            while(!ans.equals("1")){
                System.out.println(ConsoleColors.CYAN_BOLD + "Invalid input. do it again: ");
                ans = MyScanner.GetScanner1().next();
            }
        }
        else if(bound == 2){
            while(!ans.equals("1") && !ans.equals("2")){
                System.out.println(ConsoleColors.CYAN_BOLD + "Invalid input. do it again: ");
                ans = MyScanner.GetScanner1().next();
            }
        }
        else if(bound == 3){
            while(!ans.equals("1") && !ans.equals("2") && !ans.equals("3")){
                System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid input, please try again: ");
                ans = MyScanner.GetScanner1().next();
            }
        }
        else if(bound == 4){
            while(!ans.equals("1") && !ans.equals("2") && !ans.equals("3") && !ans.equals("4")){
                System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid input, please try again: ");
                ans = MyScanner.GetScanner1().next();
            }
        }
        else if(bound == 5){
            while(!ans.equals("1") && !ans.equals("2") && !ans.equals("3") && !ans.equals("4") && !ans.equals("5")){
                System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid input, please try again: ");
                ans = MyScanner.GetScanner1().next();
            }
        }
        else if(bound == 6){
            while(!ans.equals("1") && !ans.equals("2") && !ans.equals("3") && !ans.equals("4") && !ans.equals("5") && !ans.equals("6")){
                System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid input, please try again: ");
                ans = MyScanner.GetScanner1().next();
            }
        }
        else if(bound == 8){
            while(!ans.equals("1") && !ans.equals("2") && !ans.equals("3") && !ans.equals("4") && !ans.equals("5") && !ans.equals("6") && !ans.equals("7") && !ans.equals("8")){
                System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid input, please try again: ");
                ans = MyScanner.GetScanner1().next();
            }
        }
        else if(bound == 11){
            while(!ans.equals("1") && !ans.equals("2") && !ans.equals("3") && !ans.equals("4") && !ans.equals("5") && !ans.equals("6") && !ans.equals("7") && !ans.equals("8") && !ans.equals("9") && !ans.equals("10") && !ans.equals("11")){
                System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid input, please try again: ");
                ans = MyScanner.GetScanner1().next();
            }
        }
        return ans;

    }

    public static String name(String name){

        while(!isNameValid(name)){
            System.out.println(ConsoleColors.RED_UNDERLINED + "this name is not valid, try again: ");
            name = MyScanner.GetScanner1().next();
        }
        return name;

    }

    public static String number(String number){

        while (!isNumberValid(number)){
            System.out.println(ConsoleColors.RED_UNDERLINED + "invalid input, try again: ");
            number = MyScanner.GetScanner1().next();
        }
        return number;

    }

    public static String email(String email){

        while (isEmailRepeated(email)){
            System.out.println(ConsoleColors.RED_BOLD + "This email is for another account :|, try again: ");
            email = MyScanner.GetScanner1().next();
        }
        return email;

    }

    public static String phoneNumber(String phonenumber){

        while (!isNumberValid(phonenumber) || isPhoneNumberRepeated(phonenumber)){
            System.out.println(ConsoleColors.RED_BOLD + "Either invalid input or repeated phonenumber! try again: ");
            phonenumber = MyScanner.GetScanner1().next();
        }
        return phonenumber;

    }

}