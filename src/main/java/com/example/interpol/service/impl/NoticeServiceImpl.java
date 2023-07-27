package com.example.interpol.service.impl;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.Notice;
import com.example.interpol.model.User;
import com.example.interpol.repository.NoticeRepository;
import com.example.interpol.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeServiceImpl implements NoticeService {
    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Notice findById(Long noticeId) throws ServiceException {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        if (notice.isPresent()) {
            return notice.get();
        }
        throw new ServiceException("Failed find notice by id");
    }
    @Override
    public Optional<Notice> findNoticeByUserId(Long userId) {
        return noticeRepository.findById(userId);
    }
}
