package cn.itsource.cms.query;

/**  
* @Title: ArticleQuery.java
* @Package:cn.itsource.cms.query
* @Description:(文章查询，继承自BaseQuery，便于扩展)
* @author:Joi
* @date:2020年6月5日
* @version:V1.0  
*/
public class ArticleQuery extends BaseQuery{
	//文章类型
	private Long typeId;
	//文章标题
	private String title;
	//是否启用
	private Boolean enable;
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "ArticleQuery [typeId=" + typeId + ", title=" + title + ", enable=" + enable + "]";
	}
}
