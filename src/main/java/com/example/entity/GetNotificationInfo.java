package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetNotificationInfo {
    String communicate;
    String name;
    String sex;
    String contact;
    String oldManStatus;
    String oldManLocation;
    String oldManProcess;
    String date;
}
