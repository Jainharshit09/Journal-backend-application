package com.digest.journalApp.repository;

import com.digest.journalApp.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface configJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}
