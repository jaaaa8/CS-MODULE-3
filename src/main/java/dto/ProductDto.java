package dto;

public class ProductDto {
    private int id;

    private String categoryName;
    private String authorName;
    private String publisherName;
    private String title;
    private String description;
    private double price;
    private int stock;
    private String imgURL;

    public ProductDto() {
    }
    public ProductDto(int id, String categoryName, String authorName, String publisherName, String title, String description, double price, int stock, String imgURL) {
        this.id = id;
        this.categoryName = categoryName;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imgURL = imgURL;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
