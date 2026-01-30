package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.entity.DriveLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriveLogRepository extends JpaRepository<DriveLogEntity, Long>{

}
