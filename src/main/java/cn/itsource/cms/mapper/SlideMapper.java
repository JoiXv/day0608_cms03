package cn.itsource.cms.mapper;

import java.util.List;

import cn.itsource.cms.domain.Slide;
import cn.itsource.cms.query.SlideQuery;

public interface SlideMapper {

	//查询满足查询条件的总条数
	Long findPageCountByQuery(SlideQuery query);

	//查询满足查询条件的数据集合
	List<Slide> findPageList(SlideQuery query);

	//添加轮播图
	void add(Slide slide);

	//查询单个Slide对象
	Slide findOne(Long id);

	//执行更新数据库操作
	void update(Slide slide);

	//执行删除
	void del(Long id);

	//查询所有轮播图
	List<Slide> findSlides();
	
}
