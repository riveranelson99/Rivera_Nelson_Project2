package com.company.musicstorecatalog.viewModel;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ArtistViewModel {

    private long id;
    @NotNull
    private String name;
    private String instagram;
    private String twitter;

    public ArtistViewModel() {
    }

    public ArtistViewModel(long id, String name, String instagram, String twitter) {
        this.id = id;
        this.name = name;
        this.instagram = instagram;
        this.twitter = twitter;
    }

    public ArtistViewModel(String name, String instagram, String twitter) {
        this.name = name;
        this.instagram = instagram;
        this.twitter = twitter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistViewModel that = (ArtistViewModel) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(instagram, that.instagram) && Objects.equals(twitter, that.twitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instagram, twitter);
    }

    @Override
    public String toString() {
        return "ArtistViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instagram='" + instagram + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }
}
