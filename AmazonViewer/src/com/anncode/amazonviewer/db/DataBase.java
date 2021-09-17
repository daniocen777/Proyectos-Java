package com.anncode.amazonviewer.db;

public class DataBase {
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String DB = "amazonviewer";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    // Mapeando tabla "movie"
    public static final String TMOVIE = "movie";
    public static final String TMOVIE_ID = "id";
    public static final String TMOVIE_TITLE = "title";
    public static final String TMOVIE_GENRE = "genre";
    public static final String TMOVIE_CREATOR = "creator";
    public static final String TMOVIE_DURATION = "duration";
    public static final String TMOVIE_YEAR = "year";
    // Mapeando tabla "user"
    public static final String TUSER = "user";
    public static final String TUSER_ID = "id";
    public static final String TUSER_NAME = "name";
    // Mapeando tabla "material"
    public static final String TMATERIAL = "material";
    public static final String TMATERIAL_ID = "id";
    public static final String TMATERIAL_NAME = "name";
}
