package com.kedu.bimmer.dto;

import java.time.LocalDateTime;

/**
 * @author Jef
 */
public class ArticleDTO {

    /** 文章id */
    private String articleId;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 作者的用户名 */
    private String authorUserName;

    /** 作者的用户昵称 */
    private String authorNickName;

    /** 最后更新时间 */
    private LocalDateTime lastModifyTime;

    /** 最后更新时间 */
    private String lastModifyTimeStr;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorUserName() {
        return authorUserName;
    }

    public void setAuthorUserName(String authorUserName) {
        this.authorUserName = authorUserName;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public LocalDateTime getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(LocalDateTime lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyTimeStr() {
        return lastModifyTimeStr;
    }

    public void setLastModifyTimeStr(String lastModifyTimeStr) {
        this.lastModifyTimeStr = lastModifyTimeStr;
    }
}
