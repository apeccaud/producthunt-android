package fr.ec.producthunt.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import fr.ec.producthunt.R;
import fr.ec.producthunt.data.model.Post;
import java.util.Collections;
import java.util.List;

public class PostAdapter extends BaseAdapter {

  private List<Post> dataSource = Collections.emptyList();
  private PostsFragments.Callback callback;

  public PostAdapter(PostsFragments.Callback callback) {
    this.callback = callback;
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
      int layoutId = position == 0 ? R.layout.item_highlight : R.layout.item;
      convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

      viewHolder = new ViewHolder();
      viewHolder.title = convertView.findViewById(R.id.title);
      viewHolder.subTitle = convertView.findViewById(R.id.sub_title);
      viewHolder.postImage = convertView.findViewById(R.id.img_product);
      viewHolder.commentsCount = convertView.findViewById(R.id.comments_count);

      convertView.setTag(viewHolder);
    } else {

      viewHolder = (ViewHolder) convertView.getTag();
    }

    final Post post = dataSource.get(position);
    viewHolder.title.setText(post.getTitle());
    viewHolder.subTitle.setText(post.getSubTitle());
    viewHolder.commentsCount.setText(post.getcommentsCount());
    viewHolder.commentsCount.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        callback.onClickComment(post);
      }
    });

    Picasso.with(parent.getContext())
        .load(post.getImageUrl())
        .centerCrop()
        .fit()
        .into(viewHolder.postImage);

    return convertView;
  }

  public void showPosts(List<Post> posts) {
    dataSource = posts;

    notifyDataSetChanged();
  }

  private static class ViewHolder {
    TextView title;
    TextView subTitle;
    ImageView postImage;
    TextView commentsCount;
  }
}
