package entity;

public class Book {
    private int id;
    private int categoryId;
    private int authorId;
    private int publisherId;
    private String title;
    private String description;
    private double price;
    private int stock;
    private String imgURL;

    public Book(int id, int categoryId, int authorId, int publisherId, String title, String description, double price, int stock, String imgURL) {
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.description = description;
        this.id = id;
        this.imgURL = imgURL;
        this.price = price;
        this.publisherId = publisherId;
        this.stock = stock;
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
