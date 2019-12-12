package me.ham.user;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Test
    public void saveUser() {
        User user = new User();
        user.setName("함호식");
        userService.saveUser(user);
    }

    @Test
    public void findUser() {
        User user = new User();
        user.setName("함호식");
        userService.saveUser(user);
        user = userDao.findByName("함호식");
        assertEquals("함호식",user.getName());
        System.out.println(user.getId());
    }


    @Test
    @DisplayName("2. UserService에 대한 bean 주입을 테스트 한다.")
    public void isExistUserServiceBean(){
        assertNotNull(userService);
    }

    @Test
    @DisplayName("3. 원하는 정보만 수정되는지 기능 테스트를 한다.")
    public void checkUpdateFeature() {
        saveUser(); //id : 1
        saveUser(); //id : 2

        User user = userService.findUser(1);
        user.setName("김철수");
        userService.saveUser(user);

        User user1 = userService.findUser(1);
        User user2 = userService.findUser(2);
        assertEquals("김철수", user1.getName());
        assertEquals("함호식", user2.getName());
    }

    @Test
    @DisplayName("사용자가 처음 가입하면 BASIC 레벨이 된다.")
    public void checkInitialUserIsBasicLevel() {
        saveUser();
        User user = userService.findUser(1);
        assertEquals(Level.BASIC, user.getLevel());
    }

    @Test
    //각 User 이름을 통해 체크하는 로직으로 변경 필요.
    public void upgradeLevelToSilver(){
        saveUser();
        User toSilverUser = userService.findUser(1);
        toSilverUser.setLoginCnt(50);
        userService.saveUser(toSilverUser); //로그인 기록 저장

        saveUser();
        User basicUser = userService.findUser(2);
        basicUser.setLoginCnt(49);
        userService.saveUser(basicUser);

        userService.upgradeUsers(); //레벨 업그레이드

        toSilverUser = userService.findUser(1); //재조회
        basicUser = userService.findUser(2); //재조회

        assertEquals(Level.SILVER, toSilverUser.getLevel());
        assertEquals(Level.BASIC, basicUser.getLevel());
    }

    @Test
    @DisplayName("사용자 레벨 관리 기능 테스트")
    public void allUserUpgradeTest(){
        List<User> userList = Arrays.asList(
             User.builder().name("이영자").level(Level.BASIC).loginCnt(49).recommendCnt(30).build()    //still Basic
            ,User.builder().name("김철수").level(Level.BASIC).loginCnt(50).recommendCnt(33).build()    //toSilver
            ,User.builder().name("함용문").level(Level.BASIC).loginCnt(50).recommendCnt(30).build()    //toSilver
            ,User.builder().name("김동민").level(Level.SILVER).loginCnt(50).recommendCnt(29).build()   //still Silver
            ,User.builder().name("유재석").level(Level.SILVER).loginCnt(50).recommendCnt(30).build()   //toGold
            ,User.builder().name("임골드").level(Level.GOLD).loginCnt(99).recommendCnt(99).build()     //still Gold
        );
        userDao.saveAll(userList);

        int changedUserCount = userService.upgradeUsers();

        assertAll(
                () -> assertEquals(Level.BASIC, userDao.findByName("이영자").getLevel())
                ,() -> assertEquals(Level.SILVER, userDao.findByName("김철수").getLevel())
                ,() -> assertEquals(Level.SILVER, userDao.findByName("함용문").getLevel())
                ,() -> assertEquals(Level.SILVER, userDao.findByName("김동민").getLevel())
                ,() -> assertEquals(Level.GOLD, userDao.findByName("유재석").getLevel())
                ,() -> assertEquals(Level.GOLD, userDao.findByName("임골드").getLevel())
                ,() -> assertEquals(3, changedUserCount)
        );
    }

    @Test
    @DisplayName("사용자 레벨 관리 기능 테스트 - 홀수달은 더 낮은 회수로 Upgrade 시킨다.")
    public void allUserUpgradeByMonthTest(){
        List<User> userList = Arrays.asList(
                User.builder().name("이영자").level(Level.BASIC).loginCnt(49).recommendCnt(30).build()     //toSilver
                ,User.builder().name("김철수").level(Level.BASIC).loginCnt(50).recommendCnt(33).build()    //toSilver
                ,User.builder().name("함용문").level(Level.BASIC).loginCnt(50).recommendCnt(30).build()    //toSilver
                ,User.builder().name("김동민").level(Level.SILVER).loginCnt(50).recommendCnt(29).build()   //toGold
                ,User.builder().name("유재석").level(Level.SILVER).loginCnt(50).recommendCnt(30).build()   //toGold
                ,User.builder().name("임골드").level(Level.GOLD).loginCnt(99).recommendCnt(99).build()     //still Gold
        );
        userDao.saveAll(userList);

        int changedUserCount = userService.upgradeUsersByMonthEvent(1);

        assertAll(
                () -> assertEquals(Level.SILVER, userDao.findByName("이영자").getLevel())
                ,() -> assertEquals(Level.SILVER, userDao.findByName("김철수").getLevel())
                ,() -> assertEquals(Level.SILVER, userDao.findByName("함용문").getLevel())
                ,() -> assertEquals(Level.GOLD, userDao.findByName("김동민").getLevel())
                ,() -> assertEquals(Level.GOLD, userDao.findByName("유재석").getLevel())
                ,() -> assertEquals(Level.GOLD, userDao.findByName("임골드").getLevel())
                ,() -> assertEquals(5, changedUserCount)
        );
    }
}
