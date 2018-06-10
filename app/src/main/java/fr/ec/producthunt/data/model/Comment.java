package fr.ec.producthunt.data.model;

import android.content.ContentValues;

import fr.ec.producthunt.data.database.DataBaseContract;

/**
 * Created by alex59493 on 10/06/2018.
 */

public class Comment {
    private long id;
    private long postId;
    private String content;
    private String date;
    private String authorName;
    private String authorUsername;
    private String authorProfilPicUrl;
    private String authorHeadline;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getAuthorProfilPicUrl() {
        return authorProfilPicUrl;
    }

    public void setAuthorProfilPicUrl(String authorProfilPicUrl) {
        this.authorProfilPicUrl = authorProfilPicUrl;
    }

    public String getAuthorHeadline() {
        return authorHeadline;
    }

    public void setAuthorHeadline(String authorHeadline) {
        this.authorHeadline = authorHeadline;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseContract.CommentTable.ID_COLUMN, id);
        contentValues.put(DataBaseContract.CommentTable.ID_POST_COLUMN, postId);
        contentValues.put(DataBaseContract.CommentTable.CONTENT_COLUMN, content);
        contentValues.put(DataBaseContract.CommentTable.DATE_COLUMN, date);
        contentValues.put(DataBaseContract.CommentTable.AUTHOR_NAME_COLUMN, authorName);
        contentValues.put(DataBaseContract.CommentTable.AUTHOR_USERNAME_COLUMN, authorUsername);
        contentValues.put(DataBaseContract.CommentTable.AUTHOR_PROFIL_PIC_COLUMN, authorProfilPicUrl);
        contentValues.put(DataBaseContract.CommentTable.AUTHOR_HEADLINE_COLUMN, authorHeadline);
        return contentValues;
    }

}
