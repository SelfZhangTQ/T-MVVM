package com.code.mvvm.config;

/**
 * @author：tqzhang on 18/7/27 16:19
 */
public class Constants {
    public static final String HOME_TAG = "home";
    public static final String WORK_TAG = "work";
    public static final String VIDEO_TAG = "video";
    public static final String MINE_TAG = "mine";

    public final static String MATERIAL = "Material";
    public final static String FOLLOW_DRAW = "FollowDraw";
    public final static String ARTICLE = "Article";
    public final static String LIVE = "Live";
    public final static String BOOK = "Book";
    public final static String DYNAMIC = "Dynamic";
    public final static String ACTIVITY = "Activity";
    public final static String QA = "Qa";

    public final static String COURSE_ID = "course_id";
    public final static String CORRECT_ID = "correct_id";
    public final static String F_CATALOG_ID = "f_catalog_id";
    public final static String S_CATALOG = "s_catalog";
    public final static String SUB_ID = "sub_id";


    public static final String TYPE_CORRECT = "1";
    public static final String TYPE_WORK = "2";
    public static final String TYPE_MATERIAL_SUBJECT = "3";
    public static final String TYPE_ARTICLE = "4";
    public static final String TYPE_FOLLOW_DRAW = "5";
    public static final String TYPE_LIVE = "6";
    public static final String TYPE_LESSON = "7";

    public static final String PAGE_RN = "20";

    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_CACHE_MEMORY_SIZE = MAX_HEAP_SIZE / 4;
    public static final int MAX_CACHE_DISK_SIZE = 250 * 1024 * 1024;

    public static final Object EVENT_KEY_ARTICLE_STATE = "EVENT_KEY_ARTICLE_STATE";
    public static final Object EVENT_KEY_ARTICLE_LIST_STATE = "EVENT_KEY_ARTICLE_LIST_STATE";
    public static final Object EVENT_KEY_WORK = "EVENT_KEY_WORK";
    public static final Object EVENT_KEY_WORK_STATE = "EVENT_KEY_WORK_STATE";
}
