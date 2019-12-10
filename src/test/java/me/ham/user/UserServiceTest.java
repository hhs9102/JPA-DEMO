package me.ham.user;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    List<User> userList;

    @Before("setUp")
    public void setUp(){
        userList = Arrays.asList(new User("김철수")
                ,new User("이영자")
                ,new User("신동엽")
                ,new User("유재석")
                ,new User("양세형"));
    }


    @Test
    public void saveUser() {
        User user = new User("함호식");
        userService.saveUser(user);
    }

    @Test
    public void findUser() {
        saveUser();
        User user = userService.findUser(1);
        assertEquals(1, user.getId());
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

}