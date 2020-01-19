package ru.lm.itunesplayer.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */

public class ItunesResponse {
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<ItunesMedia> results;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<ItunesMedia> getResults() {
        return results;
    }

    public void setResults(List<ItunesMedia> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItunesResponse that = (ItunesResponse) o;
        return Objects.equals(resultCount, that.resultCount) &&
                Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCount, results);
    }
}
