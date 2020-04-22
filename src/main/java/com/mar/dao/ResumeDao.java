package com.mar.dao;

import com.mar.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 刘劲
 * @Date: 2020/4/22 12:25
 */
public interface ResumeDao extends JpaRepository<Resume, Integer>, JpaSpecificationExecutor<Resume> {
}
