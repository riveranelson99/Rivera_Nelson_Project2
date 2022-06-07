package com.company.musicstorecatalog.viewModel;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class LabelViewModel {

    private long id;
    @NotNull
    private String name;
    private String website;

    public LabelViewModel() {
    }

    public LabelViewModel(long id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }

    public LabelViewModel(String name, String website) {
        this.name = name;
        this.website = website;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelViewModel that = (LabelViewModel) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, website);
    }

    @Override
    public String toString() {
        return "LabelViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
