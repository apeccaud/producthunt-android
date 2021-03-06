package fr.ec.producthunt.data;

import android.app.Application;
import android.util.Log;

import fr.ec.producthunt.data.database.CollectionDao;
import fr.ec.producthunt.data.database.CommentDao;
import fr.ec.producthunt.data.database.PostDao;
import fr.ec.producthunt.data.database.ProductHuntDbHelper;
import fr.ec.producthunt.data.model.Collection;
import fr.ec.producthunt.data.model.Comment;
import fr.ec.producthunt.data.model.Post;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DataProvider {

  // Get all posts sorted by created_at parameter in desc order (newest first)
  public static final String POST_API_END_POINT =
      "https://api.producthunt.com/v1/posts?access_token=46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb&sort_by=created_at&order=desc";

  public static final String COLLECTION_API_END_POINT =
          "https://api.producthunt.com/v1/collections?access_token=46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb";

  private JsonPostParser jsonPostParser = new JsonPostParser();
  private JsonCollectionParser jsonCollectionParser = new JsonCollectionParser();
  private JsonCommentParser jsonCommentParser = new JsonCommentParser();

  private final PostDao postDao;
  private final CollectionDao collectionDao;
  private final CommentDao commentDao;

  private static DataProvider dataProvider;

  public static DataProvider getInstance(Application application) {

    if (dataProvider == null) {
      dataProvider = new DataProvider(ProductHuntDbHelper.getInstance(application));
    }
    return dataProvider;
  }

  public DataProvider(ProductHuntDbHelper dbHelper) {
    postDao = new PostDao(dbHelper);
    collectionDao = new CollectionDao(dbHelper);
    commentDao = new CommentDao(dbHelper);
  }

  public static String getCommetsForPostId(Long postId) {
    return "https://api.producthunt.com/v1/posts/"+postId+"/comments?access_token=46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb";
  }

  private String getStuffFromWeb(String apiUrl) {

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;

    // Contiendra la réponse JSON brute sous forme de String .
    String posts = null;

    try {
      // Construire l' URL de l'API ProductHunt
      URL url = new URL(apiUrl);

      // Creer de la requête http vers  l'API ProductHunt , et ouvrir la connexion
      urlConnection = (HttpURLConnection) url.openConnection();
      urlConnection.setRequestMethod("GET");
      urlConnection.connect();

      // Lire le  input stream et le convertir String
      InputStream inputStream = urlConnection.getInputStream();
      StringBuffer buffer = new StringBuffer();
      if (inputStream == null) {
        // Nothing to do.
        return null;
      }
      reader = new BufferedReader(new InputStreamReader(inputStream));

      String line;
      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }

      if (buffer.length() == 0) {
        // Si le stream est vide, on revoie null;
        return null;
      }
      posts = buffer.toString();
    } catch (IOException e) {
      Log.e(TAG, "Error ", e);
      return null;
    } finally {
      if (urlConnection != null) {
        urlConnection.disconnect();
      }
      if (reader != null) {
        try {
          reader.close();
        } catch (final IOException e) {
          Log.e(TAG, "Error closing stream", e);
        }
      }
    }

    return posts;
  }

  public List<Post> getPostsFromDatabase() {
    return postDao.retrievePosts();
  }

  public List<Collection> getCollectionsFromDatabase() {
    return collectionDao.retrieveCollections();
  }

  public List<Comment> getCommentsFromDatabase() {
    return commentDao.retrieveComments();
  }

  public Boolean syncPost() {
    List<Post> list = jsonPostParser.jsonToPosts(getStuffFromWeb(POST_API_END_POINT));

    int nb = 0;
    for (Post post : list) {
      postDao.save(post);
      nb++;
    }
    return nb > 0;
  }

  public Boolean syncCollection() {
    List<Collection> list = jsonCollectionParser.jsonToCollections(getStuffFromWeb(COLLECTION_API_END_POINT));

    int nb = 0;
    for (Collection collection : list) {
      collectionDao.save(collection);
      nb++;
    }
    return nb > 0;
  }

  public Boolean syncComments(Long postId) {
    List<Comment> list = jsonCommentParser.jsonToComments(getStuffFromWeb(getCommetsForPostId(postId)));
    int nb = 0;
    for (Comment comment : list) {
      commentDao.save(comment);
      nb++;
    }
    return nb > 0;
  }
}

