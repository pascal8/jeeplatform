package org.muses.jeeplatform.service;

import org.muses.jeeplatform.core.dao.repository.admin.MenuRepository;
import org.muses.jeeplatform.core.entity.admin.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {
	
	@Autowired
	MenuRepository menuRepository;

	/**
	 * 构建PageRequest对象
	 * @param num
	 * @param size
	 * @param asc
	 * @param string
	 * @return
	 */
	private PageRequest buildPageRequest(int num, int size, Sort.Direction asc,
										 String string) {
		return new PageRequest(num-1, size,null,string);
	}

	/**
	 * 获取所有的菜单信息并分页显示
	 * @param pageNo
	 * 			当前页面数
	 * @param pageSize
	 * 			每一页面的页数
	 * @return
	 */
	@Transactional
	public Page<Menu> findAll(int pageNo, int pageSize, Sort.Direction dir, String str){
		PageRequest request = buildPageRequest(pageNo, pageSize, dir, str);
		Page<Menu> menus = menuRepository.findAll(request);
		return menus;
	}
	
	/**
	 * 获取所有的上级菜单
	 * @return
	 */
	@Transactional
	public List<Menu> findAllParentMenu(){
		return menuRepository.findAllParentMenu();
	}
	
	/**
	 * 通过上级Id获取二级菜单
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Menu> findSubMenuById(int id){
		return menuRepository.findSubMenuByParentId(id);
	}

	/**
	 * 通过菜单Id获取菜单信息
	 * @param id
	 * @return
	 */
	@Transactional
	public Menu findMenuById(int id){
		return menuRepository.findMenuByMenuId(id);
	}

	/**
	 * 更新菜单信息
	 * @param m
	 */
	public void editM(Menu m){
		menuRepository.save(m);
	}

}
