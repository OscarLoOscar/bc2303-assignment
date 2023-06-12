package com.codewave.project.projectcryptochannel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codewave.project.projectcryptochannel.model.ChannelTrans;

public interface ChannelTransRepository extends JpaRepository<ChannelTrans, Long> {

  List<ChannelTrans> findBySourceAppAndTranType(String source, String tranType);

}
