package com.example.interpol.service;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.Notice;

import java.util.List;


public interface NoticeService {

    public Notice createNotice(Notice notice) throws ServiceException;

    public void deleteNotice(Long noticeId);

    public List<Notice> findAll();

    public Notice findById(Long noticeId) throws ServiceException;

    List<Notice> findNoticesByUserId(Long userId);
}
