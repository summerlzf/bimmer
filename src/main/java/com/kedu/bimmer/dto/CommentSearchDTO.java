package com.kedu.bimmer.dto;

/**
 * @author Jef
 */
public class CommentSearchDTO {

    /** 内容 */
    private String content;

    /** 文章标题 */
    private String articleTitle;

    /** 用户名 */
    private String userName;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
