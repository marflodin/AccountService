package com.marflo.ms.accountservice.datamodel.dao;

import com.marflo.ms.accountservice.datamodel.dao.util.MongoDbUtil;
import com.marflo.ms.accountservice.datamodel.entity.AccountServiceCallDocument;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;

import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class AccountServiceCallDaoTest {

    Morphia morphia;
    AccountServiceCallDao dao;

    @Before
    public void setup() {
        String dbName = "test";
        MongoClient mongoClient = MongoDbUtil.getMongoClient();
        morphia = new Morphia();
        morphia.map(AccountServiceCallDocument.class);
        dao = new AccountServiceCallDao(mongoClient, morphia, dbName);
    }

    @Test
    public void addNewRequestToDb() {
        long numberOfDocumentsBefore = dao.count();
        AccountServiceCallDocument documentToAdd = createNewDocument();
        Key<AccountServiceCallDocument> id = dao.save(documentToAdd);

        long numberOfDocumentsAfter = dao.count();
        assertEquals(numberOfDocumentsBefore + 1, numberOfDocumentsAfter);

        AccountServiceCallDocument foundDocument = dao.get((ObjectId) id.getId());
        assertEquals(foundDocument.getAccountNumber(), documentToAdd.getAccountNumber());
        assertEquals(foundDocument.getRequestTime(), documentToAdd.getRequestTime());
        assertEquals(foundDocument.getResponseCode(), documentToAdd.getResponseCode());
        assertEquals(foundDocument.getResponseTime(), documentToAdd.getResponseTime());
    }

    private AccountServiceCallDocument createNewDocument() {
        SecureRandom random = new SecureRandom();
        AccountServiceCallDocument document = new AccountServiceCallDocument();
        document.setAccountNumber(new BigInteger(130, random).toString(32));
        document.setRequestTime(new Date());
        document.setResponseCode(Response.Status.OK.getStatusCode());
        document.setResponseTime(ThreadLocalRandom.current().nextInt(0, 1000));
        return document;
    }
}