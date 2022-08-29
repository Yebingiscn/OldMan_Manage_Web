package com.example.service;

import com.example.entity.GetNotificationInfo;
import com.example.entity.OldManAlbertInfo;
import com.example.entity.OldManData;

import java.util.List;

public interface OldManDataService {
    List<OldManData> getOldManData();

    void saveOldManAlbertInfo(OldManAlbertInfo oldManAlbertInfo);

    List<GetNotificationInfo> getNotice();
}
