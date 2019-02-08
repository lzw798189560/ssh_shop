package com.lzw.ssh_shop.product.action;

import com.lzw.ssh_shop.category.service.CategoryService;
import com.lzw.ssh_shop.category.vo.Category;
import com.lzw.ssh_shop.product.service.ProductService;
import com.lzw.ssh_shop.product.vo.Product;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * 商品的Action对象
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
    //用于接收数据的模型驱动
    private Product product=new Product();
    //注入商品的Service
    private ProductService productService;
    //接收分类cid
    private Integer cid;
    //注入一级分类的Service
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Product getModel(){
        return product;
    }

    //根据商品的ID查询商品：执行方法
    public String findByPid(){
        //调用Service的方法完成查询
        product=productService.findByPid(product.getPid());
        return "findByPid";
    }

    //根据分类的id查询商品
    public String findByCid(){
        // List<Category> cList=categoryService.findAll();
        return "findByCid";
    }
}
