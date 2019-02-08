package com.kedu.bimmer.model;

/**
 * @author Jef
 */
public class ArticleExtend {

    /** 文章id */
    private String articleId;

    /** 链接地址URL */
    private String linkUrl;

    /** 图片地址URL */
    private String imageUrl;

    /** 所在位置 */
    private String position;

    /** 排序序号 */
    private int sortOrder;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
