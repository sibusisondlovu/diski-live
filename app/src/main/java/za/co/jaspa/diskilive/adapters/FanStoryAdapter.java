package za.co.jaspa.diskilive.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import za.co.jaspa.diskilive.R;
import za.co.jaspa.diskilive.models.FanStory;

public class FanStoryAdapter  extends RecyclerView.Adapter<FanStoryAdapter.MyViewHolder> {
    private List<FanStory> fanStoryList;

    private Context context;

    public FanStoryAdapter(List<FanStory> fanStoryList, Context context) {

        this.context = context;

        this.fanStoryList = fanStoryList;
    }

    @NonNull
    @Override
    public FanStoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_fan_story_post,
                viewGroup,
                false);

        return new FanStoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FanStoryAdapter.MyViewHolder myViewHolder, final int position) {
        //fanUsername = itemView.findViewById(R.id.row_fan_post_tv_username);
        //comments = itemView.findViewById(R.id.row_fan_post_tv_comments);
        //shares = itemView.findViewById(R.id.row_fan_post_tv_share);
        //upvote = itemView.findViewById(R.id.row_fan_post_tv_upvote);
        //downvote = itemView.findViewById(R.id.row_fan_post_tv_downvote);
        //title = itemView.findViewById(R.id.row_fan_post_tv_title);
        //body = itemView.findViewById(R.id.row_fan_post_tv_body);
        //duration = itemView.findViewById(R.id.row_fan_post_tv_duration);
        myViewHolder.fanUsername.setText(fanStoryList.get(position).getFanUsername());
        myViewHolder.title.setText(fanStoryList.get(position).getTitle());
        myViewHolder.body.setText(fanStoryList.get(position).getBody());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent = new Intent(context, EventDetailsActivity.class);

               // intent.putExtra("EVENT",eventList.get(position));

             //   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

             //   context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return fanStoryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fanUsername, comments, shares, upvote, downvote, title, body, duration;

        ImageView media;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            fanUsername = itemView.findViewById(R.id.row_fan_post_tv_username);
            comments = itemView.findViewById(R.id.row_fan_post_tv_comments);
            shares = itemView.findViewById(R.id.row_fan_post_tv_share);
            upvote = itemView.findViewById(R.id.row_fan_post_tv_upvote);
            downvote = itemView.findViewById(R.id.row_fan_post_tv_downvote);
            title = itemView.findViewById(R.id.row_fan_post_tv_title);
            body = itemView.findViewById(R.id.row_fan_post_tv_body);
            duration = itemView.findViewById(R.id.row_fan_post_tv_duration);

        }
    }
}
