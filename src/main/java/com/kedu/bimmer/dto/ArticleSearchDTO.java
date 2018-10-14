package com.kedu.bimmer.dto;

/**
 * @author Jef
 */
public class ArticleSearchDTO {

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 作者 */
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
