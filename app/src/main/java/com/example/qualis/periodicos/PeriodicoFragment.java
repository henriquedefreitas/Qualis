package com.example.qualis.periodicos;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.qualis.R;

import java.util.List;

public class PeriodicoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public PeriodicoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_periodico, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.sections_recycler_view);
        final PeriodicoListAdapter adapter = new PeriodicoListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        PeriodicoViewModel periodicoViewModel = new ViewModelProvider(this).get(PeriodicoViewModel.class);
        periodicoViewModel.getAllPeriodicos().observe(getActivity(), words -> {
            // Update the cached copy of the words in the adapter.
            adapter.setPeriodicos(words);
        });

        Spinner spinner = v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);

                if (selectedCategory.equals("Todos")) {
                    List<Periodico> allPeriodicos = periodicoViewModel.getAllPeriodicos().getValue();

                    adapter.setPeriodicos(allPeriodicos);
                } else {
                    List<Periodico> filteredPeriodicos = periodicoViewModel.filterPeriodicosByCategory(selectedCategory);

                    adapter.setPeriodicos(filteredPeriodicos);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });



//        SearchView searchView = v.findViewById(R.id.search_view);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Handle the search query here
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Filter the list of Periodico objects based on the search query
//                // Get the selected category and search query from the spinner and search view, respectively
//                String selectedCategory = (String) spinner.getSelectedItem();
//                String searchQuery = searchView.getQuery().toString();
//
//                // Filter the list of periodicos by the selected category and search query
//                List<Periodico> filteredPeriodicos = periodicoViewModel.searchAndFilterPeriodicos(searchQuery, selectedCategory);
//
//                // Update the list in the RecyclerView adapter with the filtered list
//                adapter.setPeriodicos(filteredPeriodicos);
//
//                // Check if the LiveData object is not null before accessing its data
//                // Use the post method of the RecyclerView object to update the adapter on the main thread
//                // after the background thread has finished filtering the list of periodicos
//                recyclerView.post(() -> adapter.setPeriodicos(filteredPeriodicos));
//
//
//                return false;
//            }
//        });

        return v;
    }
}