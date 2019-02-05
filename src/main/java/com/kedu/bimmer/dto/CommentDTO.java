package com.kedu.bimmer.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jef
 */
public class CommentDTO {

    /** 评论id */
    private String commentId;

    /** 评论内容 */
    private String content;

    /** 评论内容（分段） */
    private List<String> contents;

    /** 文章id */
    private String articleId;

    /** 文章标题 */
    private String articleTitle;

    /** 评论的用户名 */
    private String userName;

    /** 是否隐藏 */
    private boolean hidden;

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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
