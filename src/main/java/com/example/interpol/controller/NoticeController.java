package com.example.interpol.controller;

import com.example.interpol.model.Notice;
import com.example.interpol.model.User;
import com.example.interpol.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NoticeController {
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notices")
    public String findAll(Model model) {
        List<Notice> noticeList = noticeService.findAll();
        model.addAttribute("noticeList", noticeList);
        return "notice-list";
    }

    @GetMapping("/notice-create")
    public String createNoticeForm(User user) {
        return "notice-create";
    }

    @PostMapping("/notice-create")
    public String createNotice(Notice notice) {
        noticeService.createNotice(notice);
        return "redirect:/notices";
    }
}
