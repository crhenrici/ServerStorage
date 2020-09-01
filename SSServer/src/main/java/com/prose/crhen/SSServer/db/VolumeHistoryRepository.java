package com.prose.crhen.SSServer.db;

import com.prose.crhen.SSServer.model.Volume;
import com.prose.crhen.SSServer.model.VolumeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolumeHistoryRepository extends JpaRepository<VolumeHistory, Long> {

    public List<VolumeHistory> findByVolume(Volume volume);
}
