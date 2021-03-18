package za.co.jaspa.diskilive.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import za.co.jaspa.diskilive.R;
import za.co.jaspa.diskilive.activities.PostFanStoryActivity;
import za.co.jaspa.diskilive.adapters.FanStoryAdapter;
import za.co.jaspa.diskilive.models.FanStory;


public class FanStoriesFragment extends Fragment {

    private RecyclerView recyclerView;

    private TextView message;

    private LinearLayout noStories, loadingView;

    public FanStoriesFragment() {
        // Required empty public constructor
    }

    private void initViews(){
        recyclerView = getActivity().findViewById(R.id.fragment_fan_stories_recycler_view);
        noStories = getActivity().findViewById(R.id.fragment_fan_stories_no_data_layout);
        loadingView = getActivity().findViewById(R.id.fragment_fan_stories_loading);
        message = getActivity().findViewById(R.id.row_fan_post_tv_message);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        fetchFanStories();

        Button btnPostFanStory = getActivity().findViewById(R.id.fragment_fan_stories_btn_post_story);
        btnPostFanStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostFanStoryActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fan_stories, container, false);
    }

    private void fetchFanStories(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("fan_stories")
                .orderBy("createdAt", Query.Direction.DESCENDING).limit(10)
                .get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        recyclerView.setVisibility(View.GONE);
                        message.setVisibility(View.VISIBLE);
                        loadingView.setVisibility(View.GONE);
                        noStories.setVisibility(View.GONE);

                        message.setText(e.getMessage());
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (queryDocumentSnapshots.isEmpty()) {
                            //no records to show
                            recyclerView.setVisibility(View.GONE);
                            message.setVisibility(View.GONE);
                            loadingView.setVisibility(View.GONE);
                            noStories.setVisibility(View.VISIBLE);

                        }else{
                            List<FanStory> fanStoryArrayList = new ArrayList<>();

                            for (QueryDocumentSnapshot doc: queryDocumentSnapshots) {

                                FanStory fanStory = doc.toObject(FanStory.class);

                                fanStoryArrayList.add(fanStory);

                            }

                            FanStoryAdapter adapter = new FanStoryAdapter(fanStoryArrayList,getContext());

                            recyclerView.setAdapter(adapter);

                            adapter.notifyDataSetChanged();

                            recyclerView.setVisibility(View.VISIBLE);
                            message.setVisibility(View.GONE);
                            loadingView.setVisibility(View.GONE);
                            noStories.setVisibility(View.GONE);
                        }
                    }
                });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() !=null) {
            initViews();
        }

    }
}