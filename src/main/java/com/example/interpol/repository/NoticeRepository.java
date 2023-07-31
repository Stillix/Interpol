package com.example.interpol.repository;

import com.example.interpol.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice, Long> {


    List<Notice> findAllByUserId(Long userId);
}
