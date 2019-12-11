package me.ham.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    {
        //TODO 스케줄러 등록 시점 올바른 위치로 변경
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(7);
        scheduledExecutorService.scheduleAtFixedRate(this::upgradeUsers, 10, 10, TimeUnit.SECONDS);
    }


    public long saveUser(User user){
        userDao.save(user);
        return user.getId();
    }

    public User findUser(long id) {
        return userDao.findById(id).orElse(null);
    }

    //findAll이 아니라 대상자만 조회하는 쿼리로 수정 필요
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

    public int upgradeUsersByMonthEvent(int month){
        AtomicInteger changedUsers = new AtomicInteger(0);
        List<User> userList = userDao.findAll();
        userList.stream()
                .filter(u -> u.isUpgradeTargetByMonthEvent(month))
                .forEach(u -> {
                    u.setLevel(u.getLevel().nextLevel());
                    userDao.save(u);
                    changedUsers.incrementAndGet();
                });
        return changedUsers.get();
    }
}
