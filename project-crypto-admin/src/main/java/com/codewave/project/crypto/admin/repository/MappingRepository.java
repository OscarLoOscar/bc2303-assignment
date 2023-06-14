package com.codewave.project.crypto.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewave.project.crypto.admin.entity.MappingEntity;

public interface MappingRepository extends JpaRepository<MappingEntity, Long> {

  MappingEntity findBySourceAndTranType(String source, String tranType);

}
