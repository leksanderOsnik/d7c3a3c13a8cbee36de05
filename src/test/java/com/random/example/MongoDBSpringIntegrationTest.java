package com.random.example;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest(properties = {"spring.mongodb.embedded.version=4.0.21"})
@ExtendWith(SpringExtension.class)
public class MongoDBSpringIntegrationTest {

    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {
        DBObject objectSave = BasicDBObjectBuilder.start()
                .add("name", "John")
                .add("age", 30)
                .add("date", LocalDate.now())
                .get();

        mongoTemplate.save(objectSave, "collection");

        assertThat(mongoTemplate.findAll(DBObject.class,"collection"))
                .extracting("name").containsOnly("John");
    }
}
