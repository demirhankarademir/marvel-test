package com.theappbusiness.marvel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.theappbusiness.marvel.database.Comic;
import com.theappbusiness.marvel.database.Creator;
import com.theappbusiness.marvel.database.DatabaseManager;

import java.util.List;

/**
 * Created by demir on 7.04.2017.
 */

public class ComicDetailActivity extends AppCompatActivity
{
    public static final String INTENT_COMIC_ID = "comic_id";

    TextView tvTitle, tvDescription, tvNumberOfPages, tvPrice;
    ImageView ivImage;
    ListView lvCreators;

    private Comic comic;
    private int comicID;
    private CreatorAdapter creatorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        comicID = getIntent().getIntExtra(INTENT_COMIC_ID, 0);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvNumberOfPages = (TextView) findViewById(R.id.tvNumberOfPages);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        lvCreators = (ListView) findViewById(R.id.lvCreators);

        comic = DatabaseManager.getComic(comicID);

        tvTitle.setText(comic.getTitle());
        tvDescription.setText(comic.getDescription());
        tvNumberOfPages.setText(comic.getPageCount()+"");
        tvPrice.setText(comic.getPrice()+"");
        if (comic.getImageBigLandscape() != null && !comic.getImageBigLandscape().isEmpty())
        {
            Picasso.with(this).load(comic.getImageBigLandscape()).into(ivImage);
        }

        List<Creator> creatorList = comic.getCreators();
        creatorAdapter = new CreatorAdapter(this);
        lvCreators.setAdapter(creatorAdapter);
        creatorAdapter.updateList(creatorList);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
