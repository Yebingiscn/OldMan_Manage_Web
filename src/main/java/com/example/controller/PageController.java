package com.example.controller;

import com.example.entity.GetDataAndName;
import com.example.entity.GetNotificationInfo;
import com.example.entity.OldManAlbertInfo;
import com.example.service.OldManDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/page")
public class PageController {
    @Resource
    OldManDataService oldManDataService;

    @RequestMapping("/index-Show-DataAndName")
    public GetDataAndName showData(HttpSession session) {
        return new GetDataAndName(
                session.getAttribute("user").toString(),
                oldManDataService.getOldManData());
    }

    @RequestMapping(value = "/Client-add-alerts", method = RequestMethod.POST)
    public void clientChangeStatus(@RequestParam("oldManName") String oldManName,
                                   @RequestParam("oldManStatus") String oldManStatus,
                                   @RequestParam("oldManLocation") String oldManLocation,
                                   @RequestParam("oldManContact") String oldManContact) {
        synchronized(this) {    //可能有多个客户端同时发送，加把锁免得出问题
            OldManAlbertInfo oldManAlbertInfo =
                    new OldManAlbertInfo(oldManName,oldManStatus,oldManLocation,oldManContact,"未处理");
            oldManDataService.saveOldManAlbertInfo(oldManAlbertInfo);
        };

    }

    @RequestMapping("/index-Show-Notification")
    public List<GetNotificationInfo> getNotificationInfo() {
        return  oldManDataService.getNotice();
    }
}
