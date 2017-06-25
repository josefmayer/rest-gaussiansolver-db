package dbClients;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

/**
 * Created by Josef Mayer on 23.06.2017.
 */
public class MongoDBClient {

    public MongoDBClient(){
        mongoDBinit();
    }

    public MongoCollection<Document> collection;



    void mongoDBinit() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("mydb");
        collection = database.getCollection("test15");
    }

    public void insert(String jsonStringInputDocument, String jsonStringResult){
        Document document = new Document();

        document.put("requestTime", new Date());
        document.put("document", jsonStringInputDocument);
        document.put("result", jsonStringResult);
        collection.insertOne(document);
    }


    public StringBuffer getAll(){
        MongoCursor<Document> cursor = collection.find().iterator();

        StringBuffer sb = new StringBuffer();
        String str;
        String str2;

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                sb.append("requestTime ");
                sb.append(doc.get("requestTime").toString());
                sb.append("\n");
                sb.append("document ");
                sb.append(doc.get("document").toString());
                sb.append("\n");
                sb.append("result ");
                sb.append(doc.get("result").toString());
                sb.append("\n");

                sb.append("\n");
            }
        } finally {
            cursor.close();
        }
        return sb;
    }

    public StringBuffer getAll_2(){
        MongoCursor<Document> cursor = collection.find().iterator();

        StringBuffer sb = new StringBuffer();
        String str;

        try {
            while (cursor.hasNext()) {
                //Document doc = cursor.next();
                //str = doc.toJson();
                //sb.append(str);
                sb.append(cursor.next().toJson());
                sb.append("\n");
            }
        } finally {
            cursor.close();
        }
        return sb;
    }

}
