package cn.itsource.cms.util;

import java.util.ArrayList;
import java.util.List;

/**  
* @Title: PageBean.java
* @Package:cn.itsource.cms.util
* @Description:(封装返回给gridmanager接收的data 数据列表和totals 数据长度)
* @author:Joi
* @date:2020年6月5日
* @version:V1.0  
*/
public class PageBean<T> {
	//传递数据总长度
	private Long totals = 0l;
	//传递的list对象
	private List<T> data  = new ArrayList<>();
	//无参构造
	public PageBean() {
	}
	//有参构造
	public PageBean(Long totals, List<T> data) {
		this.totals = totals;
		this.data = data;
	}
	public Long getTotals() {
		return totals;
	}
	public void setTotals(Long totals) {
		this.totals = totals;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "PageBean [totals=" + totals + ", data=" + data + "]";
	}
}
