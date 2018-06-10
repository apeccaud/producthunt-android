package fr.ec.producthunt.data.database;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.ec.producthunt.data.model.Comment;


/**
 * Created by alex59493 on 10/06/2018.
 */

public class CommentDao {
    private final ProductHuntDbHelper productHuntDbHelper;

    public CommentDao(ProductHuntDbHelper productHuntDbHelper) {
        this.productHuntDbHelper = productHuntDbHelper;
    }

    public long save(Comment comment) {
        return productHuntDbHelper.getWritableDatabase()
                .replace(DataBaseContract.CommentTable.TABLE_NAME, null, comment.toContentValues());
    }

    public List<Comment> retrieveComments() {


        Cursor cursor = productHuntDbHelper.getReadableDatabase()
                .query(DataBaseContract.CommentTable.TABLE_NAME,
                        DataBaseContract.CommentTable.PROJECTIONS,
                        null, null, null, null,
                        DataBaseContract.CommentTable.DATE_COLUMN + " DESC");

        List<Comment> comments = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            do {

                Comment comment = new Comment();

                comment.setId(cursor.getInt(0));
                comment.setPostId(cursor.getInt(1));
                comment.setContent(cursor.getString(2));
                comment.setDate(cursor.getString(3));
                comment.setAuthorName(cursor.getString(4));
                comment.setAuthorUsername(cursor.getString(5));
                comment.setAuthorProfilPicUrl(cursor.getString(6));
                comment.setAuthorHeadline(cursor.getString(7));
                comments.add(comment);


            } while (cursor.moveToNext());
        }

        cursor.close();

        return comments;
    }
}
