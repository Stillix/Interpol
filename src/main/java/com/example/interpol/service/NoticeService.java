package com.example.interpol.service;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.Notice;

import java.util.List;
import java.util.Optional;


public interface NoticeService {

    public Notice createNotice(Notice notice);

    public void deleteNotice(Long noticeId);

    public List<Notice> findAll();

    public Notice findById(Long noticeId) throws ServiceException;


    public Optional<Notice> findNoticeByUserId(Long userId);
}
