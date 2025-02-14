package pl.coderslab;

import pl.coderslab.daoClasses.UserDao;
import pl.coderslab.entity.User;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        User user = new User();
//        user.setUsername("coder_update");
//        user.setPassword("coderslab_update");
//        user.setEmail("coderslab_updated@coderslab.pl");

        UserDao userDao = new UserDao();
//        userDao.create(user);
//        System.out.println(user.getId());

//        User readUser = userDao.read(2);
//        System.out.println(readUser.getId());
//        System.out.println(readUser.getUsername());
//        System.out.println(readUser.getPassword());
//        System.out.println(readUser.getEmail());

//        readUser.setUsername("coder_update");
//        readUser.setPassword("coderslab_update");
//        readUser.setEmail("coderslab_updated@coderslab.pl");
//        userDao.update(readUser);

//        userDao.delete(2);

        User[] array = userDao.findAll("SELECT * FROM users WHERE email REGEXP '[a-z]+\\@[a-z]+\\.pl'");
        System.out.println(Arrays.toString(array));

    }
}
