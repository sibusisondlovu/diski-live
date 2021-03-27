package za.co.jaspa.diskilive.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import za.co.jaspa.diskilive.R;
import za.co.jaspa.diskilive.adapters.MatchCenterAdapter;
import za.co.jaspa.diskilive.models.Match;
import za.co.jaspa.diskilive.models.Team;


public class MatchCenterFragment extends Fragment {

    private static final String TAG = "MatchCenter";
    private String dateFrom;
    private String dateTo;
    private String apiKey;
    private int season_id;
    private static String BASE_API_URL ="https://app.sportdataapi.com/api/v1/soccer/matches?";

    private RecyclerView recyclerView;
    private List<Match> matchList;
    private MatchCenterAdapter matchCenterAdapter;

    public MatchCenterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_center, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        matchList = new ArrayList<>();
        initViews();

        extractMatches();
    }

    private void extractMatches() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        dateFrom = "2021-04-03";
        dateTo = "2021-04-09";
        apiKey = "a962e5f0-8bdf-11eb-82a3-052d6b3eb782";
        season_id = 1626;

        String url = BASE_API_URL + "apikey=" + apiKey + "&season_id=" + season_id + "&date_from" +
                             "=" + dateFrom + "&date_to=" + dateTo;

        JsonObjectRequest root = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject matchObject = data.getJSONObject(i);


                        Match match = new Match();

                        JSONObject homeTeamObject = matchObject.getJSONObject("home_team");
                        JSONObject awayTeamObject = matchObject.getJSONObject("away_team");

                        match.setHome_team(homeTeamObject.getString("name"));
                        match.setAway_team(awayTeamObject.getString("name"));
                        match.setHome_team_logo(homeTeamObject.getString("logo"));
                        match.setAway_team_logo(awayTeamObject.getString("logo"));
                        match.setMatch_start(matchObject.getString("match_start"));
                        matchList.add(match);

                        Log.d(TAG,
                                "Match Number " + i + matchList.get(i).getHome_team() + " vs " + matchList.get(i).getAway_team());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "onResponse: This is an error " +  e.getMessage());
                }

                matchCenterAdapter = new MatchCenterAdapter(matchList,getContext());
                recyclerView.setAdapter(matchCenterAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(root);
    }

    private void initViews() {

        if (getActivity() !=null) {
            recyclerView = getActivity().findViewById(R.id.fragment_match_center_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
}