package com.example.myapplication;




import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.StarAdapter;
import com.example.myapplication.beans.Star;
import com.example.myapplication.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void init() {
        service.create(new Star("kate bosworth", "https://static1.puretrend.com/articles/1/79/22/1/@/904464-kate-bosworth-fan-du-teint-zero-580x0-2.jpg", 3.5f));
        service.create(new Star("george clooney", "https://mobile-img.lpcdn.ca/v2/924x/r3996/174ab413fad93f6db65e20692b57554f.jpg", 3));
        service.create(new Star("michelle rodriguez", "https://resize-parismatch.lanmedia.fr/img/var/news/storage/images/paris-match/people-a-z/michelle-rodriguez/6145316-9-fre-FR/Michelle-Rodriguez.jpg", 5));
        service.create(new Star("george clooney", "https://mobile-img.lpcdn.ca/v2/924x/r3996/174ab413fad93f6db65e20692b57554f.jpg", 1));
        service.create(new Star("louise bouroin", "https://offline-online.ch/wp-content/uploads/2021/02/louisebourgoin-1428x2000.jpg", 5));
        service.create(new Star("louise bouroin", "https://offline-online.ch/wp-content/uploads/2021/02/louisebourgoin-1428x2000.jpg", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }


}
