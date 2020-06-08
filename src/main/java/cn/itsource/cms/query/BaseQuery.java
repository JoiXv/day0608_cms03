package cn.itsource.cms.query;

/**  
* @Title: BaseQuery.java
* @Package:cn.itsource.cms.query
* @Description:(基础查询)
* @author:Joi
* @date:2020年6月5日
* @version:V1.0  
*/
public class BaseQuery {
	//分页信息：当前页
	private Long localPage;
	//分页信息：每一页显示的数量
	private Long pageSize;
	
	/**
	 * @Description:(获取起始页,获取开始条数的下标   即limit的第一个参数值)
	 * @param:@return   
	 * @return:Long  
	 * @author:Joi
	 * @date:2020年6月5日
	 * @version:V1.0
	 */
	public Long getBegin() {
		return (this.localPage-1)*this.pageSize;
	}
	
	public Long getLocalPage() {
		return localPage;
	}
	public void setLocalPage(Long localPage) {
		this.localPage = localPage;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "BaseQuery [localPage=" + localPage + ", pageSize=" + pageSize + "]";
	}
	
}
