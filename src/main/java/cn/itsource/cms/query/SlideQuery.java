package cn.itsource.cms.query;

/**  
* @Title: ArticleQuery.java
* @Package:cn.itsource.cms.query
* @Description:(轮播图查询，继承自BaseQuery，便于扩展)
* @author:Joi
* @date:2020年6月9日
* @version:V1.0  
*/
public class SlideQuery extends BaseQuery{
	private String name;
	private Boolean enable;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public SlideQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SlideQuery(String name, Boolean enable) {
		this.name = name;
		this.enable = enable;
	}
}
