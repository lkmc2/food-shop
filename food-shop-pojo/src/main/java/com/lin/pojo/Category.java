package com.lin.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 商品分类实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
public class Category {
    @Id
    private String id;

    private String name;

    private String type;

    @Column(name = "father_id")
    private String fatherId;

    private String logo;

    private String slogan;

    @Column(name = "cat_image")
    private String catImage;

    @Column(name = "bg_color")
    private String bgColor;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return parent_name
     */
    public String getFatherId() {
        return fatherId;
    }

    /**
     * @param fatherId
     */
    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}