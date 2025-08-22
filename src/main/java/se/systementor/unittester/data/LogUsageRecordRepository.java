package se.systementor.unittester.data;


import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

public interface LogUsageRecordRepository extends CrudRepository<LogUsageRecord, Long> {
}