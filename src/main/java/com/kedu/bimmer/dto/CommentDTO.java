package com.kedu.bimmer.dto;

import java.time.LocalDateTime;

/**
 * @author Jef
 */
public class CommentDTO {

    /** 评论id */
    private String commentId;

    /** 评论内容 */
    private String content;

    /** 文章标题 */
    private String articleTitle;

    /** 评论的用户名 */
    private String userName;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 创建时间 */
    private String createTimeStr;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
