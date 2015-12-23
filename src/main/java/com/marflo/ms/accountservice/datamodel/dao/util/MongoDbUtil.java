package com.marflo.ms.accountservice.datamodel.dao.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

public class MongoDbUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbUtil.class);

    private static final int port = 27017;
    private static final String host = "localhost";
    private static MongoClient mongo = null;

    public static MongoClient getMongoClient() {
        if (mongo == null) {
            try {
                mongo = new MongoClient(host, port);
                LOGGER.debug("New Mongo created with [{}] and [{}]", host, port);
            } catch (UnknownHostException e) {
                LOGGER.error(e.getMessage());
            } catch (MongoException e2) {
                LOGGER.error(e2.getMessage());
            }
        }
        return mongo;
    }
}
