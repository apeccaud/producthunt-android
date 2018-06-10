package fr.ec.producthunt.data.database;

import android.provider.BaseColumns;

/**
 * @author Mohammed Boukadir  @:mohammed.boukadir@gmail.com
 */
public final class DataBaseContract {

  public static final String DATABASE_NAME = "database";
  public static final int DATABASE_VERSION = 3;

  public static final String TEXT_TYPE = " TEXT";
  public static final String COMM_SPA = ",";
  public static final String INTEGER_TYPE = " INTEGER";

  /**
   * Description de la table des Posts
   **/
  public static final class PostTable implements BaseColumns {

    public static final String TABLE_NAME = "post";

    public static final String ID_COLUMN = "id";
    public static final String TITLE_COLUMN = "title";
    public static final String SUBTITLE_COLUMN = "subtitle";
    public static final String IMAGE_URL_COLUMN = "imageurl";
    public static final String POST_URL_COLUMN = "postUrl";
    public static final String POST_COMMENTSCOUNT_COLUMN = "commentsCount";


    public static final String SQL_CREATE_POST_TABLE =
            "CREATE TABLE " + PostTable.TABLE_NAME + " (" +
                    PostTable.ID_COLUMN + INTEGER_TYPE + " PRIMARY KEY" + COMM_SPA +
                    PostTable.TITLE_COLUMN + TEXT_TYPE + COMM_SPA +
                    PostTable.SUBTITLE_COLUMN + TEXT_TYPE + COMM_SPA +
                    PostTable.IMAGE_URL_COLUMN + TEXT_TYPE + COMM_SPA +
                    PostTable.POST_URL_COLUMN + TEXT_TYPE + COMM_SPA +
                    PostTable.POST_COMMENTSCOUNT_COLUMN + TEXT_TYPE +
                    ")";

    public static final String SQL_DROP_POST_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static String[] PROJECTIONS = new String[]{
            ID_COLUMN,
            TITLE_COLUMN,
            SUBTITLE_COLUMN,
            IMAGE_URL_COLUMN,
            POST_URL_COLUMN,
            POST_COMMENTSCOUNT_COLUMN
    };
  }

  public static final class CollectionTable implements BaseColumns {

    public static final String TABLE_NAME = "collection";

    public static final String ID_COLUMN = "id";
    public static final String TITLE_COLUMN = "title";
    public static final String NAME_COLUMN = "name";
    public static final String BACKGROUND_IMAGE_URL_COLUMN = "imageurl";


    public static final String SQL_CREATE_COLLECTION_TABLE =
            "CREATE TABLE " + CollectionTable.TABLE_NAME + " (" +
                    ID_COLUMN + INTEGER_TYPE + " PRIMARY KEY" + COMM_SPA +
                    TITLE_COLUMN + TEXT_TYPE + COMM_SPA +
                    NAME_COLUMN + TEXT_TYPE + COMM_SPA +
                    BACKGROUND_IMAGE_URL_COLUMN + TEXT_TYPE +
                    ")";

    public static final String SQL_DROP_COLLECTION_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static String[] PROJECTIONS = new String[]{
            ID_COLUMN,
            TITLE_COLUMN,
            NAME_COLUMN,
            BACKGROUND_IMAGE_URL_COLUMN,
    };
  }

  public static final class CommentTable implements BaseColumns {

    public static final String TABLE_NAME = "comment";

    public static final String ID_COLUMN = "id";
    public static final String ID_POST_COLUMN = "post_id";
    public static final String CONTENT_COLUMN = "content";
    public static final String DATE_COLUMN = "date";
    public static final String AUTHOR_NAME_COLUMN = "author_name";
    public static final String AUTHOR_USERNAME_COLUMN = "author_username";
    public static final String AUTHOR_PROFIL_PIC_COLUMN = "author_profilpic";
    public static final String AUTHOR_HEADLINE_COLUMN = "author_headline";


    public static final String SQL_CREATE_COMMENT_TABLE =
            "CREATE TABLE " + CommentTable.TABLE_NAME + " (" +
                    ID_COLUMN + INTEGER_TYPE + " PRIMARY KEY" + COMM_SPA +
                    ID_POST_COLUMN + TEXT_TYPE + COMM_SPA +
                    CONTENT_COLUMN + TEXT_TYPE + COMM_SPA +
                    DATE_COLUMN + TEXT_TYPE + COMM_SPA +
                    AUTHOR_NAME_COLUMN + TEXT_TYPE + COMM_SPA +
                    AUTHOR_USERNAME_COLUMN + TEXT_TYPE + COMM_SPA +
                    AUTHOR_PROFIL_PIC_COLUMN + TEXT_TYPE + COMM_SPA +
                    AUTHOR_HEADLINE_COLUMN + TEXT_TYPE +
                    ")";

    public static final String SQL_DROP_COMMENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static String[] PROJECTIONS = new String[]{
            ID_COLUMN,
            ID_POST_COLUMN,
            CONTENT_COLUMN,
            DATE_COLUMN,
            AUTHOR_NAME_COLUMN,
            AUTHOR_USERNAME_COLUMN,
            AUTHOR_PROFIL_PIC_COLUMN,
            AUTHOR_HEADLINE_COLUMN
    };
  }
}
