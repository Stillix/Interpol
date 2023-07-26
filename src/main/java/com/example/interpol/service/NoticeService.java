package com.example.interpol.service;

import com.example.interpol.model.Notice;
import com.example.interpol.repository.NoticeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    public Optional<Notice> findById(Long noticeId) {
        return noticeRepository.findById(noticeId);
    }


    public Optional<Notice> findNoticeByUserId(Long userId) {
        return noticeRepository.findById(userId);
    }
}
