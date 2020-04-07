public class Artist {

    private String id;
    private String name;
    private String country;

    public Artist(){
        this.id = null;
        this.name = null;
        this.country = null;
    }

    public Artist(String id, String name, String country){
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getcountry(){
        return this.country;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nId : "  + this.id);
        stringBuilder.append("\nName : " + this.name);
        stringBuilder.append("\nCountry : " + this.country);
        return stringBuilder.toString();
    }


}
