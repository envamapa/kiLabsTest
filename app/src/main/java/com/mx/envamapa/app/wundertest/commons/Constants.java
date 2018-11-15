package com.mx.envamapa.app.wundertest.commons;

public class Constants {

    public static final boolean ALLOWING_LOGS = true;
    public static String TAG_LOG = "WUNDER TEST";

    public static int SPLASH_TIME = 3000;
    public static int TIMEOUT = 20000;
    public static int RETRIES = 3;

    public static final String GET_CARS = "https://s3-us-west-2.amazonaws.com/wunderbucket/locations.json";

    public static final int CODE_REQUEST_PERMISSION = 1;

    public static String GOOD = "GOOD";
    public static String UNACCEPTABLE = "UNACCEPTABLE";

    public static int ITEM_COUNT = 15;

    public static Double INIT_LAT = 53.5582447;
    public static Double INIT_LON = 9.647645;
    public static float ZOOM = 10;
    
    /*********/
    public static final int PAGE_SIZE = 15;
    public static final long CACHE_SIZE_IN_MB = 10 * 1024 * 1024;
    public static final String API_KEY = "a100d8c9c095934a24a27212791b4398";
    public static final String GET_IMAGES = "https://api.flickr.com/services/rest/"
            + "?format=json&nojsoncallback=1&api_key=" + API_KEY;
    public static final String URL_SEARCH = "&method=flickr.photos.search&tags=mode&per_page=" + PAGE_SIZE + "&page=";
    public static final String URL_DETAIL = "&method=flickr.photos.getInfo&photo_id=";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_PHOTOS = "photos";


}
