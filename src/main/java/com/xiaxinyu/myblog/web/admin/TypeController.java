package com.xiaxinyu.myblog.web.admin;


import com.xiaxinyu.myblog.po.Type;
import com.xiaxinyu.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(@PageableDefault(size = 6,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){

        //pageable实现分页
        //将封装好的page对象传递给前端页面
        model.addAttribute("page",typeService.listType(pageable));

        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //由于请求类型不一样，所以不会有冲突
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){ //接受由html页面传过来的Type对象(包括提交的内容)
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            //这里的name必须是Type里的name名称一致
            result.rejectValue("name","nameError","不能添加重复分类");
        }

        //错误校验
        if(result.hasErrors()){
            return "admin/types-input";
        }

        Type t = typeService.saveType(type);
        if(t == null){
            //保存失败
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }


    @PostMapping("/types/{id}") //BindingResult和Type之间不能有其他类型的参数，否则校验无效
    public String editPost(@Valid Type type,BindingResult result,@PathVariable Long id, RedirectAttributes attributes){ //接受由html页面传过来的Type对象(包括提交的内容)
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            //这里的name必须是Type里的name名称一致
            result.rejectValue("name","nameError","不能添加重复分类");
        }

        //错误校验
        if(result.hasErrors()){
            return "admin/types-input";
        }

        Type t = typeService.updateType(id,type);
        if(t == null){
            //保存失败
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}
