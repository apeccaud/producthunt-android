package fr.ec.producthunt.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.ec.producthunt.R;
import fr.ec.producthunt.data.model.Comment;
import java.util.Collections;
import java.util.List;

public class CommentAdapter extends BaseAdapter {

    private List<Comment> dataSource = Collections.emptyList();

    public CommentAdapter() {
    }

    @Override public int getCount() {
        return dataSource.size();
    }

    @Override public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_comment_list, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.author = convertView.findViewById(R.id.author);
            viewHolder.authorusername = convertView.findViewById(R.id.authorusername);
            viewHolder.authorheadline = convertView.findViewById(R.id.authorheadline);
            viewHolder.content = convertView.findViewById(R.id.content);
            viewHolder.profilPic = convertView.findViewById(R.id.profilpic);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Comment comment = dataSource.get(position);
        viewHolder.author.setText(comment.getAuthorName());
        viewHolder.authorusername.setText(comment.getAuthorUsername());
        viewHolder.authorheadline.setText(comment.getAuthorHeadline());
        viewHolder.content.setText(comment.getContent());

//        Picasso.with(parent.getContext())
//                .load(comment.getAuthorProfilPicUrl())
//                .centerCrop()
//                .fit()
//                .transform(new CircleTransform())
//                .into(viewHolder.profilPic);

        return convertView;
    }

    public void showComments(List<Comment> comments) {
        dataSource = comments;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView author;
        TextView authorusername;
        TextView authorheadline;
        TextView content;
        ImageView profilPic;
    }
}
