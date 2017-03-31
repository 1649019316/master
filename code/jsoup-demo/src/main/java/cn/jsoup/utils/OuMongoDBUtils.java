package cn.jsoup.utils;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Created by oahzuw@gmail.com on 15-11-24.
 */
public class OuMongoDBUtils {

    private static final int PORT;
    private static final String DBURL;
//    private static final String DBNAME;
//    private static final String COLLECTIONNAME;

    private static Mongo mongo = null;
    private static DB db = null;

    private Mongo mongO = null;
    private DB dB = null;

    static {
        DBURL = ResourceBundle.getBundle("db").getString("mongodb.dburl");
        PORT = Integer.parseInt(ResourceBundle.getBundle("db").getString("mongodb.port"));
//        DBNAME = ResourceBundle.getBundle("jdbc").getString("mongodb.dbname");
//        COLLECTIONNAME = Integer.parseInt(ResourceBundle.getBundle("jdbc").getString("mongodb.collectionname"));
    }

    /**
     * 初始 跨表
     */
    public static void init(String DBNAME) {
        try {
            mongo = new Mongo(DBURL, PORT);
        } catch (MongoException e) {
            System.out.println(e.toString());
        }

        if (null != mongo) {
            db = mongo.getDB(DBNAME);
        }
    }

    public static Mongo getMongo(String DBNAME) {
        if (null == mongo) {
            init(DBNAME);
        }

        return mongo;
    }

    /**
     * 获取DB
     *
     * @return DB
     */
    public static DB getDB(String DBNAME) {
        if (null == mongo) {
            init(DBNAME);
        }

        return db;
    }

    /**
     * 获取User Collection
     *
     * @return DBCollection
     */
    public static DBCollection getCollection(String DBNAME, String COLLECTIONNAME) {
        if (null == db) {
            db = getDB(DBNAME);
        }

        if (null != db) {
            return db.getCollection(COLLECTIONNAME);
        }

        return null;
    }

    // 关闭数据库
    public static boolean close(DBCollection dbCollection) {
        try {
            dbCollection.getDB().getMongo().close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean close(DB db) {
        try {
            db.getMongo().close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Mongo getMongO() {
        return mongO;
    }

    public void setMongO(Mongo mongO) {
        this.mongO = mongO;
    }

    public DB getdB() {
        return dB;
    }

    public void setdB(DB dB) {
        this.dB = dB;
    }

    /**
     * 初始  跨库 跨表
     */
    public void INIT(String dbName) {
        try {
            this.mongO = new Mongo(DBURL, PORT);
        } catch (MongoException e) {
            System.out.println(e.toString());
        }

        if (null != mongO) {
            this.dB = mongO.getDB(dbName);
        }
    }

    public Mongo GetMongo(String dbName) {
        if (null == this.mongO) {
            this.INIT(dbName);
        }

        return mongO;
    }

    /**
     * 获取DB
     *
     * @return DB
     */
    public DB GetDB(String dbName) {

//        if (StringUtils.isNoneBlank(dbName)) {
//            MongoDatabase database = mongoClient.getDatabase(dbName);
//            return database;
//        }
//        return null;


        if (null == this.mongO) {
            this.INIT(dbName);
        }

        return dB;
    }

    /**
     * 获取User Collection
     *
     * @return DBCollection
     */
    public DBCollection GetCollection(String dbName, String collName) {

//        if (StringUtils.isNoneBlank(dbName) && StringUtils.isNoneBlank(collName)) {
//            return mongoClient.getDatabase(dbName).getCollection(collName);
//        }
//        return null;


        if (null == this.dB) {
            this.dB = GetDB(dbName);
        }

        if (null != this.dB) {
            return dB.getCollection(collName);
        }

        return null;
    }

}
