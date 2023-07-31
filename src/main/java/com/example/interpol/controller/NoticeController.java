package com.example.interpol.controller;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.Notice;
import com.example.interpol.model.User;
import com.example.interpol.service.impl.NoticeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;


@Controller
public class NoticeController {
    public static final String MESSAGE = "message";
    private NoticeServiceImpl noticeService;

    @Autowired
    public NoticeController(NoticeServiceImpl noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notices")
    public String showNoticeList(Model model) {
        List<Notice> noticeList = noticeService.findAll();
        model.addAttribute("noticeList", noticeList);
        return "notices";
    }

    @GetMapping("/client/notices")
    public String showClientNoticeList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Notice> noticeList = noticeService.findNoticesByUserId(user.getId());
        model.addAttribute("noticeList", noticeList);
        return "notices";
    }

    @GetMapping("/notices/new")
    public String showNoticeForm(Model model) {
        model.addAttribute("notice", new Notice());
        return "notice_form";
    }

    @PostMapping("/notices/save")
    public String createNotice(Notice notice, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = (User) request.getSession().getAttribute("user");
        long now = System.currentTimeMillis();
        Timestamp publicationDateTime = new Timestamp(now);
        notice.setPublicationDateTime(publicationDateTime);
        notice.setUserId(user.getId());
        try {
            noticeService.createNotice(notice);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "The notice has been create successfully");
        return "redirect:/notices";
    }

    @GetMapping("/notices/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        try {
            Notice notice = noticeService.findById(id);
            model.addAttribute("notice", notice);
            return "edit_notice_form";
        } catch (ServiceException e) {
            return "redirect:/notices";
        }
    }

    @PostMapping("/notices/update")
    public String updateNotice(Notice notice, RedirectAttributes redirectAttributes) {
        long now = System.currentTimeMillis();
        Timestamp publicationDateTime = new Timestamp(now);
        notice.setPublicationDateTime(publicationDateTime);
        try {
            noticeService.createNotice(notice);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "The notice has been saved successfully");
        return "redirect:/notices";
    }

    @GetMapping("notices/delete/{id}")
    public String deleteNotice(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        noticeService.deleteNotice(id);
        redirectAttributes.addFlashAttribute(MESSAGE, "The notice (id=" + id + ") has been successfully deleted");
        return "redirect:/notices";
    }
}
