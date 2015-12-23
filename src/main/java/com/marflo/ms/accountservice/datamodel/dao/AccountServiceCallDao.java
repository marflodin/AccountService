package com.marflo.ms.accountservice.datamodel.dao;

import com.marflo.ms.accountservice.datamodel.entity.AccountServiceCallDocument;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

public class AccountServiceCallDao extends BasicDAO<AccountServiceCallDocument, ObjectId> {

    protected AccountServiceCallDao(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }
}
