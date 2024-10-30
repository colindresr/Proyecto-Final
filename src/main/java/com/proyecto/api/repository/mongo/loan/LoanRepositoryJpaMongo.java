package com.proyecto.api.repository.mongo.loan;

import com.proyecto.api.modelo.mongo.LoanMongo;
import com.proyecto.api.modelo.mongo.LoanMongoId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepositoryJpaMongo extends MongoRepository<LoanMongo, LoanMongoId> {
}
