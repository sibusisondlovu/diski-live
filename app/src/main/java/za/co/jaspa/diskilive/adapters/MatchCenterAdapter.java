package za.co.jaspa.diskilive.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import za.co.jaspa.diskilive.R;
import za.co.jaspa.diskilive.models.Match;

public class MatchCenterAdapter  extends RecyclerView.Adapter<MatchCenterAdapter.MyViewHolder> {
    private List<Match> matchesList;

    private Context context;

    public MatchCenterAdapter(List<Match> matchesList, Context context) {
        this.context = context;
        this.matchesList = matchesList;
    }

    @NonNull
    @Override
    public MatchCenterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_match_center,
                viewGroup,
                false);

        return new MatchCenterAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchCenterAdapter.MyViewHolder myViewHolder, final int position) {

        myViewHolder.homeTeam.setText(matchesList.get(position).getHome_team());
        myViewHolder.awayTeam.setText(matchesList.get(position).getAway_team());
        myViewHolder.kickOff.setText(matchesList.get(position).getMatch_start());
        Picasso.get().load(matchesList.get(position).getHome_team_logo()).into(myViewHolder.homeTeamBadge);
        Picasso.get().load(matchesList.get(position).getAway_team_logo()).into(myViewHolder.awayTeamBadge);

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
        return matchesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView homeTeam, awayTeam, league, kickOff;
        ImageView homeTeamBadge, awayTeamBadge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeam = itemView.findViewById(R.id.row_match_center_tvHomeTeam);
            awayTeam = itemView.findViewById(R.id.row_match_center_tvAwayTeam);
            league = itemView.findViewById(R.id.row_match_center_tvLeague);
            kickOff = itemView.findViewById(R.id.row_match_center_tvTime);
            homeTeamBadge = itemView.findViewById(R.id.row_match_center_ivHomeTeamBadge);
            awayTeamBadge = itemView.findViewById(R.id.row_match_center_ivAwayTeamBadge);
        }
    }
}
