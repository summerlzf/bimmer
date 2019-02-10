package com.kedu.bimmer.dto;

/**
 * @author Jef
 */
public class ArticleIndexDTO {

    /** 文章id */
    private String articleId;

    /** 标题 */
    private String title;

    /** 副标题 */
    private String subTitle;

    /** 内容（截取前面一部分） */
    private String content;

    /** 链接地址URL */
    private String linkUrl;

    /** 图片地址URL */
    private String imageUrl;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
