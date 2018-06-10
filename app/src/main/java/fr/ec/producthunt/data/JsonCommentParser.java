package fr.ec.producthunt.data;

import android.util.Log;

import fr.ec.producthunt.data.model.Comment;
import fr.ec.producthunt.data.model.Post;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author alex59493
 */
public class JsonCommentParser {

    public  List<Comment> jsonToComments(String json) {

        try {

            JSONObject commentsResponse = new JSONObject(json);
            JSONArray commentsJson = commentsResponse.getJSONArray("comments");

            int size = commentsJson.length();
            ArrayList<Comment> comments = new ArrayList(size);

            for (int i = 0; i < commentsJson.length(); i++) {
                JSONObject commentJson = (JSONObject) commentsJson.get(i);
                comments.add(jsonToComment(commentJson));
            }


            return comments;
        } catch (JSONException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private  Comment jsonToComment(JSONObject commentJson) throws JSONException {
        Comment comment = new Comment();
        comment.setId(commentJson.getInt("id"));
        comment.setPostId(commentJson.getInt("post_id"));
        comment.setContent(commentJson.getString("body"));
        comment.setDate(commentJson.getString("created_at"));
        comment.setAuthorName(commentJson.getJSONObject("user").getString("name"));
        comment.setAuthorUsername(commentJson.getJSONObject("user").getString("username"));
        comment.setAuthorHeadline(commentJson.getJSONObject("user").getString("headline"));
        comment.setAuthorProfilPicUrl(commentJson.getJSONObject("user").getJSONObject("image_url").getString("original"));

        return comment;
    }
}
