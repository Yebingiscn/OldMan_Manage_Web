package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetDataAndName {
    String name;
    List<OldManData> data;
}
