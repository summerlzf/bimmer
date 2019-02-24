package com.kedu.bimmer.model;

import java.time.LocalDateTime;

/**
 * 评论
 *
 * Created by liuzifeng on 2018/12/7.
 */
public class Comment {

	/** 评论id */
	private String commentId;

	/** 所回复的评论id */
	private String replyCommentId;

	/** 文章id */
	private String articleId;

	/** 用户id */
	private String userId;

	/** 评论内容 */
	private String content;

	/** 是否隐藏 */
	private boolean hidden;

	/** 创建时间 */
	private LocalDateTime createTime;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getReplyCommentId() {
		return replyCommentId;
	}

	public void setReplyCommentId(String replyCommentId) {
		this.replyCommentId = replyCommentId;
	}
}
