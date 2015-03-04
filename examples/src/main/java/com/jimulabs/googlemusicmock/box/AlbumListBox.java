package com.jimulabs.googlemusicmock.box;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jimulabs.googlemusicmock.Album;
import com.jimulabs.googlemusicmock.AlbumsAdapter;
import com.jimulabs.googlemusicmock.R;
import com.jimulabs.mirrorsandbox.mockdata.ImageSize;
import com.jimulabs.mirrorsandbox.MirrorSandboxBase;
import com.jimulabs.mirrorsandbox.mockdata.MockData;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lintonye on 15-02-15.
 */
public class AlbumListBox extends MirrorSandboxBase {

    @InjectView(R.id.album_list)
    RecyclerView albumList;

    public AlbumListBox(View rootView) {
        super(rootView);
        ButterKnife.inject(this, rootView);
        albumList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void $onLayoutDone(View rootView) {
        List<Album> albums = new ArrayList<>();
        MockData md = new MockData();
        for (int i = 0; i < 100; i++) {
            Album album = new Album(md.imageUrl(ImageSize.small), md.personName(),
                    md.phrase(), md.paragraph());
            albums.add(album);
        }
        albumList.setAdapter(new AlbumsAdapter(albums));
    }
}
