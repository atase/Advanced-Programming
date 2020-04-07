public class Album {

    private String artistId;
    private String id;
    private String name;
    private Integer releaseYear;

    public Album(){
        this.artistId = null;
        this.id = null;
        this.name = null;
        this.releaseYear = null;
    }

    public Album(String artistId, String id, String name, Integer releaseYear){
        this.artistId = artistId;
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public void setArtistId(String artistId){
        this.artistId = artistId;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setReleaseYear(Integer releaseYear){
        this.releaseYear = releaseYear;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getArtistId(){
        return this.artistId;
    }

    public Integer getReleaseYear(){
        return this.releaseYear;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nId : " + this.id);
        stringBuilder.append("\nName : " + this.name);
        stringBuilder.append("\nRelease year : " + this.releaseYear);
        stringBuilder.append("\nArtist id : " + this.artistId);
        return stringBuilder.toString();
    }

}
