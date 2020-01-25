package pl.stqa.pft.addressbook.model;

public class GroupData {

    public String name;
    public String header;
    public String footer;

    public GroupData(String name, String header, String footer) {
        this.header = header;
        this.name = name;
        this.footer = footer;
    }

    public String getName() {
        return name;
    }
    public String getHeader() {
        return header;
    }
    public String getFooter() {
        return footer;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public void setFooter(String footer) {
        this.footer = footer;
    }

}
