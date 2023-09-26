package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("Джордж", "Вашингтон", "vashington@gmail.com");
      User user2 = new User("Джо", "Байден", "bayden@gmail.com");
      User user3 = new User("Дональд", "Трамп", "tramp@gmail.com");
      User user4 = new User("Барак", "Обама", "obama@gmail.com");

      Car car1 = new Car("Lincoln", 999);
      Car car2 = new Car("Dodge", 777);
      Car car3 = new Car("Ford", 666);
      Car car4 = new Car("Cadillac", 111);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }
      System.out.println(userService.getCarInfo("Lincoln", 999));

      try {
         userService.getCarInfo("Jeep", 555);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }
      context.close();
   }
}
