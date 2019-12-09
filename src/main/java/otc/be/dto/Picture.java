package otc.be.dto;

public class Picture {
    private String link;
    private String info;

    public Picture(String link, String info) {
        this.link = link;
        this.info = info;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
