package com.example.service.impl;

import com.example.entity.GetNotificationInfo;
import com.example.entity.OldManAlbertInfo;
import com.example.entity.OldManData;
import com.example.mapper.OldManMapper;
import com.example.service.OldManDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OldManDataServiceImpl implements OldManDataService {
    @Resource
    OldManMapper oldManMapper;

    @Override
    public List<OldManData> getOldManData() {
        return oldManMapper.selectAllOldManData();
    }

    @Override
    @Transactional
    public void saveOldManAlbertInfo(OldManAlbertInfo oldManAlbertInfo) {
        int oldManId = oldManMapper.findIdByNameAndContact
                (oldManAlbertInfo.getOldManName(), oldManAlbertInfo.getOldManContact());
        oldManMapper.addOldManAlbertInfo
                (oldManId,oldManAlbertInfo.getOldManName(),
                        oldManAlbertInfo.getOldManLocation(),oldManAlbertInfo.getOldManProcess(), new Date());
    }

    @Override
    public List<GetNotificationInfo> getNotice() {
        return oldManMapper.selectAllNotice();
    }
}
