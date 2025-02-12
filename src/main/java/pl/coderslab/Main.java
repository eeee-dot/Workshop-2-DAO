package pl.coderslab;

import pl.coderslab.daoClasses.UserDao;
import pl.coderslab.entity.User;

public class Main {
    public static void main(String[] args) {
        User user = new User();
//        user.setUsername("coder1");
//        user.setPassword("coderslab1");
//        user.setEmail("coderslab1@coderslab.pl");

        UserDao userDao = new UserDao();
//        userDao.create(user);
//        System.out.println(user.getId());

        User read = userDao.read(2);
        System.out.println(read.getId());
        System.out.println(read.getUsername());
        System.out.println(read.getPassword());
        System.out.println(read.getEmail());
    }
}
