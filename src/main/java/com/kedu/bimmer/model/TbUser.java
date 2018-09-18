package com.kedu.bimmer.model;

public class TbUser {
	
	/** 用户id（UUID） */
	private String uidUserId;
	
	/** 微信用户id */
	private String wxOpenId;
	
	/** 用户头像URL */
	private String strAvatarUrl;
	
	/** 用户昵称 */
	private String strNickName;
	
	/** 性别（0-未知，1-男，2-女） */
	private int iGender;
	
	/** 所在的省 */
	private String strProvince;
	
	/** 所在的市 */
	private String strCity;
	
	/** 用户预算额度 */
	private int iBudget;
	
	/** 月份统计的起算日期 */
	private int iStartDay;
	
	/** 创建时间 */
	private String strCreateTime;
	
	/** 创建时间戳 */
	private long lgCreateTimestamp;
	
	/** 最后一次登录时间 */
	private String strLastLoginTime;
	
	/** 最后一次登录时间戳 */
	private long lgLastLoginTimestamp;

	public String getUidUserId() {
		return uidUserId;
	}

	public void setUidUserId(String uidUserId) {
		this.uidUserId = uidUserId;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getStrCreateTime() {
		return strCreateTime;
	}

	public void setStrCreateTime(String strCreateTime) {
		this.strCreateTime = strCreateTime;
	}

	public long getLgCreateTimestamp() {
		return lgCreateTimestamp;
	}

	public void setLgCreateTimestamp(long lgCreateTimestamp) {
		this.lgCreateTimestamp = lgCreateTimestamp;
	}

	public int getiBudget() {
		return iBudget;
	}

	public void setiBudget(int iBudget) {
		this.iBudget = iBudget;
	}

	public int getiStartDay() {
		return iStartDay;
	}

	public void setiStartDay(int iStartDay) {
		this.iStartDay = iStartDay;
	}

	public String getStrAvatarUrl() {
		return strAvatarUrl;
	}

	public void setStrAvatarUrl(String strAvatarUrl) {
		this.strAvatarUrl = strAvatarUrl;
	}

	public String getStrNickName() {
		return strNickName;
	}

	public void setStrNickName(String strNickName) {
		this.strNickName = strNickName;
	}

	public int getiGender() {
		return iGender;
	}

	public void setiGender(int iGender) {
		this.iGender = iGender;
	}

	public String getStrProvince() {
		return strProvince;
	}

	public void setStrProvince(String strProvince) {
		this.strProvince = strProvince;
	}

	public String getStrCity() {
		return strCity;
	}

	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}

	public String getStrLastLoginTime() {
		return strLastLoginTime;
	}

	public void setStrLastLoginTime(String strLastLoginTime) {
		this.strLastLoginTime = strLastLoginTime;
	}

	public long getLgLastLoginTimestamp() {
		return lgLastLoginTimestamp;
	}

	public void setLgLastLoginTimestamp(long lgLastLoginTimestamp) {
		this.lgLastLoginTimestamp = lgLastLoginTimestamp;
	}

}
