package com.tmall.service;

import java.util.List;

import com.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tmall.dao.CategoryDAO;
import com.tmall.pojo.Category;

@Service
public class CategoryService {
    @Autowired CategoryDAO categoryDAO;

    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA = categoryDAO.findAll(pageable);
        //System.out.print("page4+"+pageFromJPA.getContent().size() + "\n");
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<Category> rt = categoryDAO.findAll(sort);
        //System.out.print(rt.size()+"\n");
        return rt;
        //return categoryDAO.findAll(sort);
    }

    public void add(Category bean) {
        categoryDAO.save(bean);
    }

    public void delete(int id) {
        categoryDAO.delete(id);
    }

    public Category get(int id) {
        Category c = categoryDAO.findOne(id);
        return c;
    }

    public void update(Category bean) {
        categoryDAO.save(bean);
    }
}
