package se.systementor.unittester.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
public class LogUsageRecord {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    @Getter
    @Setter
    private String action;

    @Getter
    @Setter
    private int tal1;
    @Getter
    @Setter
//    private int tal2;
    private int tal2;

//    public int getTal2() {
//        return tal1;
//    }
//    public void setTal2(int tal2) {
//        this.tal2 = tal2;
//    }

    @Getter
    @Setter
    private int result;
    @Getter
    @Setter
    private Date date;

    public LogUsageRecord() {}

    public LogUsageRecord(int tal1, int tal2, String action, int result) {
        this.tal1 = tal1;
        this.tal2 = tal2;
        this.action = action;
        this.result = result;
        date = new Date(System.currentTimeMillis());
    }
}
