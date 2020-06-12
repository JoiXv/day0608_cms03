package cn.itsource.cms.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.itsource.cms.domain.Slide;
import cn.itsource.cms.mapper.SlideMapper;
import cn.itsource.cms.query.SlideQuery;
import cn.itsource.cms.service.ISlideService;
import cn.itsource.cms.util.PageBean;

@Service
public class SlideServiceImpl implements ISlideService{
	
	@Autowired
	private SlideMapper mapper;

	@Override
	public PageBean<Slide> findPageList(SlideQuery query) {
		Long totals = mapper.findPageCountByQuery(query);
		if (totals == 0l) {//根据查询条件没匹配到对象，返回一个空的默认PageBean
			return new PageBean<Slide>();
		}
		//查询指定页面中的内容返回一个list对象
		//修改SlideMapper.xml的findPageList方法为高级分页查询
		List<Slide> slides = mapper.findPageList(query);
		return new PageBean<Slide>(totals,slides);
	}

	@Override
	public void save(Slide slide, HttpServletRequest req, MultipartFile photo) {
		
		//2.1.获取文件上传的父目录
		String parentPath = req.getServletContext().getRealPath("/upload");
		
		if(photo != null){//用户上传了文件
			InputStream in = null;
			FileOutputStream out = null; 
			try {
				//1.获取输入流
				in = photo.getInputStream();
				//2.获取输出流
				File file = new File(parentPath);
				//2.2.处理文件的名称
				String oName = photo.getOriginalFilename();//404.jpg
				String suffix = oName.substring(oName.lastIndexOf("."));
				String fileName = System.currentTimeMillis() + suffix;//1234545788.jpg
				
				if(!file.exists()){//判断根路径下upload文件夹是否存在，不存在就创建
					file.mkdir();
				}
				out = new FileOutputStream(new File(parentPath, fileName));
				//3.上传文件
				IOUtils.copy(in, out);
				slide.setName(fileName);//设置文件的名称
				slide.setPath("/upload/" + fileName);//设置文件的路径
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
					try {
						if(out != null){
							out.close();
						}
						if(in != null){
							in.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
		
		//判断是添加或修改操作
		if(slide.getId() == null){//添加操作【用户没有上传图片    or 用户上传了图片】
			//能直接添加数据库吗？
			if(photo != null){//用户在添加操作的时候没有上传图片
				mapper.add(slide);//添加到数据库
			}//if
		}else{
			//用户不修改图片   使用动态sql
			//更新操作
			Slide slide2 = mapper.findOne(slide.getId());//拿到数据库中的原来对象，为了删除upload路径下的原文件
			File file = new File(parentPath, slide2.getName());//查询父路径下是否有这图片
			if (file.exists() && photo != null) {
				file.delete();
			}
			//执行更新数据库里的文件名操作   动态sql判断，name和path为空的情况
			mapper.update(slide);
		}//else
	}//save

	@Override
	public void del(Long id, HttpServletRequest req) {
		//先根据id查询一个article对象
		Slide slide = mapper.findOne(id);
		String name = slide.getName();
		mapper.del(id);
		String path = req.getServletContext().getRealPath("/upload");
		File file = new File(path, name);
		if (file.exists()) {
			file.delete();
		}
	}

	@Override
	public List<Slide> findSlides() {
		return mapper.findSlides();
	}


}
