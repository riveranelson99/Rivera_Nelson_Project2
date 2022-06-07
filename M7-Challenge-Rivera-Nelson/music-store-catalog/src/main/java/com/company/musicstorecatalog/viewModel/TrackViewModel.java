package com.company.musicstorecatalog.viewModel;

import com.company.musicstorecatalog.models.Album;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TrackViewModel {

    private long id;
    @NotNull
    private Album album;
    @NotNull
    private String title;
    @NotNull
    private int runTime;

    public TrackViewModel() {
    }

    public TrackViewModel(long id, Album album, String title, int runTime) {
        this.id = id;
        this.album = album;
        this.title = title;
        this.runTime = runTime;
    }

    public TrackViewModel(Album album, String title, int runTime) {
        this.album = album;
        this.title = title;
        this.runTime = runTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackViewModel that = (TrackViewModel) o;
        return id == that.id && runTime == that.runTime && Objects.equals(album, that.album) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, album, title, runTime);
    }

    @Override
    public String toString() {
        return "TrackViewModel{" +
                "id=" + id +
                ", album=" + album +
                ", title='" + title + '\'' +
                ", runTime=" + runTime +
                '}';
    }
}
