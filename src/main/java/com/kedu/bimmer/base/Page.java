package com.kedu.bimmer.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by liuzifeng on 2018/11/5.
 */
public class Page<E> {

	private int pageNum = 1;
	private int pageSize = 10;
	private int startRow;
	private int totalCount;
	private int totalPage;
	private List<E> dataList;

	public Page() {
	}

	public Page(int pageNum, int pageSize) {
		if(pageNum >= 1) {
			this.pageNum = pageNum;
		}
		if(pageNum >= 1) {
			this.pageSize = pageSize;
		}
		this.startRow = (this.pageNum - 1) * this.pageSize;
	}

	public int getStartRow() {
		return this.startRow;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(totalCount % this.pageSize == 0) {
			this.totalPage = totalCount / this.pageSize;
		} else {
			this.totalPage = totalCount / this.pageSize + 1;
		}
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public List<E> getDataList() {
		return Optional.ofNullable(dataList).orElse(new ArrayList<>());
	}

	public void setDataList(List<E> dataList) {
		this.dataList = dataList;
	}

	public int getPageNum() {
		return this.pageNum;
	}
}
