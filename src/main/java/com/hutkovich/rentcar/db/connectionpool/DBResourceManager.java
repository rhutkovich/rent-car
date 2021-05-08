package com.hutkovich.rentcar.db.connectionpool;

public class DBResourceManager {
    private static final DBResourceManager instance = new DBResourceManager();

    //private ResourceBundle = ResourceBundle.getBundle(db.props);...
    //TODO change res-s

    public static DBResourceManager getInstance() {
        return instance;
    }

    //TODO change getValue()!!
    public String getValue(String key) {
        //return bundle.getString(key);
        switch (key) {
            case "db.driver":
                return "com.mysql.jdbc.Driver"; //"oracle.jdbc.driver.OracleDriver";
            case "db.url":
                return "jdbc:mysql://127.0.0.1/rent_car?useUnicode=true&characterEncoding=utf-8"; //look to conspect!!
            case "db.user":
                return "user";
            case "db.password":
                return "pass";
            case "db.poolsize":
                return "5";
            default:
                return null;
        }
    }
}
