package com.qianliu.springboot_test.web;

import com.qianliu.springboot_test.entity.User2;
import com.qianliu.springboot_test.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Resource
    UserService userService;

    /*
     * @author qianliu on 2019/5/14 16:42
     * @param  / 输入一个"/"字符串
     * @return 跳转到"/list"页面
     * @Discription: 将"/"自动重定向到"/list"
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    /*
     * @author qianliu on 2019/5/14 16:44
     * @param  输入字符串"/list"
     * @return
     * @Discription:
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<User2> users=userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    /*
     * @author qianliu on 2019/5/14 17:39
     * @Discription:跳转到增加用户页面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    /*
     * @author qianliu on 2019/5/14 17:41
     * @param  "/add"链接
     * @return  重定向到/list页面
     * @Discription: 保存User实体到数据库，然后返回到list界面
     */
    @RequestMapping("/add")
    public String add(User2 user) {
        userService.save(user);
        return "redirect:/list";
    }

    /*
     * @author qianliu on 2019/5/14 18:39
     * @param  /toEdit链接进入该程序
     * @return 查询出User以后，跳转到"user/userEdit"编辑界面
     * @Discription:
     */
    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        User2 user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    /*
     * @author qianliu on 2019/5/14 18:40
     * @param  "/edit"链接进入该程序
     * @return 重定向到"/list"链接
     * @Discription:更新数据库中的User以后跳转到list
     */
    @RequestMapping("/edit")
    public String edit(User2 user) {
        userService.edit(user);
        return "redirect:/list";
    }

    /*
     * @author qianliu on 2019/5/14 18:43
     * @param  "/delete"链接进入该方法
     * @return  重定向到"/list"
     * @Discription: 删除某一个数据后定向到"/list"
     */
    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/list";
    }
}
