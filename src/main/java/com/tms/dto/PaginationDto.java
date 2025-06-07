package com.tms.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class PaginationDto<E> {

	private List<E> content;
	private Integer pageno;
	private Integer pagesize;
	private Long totalelement;
	private Long totalpages;
	private boolean last;
	
	public PaginationDto() {
	}
	
	public PaginationDto(Page<E> p)  {
		content = p.getContent();
		pageno = p.getNumber();
		pagesize = p.getSize();
		totalelement = p.getTotalElements();
		totalpages = p.getTotalElements();
		last = p.isLast();
	}
	
	public List<E> getContent() {
		return content;
	}
	public Integer getPageno() {
		return pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public Long getTotalelement() {
		return totalelement;
	}
	public Long getTotalpages() {
		return totalpages;
	}
	public boolean isLast() {
		return last;
	}
	public void setContent(List<E> content) {
		this.content = content;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public void setTotalelement(Long totalelement) {
		this.totalelement = totalelement;
	}
	public void setTotalpages(Long totalpages) {
		this.totalpages = totalpages;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	
}