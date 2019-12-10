package me.ham.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    Level level;

    private long loginCnt;

    private long recommendCnt;

    @Builder
    public User(String name){
        this.name       = name;
        this.level      = Level.BASIC;
        this.loginCnt   = 0;
        this.recommendCnt = 0;
    }

    public boolean isUpgradeTarget(){
        if(Level.BASIC == level
        && loginCnt >= 50){
            return true;
        }else if(Level.SILVER == level
                && recommendCnt >= 30){
            return true;
        }else{
            return false;
        }
    }
}
