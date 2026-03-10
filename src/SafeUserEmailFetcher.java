import java.util.*;
public class SafeUserEmailFetcher {
    // Model Layer
    static class User{
        int id;
        String name;
        String email;
        User(int id,String name,String email){
            this.id=id;
            this.name=name;
            this.email=email;
        }
        String getEmail() {return email;}
        String getName() {return name;}
    }
    // Repository Layer
    static class UserRepository{
        Map<Integer,User> database = new HashMap<>();
        UserRepository(){
            database.put(1,new User(1,"Kiran Surya","inbox.kiran123@gmail.com"));
            database.put(2,new User(2,"Gandhi Mahaan","mahaan2k22@gmail.com"));
            database.put(3,new User(3,"Sathyavaan",null));
        }
        User findUserById(int id){
            return database.get(id);
        }
    }
    // Service Layer
    static class UserService{
        UserRepository repo = new UserRepository();
        String fetchUserEmail(int userId){
            User user = repo.findUserById(userId);
            if(user==null){
                throw new RuntimeException("User not found with id: "+userId);
            }
            if(user.getEmail()==null){
                return "Email not available";
            }
            return user.getEmail();
        }
    }
   static class UserController{
        UserService service = new UserService();
        void getUserEmail(int userId){
            try{
                String email = service.fetchUserEmail(userId);
                System.out.println("User ID: "+userId);
                System.out.println("Email: "+email);
                System.out.println("----------------");
            }catch (RuntimeException exp){
                System.out.println("Error occured: "+exp.getMessage());
                System.out.println("----------------");
            }
        }
    }
    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.getUserEmail(1);
        controller.getUserEmail(2);
        controller.getUserEmail(3);
    }
}
