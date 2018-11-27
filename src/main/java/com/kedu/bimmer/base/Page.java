package com.kedu.bimmer.base;

import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

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

	public static <E> Page<E> startPage(int pageNum, int pageSize, Callable<List<E>> callable) {
		com.github.pagehelper.Page<E> pg = PageHelper.startPage(pageNum, pageSize);
		Page<E> page = new Page<>(pageNum, pageSize);
		try {
			page.setDataList(callable.call());
			page.setTotalCount((int) pg.getTotal());
		} catch (Exception e) {
			//
		}
		return page;
	}

	public Page() {
		//
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
