package com.suntime.study.entity;

import com.suntime.study.dto.TimerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "subject_table")
public class TimerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String subject;

    @Column
    private int time;

    public static TimerEntity toTimerEntity(TimerDTO timerDTO){
        TimerEntity timerEntity = new TimerEntity();
        timerEntity.setSubject(timerDTO.getSubject());
        return timerEntity;
    }
}