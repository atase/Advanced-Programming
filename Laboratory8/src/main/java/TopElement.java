public class TopElement {

    private Album album;
    private Artist artist;
    private Integer score;

    public TopElement(){
        this.artist = null;
        this.album = null;
        this.score = null;
    }

    public TopElement(Artist artist, Album album, Integer score){
        this.artist = artist;
        this.album = album;
        this.score = score;
    }

    public Artist getArtist(){
        return this.artist;
    }

    public Album getAlbum(){
        return this.album;
    }

    public Integer getScore(){
        return this.score;
    }
}
