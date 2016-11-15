
package com.example.yuri.itunessearch.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ItunesItem {

    @SerializedName("wrapperType")
    private String wrapperType;
    @SerializedName("kind")
    private String kind;
    @SerializedName("artistId")
    private Long artistId;
    @SerializedName("collectionId")
    private Long collectionId;
    @SerializedName("trackId")
    private Long trackId;
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("collectionName")
    private String collectionName;
    @SerializedName("trackName")
    private String trackName;
    @SerializedName("collectionCensoredName")
    private String collectionCensoredName;
    @SerializedName("trackCensoredName")
    private String trackCensoredName;
    @SerializedName("artistViewUrl")
    private String artistViewUrl;
    @SerializedName("collectionViewUrl")
    private String collectionViewUrl;
    @SerializedName("trackViewUrl")
    private String trackViewUrl;
    @SerializedName("previewUrl")
    private String previewUrl;
    @SerializedName("artworkUrl60")
    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    private Double collectionPrice;
    @SerializedName("trackPrice")
    private Double trackPrice;
    @SerializedName("collectionExplicitness")
    private String collectionExplicitness;
    @SerializedName("trackExplicitness")
    private String trackExplicitness;
    @SerializedName("discCount")
    private Integer discCount;
    @SerializedName("discNumber")
    private Integer discNumber;
    @SerializedName("trackCount")
    private Integer trackCount;
    @SerializedName("trackNumber")
    private Integer trackNumber;
    @SerializedName("trackTimeMillis")
    private Integer trackTimeMillis;
    @SerializedName("country")
    private String country;
    @SerializedName("currency")
    private String currency;
    @SerializedName("primaryGenreName")
    private String primaryGenreName;

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getCollectionId() {
        return collectionId;
    }


    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }


    public Long getTrackId() {
        return trackId;
    }


    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }


    public String getArtistName() {
        return artistName;
    }


    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }


    public String getCollectionName() {
        return collectionName;
    }


    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }


    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }


    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }


    public String getTrackCensoredName() {
        return trackCensoredName;
    }


    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }


    public String getArtistViewUrl() {
        return artistViewUrl;
    }


    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }


    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }


    public String getTrackViewUrl() {
        return trackViewUrl;
    }


    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }


    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }


    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }


    public String getArtworkUrl100() {
        return artworkUrl100;
    }


    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }


    public Double getCollectionPrice() {
        return collectionPrice;
    }


    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }


    public Double getTrackPrice() {
        return trackPrice;
    }


    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }


    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }


    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }


    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }


    public Integer getDiscCount() {
        return discCount;
    }


    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }


    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }


    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }


    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }


    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }


    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItunesItem)) return false;

        ItunesItem that = (ItunesItem) o;

        if (wrapperType != null ? !wrapperType.equals(that.wrapperType) : that.wrapperType != null)
            return false;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;
        if (artistId != null ? !artistId.equals(that.artistId) : that.artistId != null)
            return false;
        if (collectionId != null ? !collectionId.equals(that.collectionId) : that.collectionId != null)
            return false;
        if (trackId != null ? !trackId.equals(that.trackId) : that.trackId != null) return false;
        if (artistName != null ? !artistName.equals(that.artistName) : that.artistName != null)
            return false;
        if (collectionName != null ? !collectionName.equals(that.collectionName) : that.collectionName != null)
            return false;
        if (trackName != null ? !trackName.equals(that.trackName) : that.trackName != null)
            return false;
        if (collectionCensoredName != null ? !collectionCensoredName.equals(that.collectionCensoredName) : that.collectionCensoredName != null)
            return false;
        if (trackCensoredName != null ? !trackCensoredName.equals(that.trackCensoredName) : that.trackCensoredName != null)
            return false;
        if (artistViewUrl != null ? !artistViewUrl.equals(that.artistViewUrl) : that.artistViewUrl != null)
            return false;
        if (collectionViewUrl != null ? !collectionViewUrl.equals(that.collectionViewUrl) : that.collectionViewUrl != null)
            return false;
        if (trackViewUrl != null ? !trackViewUrl.equals(that.trackViewUrl) : that.trackViewUrl != null)
            return false;
        if (previewUrl != null ? !previewUrl.equals(that.previewUrl) : that.previewUrl != null)
            return false;
        if (artworkUrl60 != null ? !artworkUrl60.equals(that.artworkUrl60) : that.artworkUrl60 != null)
            return false;
        if (artworkUrl100 != null ? !artworkUrl100.equals(that.artworkUrl100) : that.artworkUrl100 != null)
            return false;
        if (collectionPrice != null ? !collectionPrice.equals(that.collectionPrice) : that.collectionPrice != null)
            return false;
        if (trackPrice != null ? !trackPrice.equals(that.trackPrice) : that.trackPrice != null)
            return false;
        if (collectionExplicitness != null ? !collectionExplicitness.equals(that.collectionExplicitness) : that.collectionExplicitness != null)
            return false;
        if (trackExplicitness != null ? !trackExplicitness.equals(that.trackExplicitness) : that.trackExplicitness != null)
            return false;
        if (discCount != null ? !discCount.equals(that.discCount) : that.discCount != null)
            return false;
        if (discNumber != null ? !discNumber.equals(that.discNumber) : that.discNumber != null)
            return false;
        if (trackCount != null ? !trackCount.equals(that.trackCount) : that.trackCount != null)
            return false;
        if (trackNumber != null ? !trackNumber.equals(that.trackNumber) : that.trackNumber != null)
            return false;
        if (trackTimeMillis != null ? !trackTimeMillis.equals(that.trackTimeMillis) : that.trackTimeMillis != null)
            return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null)
            return false;
        return primaryGenreName != null ? primaryGenreName.equals(that.primaryGenreName) : that.primaryGenreName == null;

    }

    @Override
    public int hashCode() {
        int result = wrapperType != null ? wrapperType.hashCode() : 0;
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (artistId != null ? artistId.hashCode() : 0);
        result = 31 * result + (collectionId != null ? collectionId.hashCode() : 0);
        result = 31 * result + (trackId != null ? trackId.hashCode() : 0);
        result = 31 * result + (artistName != null ? artistName.hashCode() : 0);
        result = 31 * result + (collectionName != null ? collectionName.hashCode() : 0);
        result = 31 * result + (trackName != null ? trackName.hashCode() : 0);
        result = 31 * result + (collectionCensoredName != null ? collectionCensoredName.hashCode() : 0);
        result = 31 * result + (trackCensoredName != null ? trackCensoredName.hashCode() : 0);
        result = 31 * result + (artistViewUrl != null ? artistViewUrl.hashCode() : 0);
        result = 31 * result + (collectionViewUrl != null ? collectionViewUrl.hashCode() : 0);
        result = 31 * result + (trackViewUrl != null ? trackViewUrl.hashCode() : 0);
        result = 31 * result + (previewUrl != null ? previewUrl.hashCode() : 0);
        result = 31 * result + (artworkUrl60 != null ? artworkUrl60.hashCode() : 0);
        result = 31 * result + (artworkUrl100 != null ? artworkUrl100.hashCode() : 0);
        result = 31 * result + (collectionPrice != null ? collectionPrice.hashCode() : 0);
        result = 31 * result + (trackPrice != null ? trackPrice.hashCode() : 0);
        result = 31 * result + (collectionExplicitness != null ? collectionExplicitness.hashCode() : 0);
        result = 31 * result + (trackExplicitness != null ? trackExplicitness.hashCode() : 0);
        result = 31 * result + (discCount != null ? discCount.hashCode() : 0);
        result = 31 * result + (discNumber != null ? discNumber.hashCode() : 0);
        result = 31 * result + (trackCount != null ? trackCount.hashCode() : 0);
        result = 31 * result + (trackNumber != null ? trackNumber.hashCode() : 0);
        result = 31 * result + (trackTimeMillis != null ? trackTimeMillis.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (primaryGenreName != null ? primaryGenreName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItunesItem{" +
                "wrapperType='" + wrapperType + '\'' +
                ", kind='" + kind + '\'' +
                ", artistId=" + artistId +
                ", collectionId=" + collectionId +
                ", trackId=" + trackId +
                ", artistName='" + artistName + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", trackName='" + trackName + '\'' +
                ", collectionCensoredName='" + collectionCensoredName + '\'' +
                ", trackCensoredName='" + trackCensoredName + '\'' +
                ", artistViewUrl='" + artistViewUrl + '\'' +
                ", collectionViewUrl='" + collectionViewUrl + '\'' +
                ", trackViewUrl='" + trackViewUrl + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                ", artworkUrl60='" + artworkUrl60 + '\'' +
                ", artworkUrl100='" + artworkUrl100 + '\'' +
                ", collectionPrice=" + collectionPrice +
                ", trackPrice=" + trackPrice +
                ", collectionExplicitness='" + collectionExplicitness + '\'' +
                ", trackExplicitness='" + trackExplicitness + '\'' +
                ", discCount=" + discCount +
                ", discNumber=" + discNumber +
                ", trackCount=" + trackCount +
                ", trackNumber=" + trackNumber +
                ", trackTimeMillis=" + trackTimeMillis +
                ", country='" + country + '\'' +
                ", currency='" + currency + '\'' +
                ", primaryGenreName='" + primaryGenreName + '\'' +
                '}';
    }
}
