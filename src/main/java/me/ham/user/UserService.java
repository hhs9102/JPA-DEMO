package me.ham.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public long saveUser(User user){
        userDao.save(user);
        return user.getId();
    }

    public User findUser(long id) {
        return userDao.findById(id).orElse(null);
    }

    public int upgradeUsers(){
        AtomicInteger changedUsers = new AtomicInteger(0);
        List<User> userList = userDao.findAll();
        userList.stream()
                .filter(User::isUpgradeTarget)
                .forEach(u -> {
                    u.setLevel(u.getLevel().nextLevel());
                    userDao.save(u);
                    changedUsers.incrementAndGet();
                });
        return changedUsers.get();
    }
}
