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

    public static final int LOGIN_COUNT_FOR_SILVER = 50;
    public static final int RECOMMAND_COUNT_TO_GOLD = 30;
    public static final int LOGIN_COUNT_FOR_SILVER_EVENT = 40;
    public static final int RECOMMAND_COUNT_TO_GOLD_EVENT = 20;
    @Id @GeneratedValue
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    Level level;

    private long loginCnt;

    private long recommendCnt;

    @Builder
    public User(String name, Level level, int loginCnt, int recommendCnt){
        this.name       = name;
        this.level      = level;
        this.loginCnt   = loginCnt;
        this.recommendCnt = recommendCnt;
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

    public boolean isUpgradeTargetByMonthEvent(int month){
        int loginCountForSilver = LOGIN_COUNT_FOR_SILVER;
        int recommandCountForGold = RECOMMAND_COUNT_TO_GOLD;
        //홀수달은 더 낮은 숫자로 할당
        if(month%2==1){
            loginCountForSilver = LOGIN_COUNT_FOR_SILVER_EVENT;
            recommandCountForGold = RECOMMAND_COUNT_TO_GOLD_EVENT;
        }
        if(Level.BASIC == level
        && loginCnt >= loginCountForSilver){
            return true;
        }else if(Level.SILVER == level
                && recommendCnt >= recommandCountForGold){
            return true;
        }else{
            return false;
        }
    }
}
