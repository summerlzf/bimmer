package com.kedu.bimmer.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jef
 */
public class ArticleDTO {

    /** 文章id */
    private String articleId;

    /** 标题 */
    private String title;

    /** 副标题 */
    private String subTitle;

    /** 内容 */
    private String content;

    /** 内容（分段） */
    private List<String> contents;

    /** 作者的用户名 */
    private String authorUserName;

    /** 作者的用户昵称 */
    private String authorNickName;

    /** 浏览次数 */
    private int viewCount;

    /** 是否允许评论 */
    private boolean allowComment;

    /** 是否隐藏 */
    private boolean hidden;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 创建时间 */
    private String createTimeStr;

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

    public boolean isAllowComment() {
        return allowComment;
    }

    public void setAllowComment(boolean allowComment) {
        this.allowComment = allowComment;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

	public List<String> getContents() {
		return contents;
	}

	public void setContents(List<String> contents) {
		this.contents = contents;
	}

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
