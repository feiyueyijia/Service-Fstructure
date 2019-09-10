package com.yfny.servicefstructure.fstructure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yfny.servicefstructure.base.APIBaseTest;
import com.yfny.servicefstructure.constant.ProjectConstant;
import com.yfny.servicefstructure.entity.ProjectEntity;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
public class FStructureProjectSteps extends APIBaseTest {

    boolean menuPermission = false; //菜单权限
    boolean projectPermission = false;  //项目权限
    String createResultMessage = "";   //创建结果
    String updateResultMessage = "";   //修改结果
    String lockResultMessage = "";  //锁定结果
    String queryResultMessage = "";   //查看结果
    String loadListResultMessage = "";  //加载列表结果
    String deleteResultMessage = "";    //删除结果
    JSONArray jsonArray = new JSONArray();//列表数据
    ProjectEntity project = new ProjectEntity();


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Given("^项目--登录用户拥有菜单权限$")
    public void menuPermission() {
//        if (userName.equals("admin")) {
//            menuPermission = true;
//        }
//        Assert.assertEquals(menuPermission, true);
    }

    @Given("^项目--用户 \"([^\"]*)\" 拥有项目权限$")
    public void projectPermission(String userName) {
        project = new ProjectEntity();
        project.setUserName(userName);
    }

    @Given("^项目--动作执行成功$")
    public void success() throws Exception {
        //Assert.assertEquals(createResult, true);
    }

    @When("^项目--点击新建项目按钮$")
    public void createProject() throws Exception {

    }

    @When("^项目--选择 \"([^\"]*)\" 点击锁定项目按钮$")
    public void lockProject(String name) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        project.setName(name);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject != null) {
                String projectName = jsonObject.getString("name");
                if (name.equals(projectName)) {
                    String id = jsonObject.getString("id");
                    project.setId(id);
                }
            }
        }

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/lock";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        lockResultMessage = result.getString("message");
    }

    @When("^项目--选择 \"([^\"]*)\" 点击修改项目按钮$")
    public void updateProject(String name) throws Exception {
        project.setName(name);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject != null) {
                String projectName = jsonObject.getString("name");
                if (name.equals(projectName)) {
                    String id = jsonObject.getString("id");
                    String lock = jsonObject.getString("lockin");
                    project.setId(id);
                    project.setLockin(lock);
                }
            }
        }
    }

    @When("^项目--选择 \"([^\"]*)\" 点击删除项目按钮$")
    public void deleteProject(String name) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        project.setName(name);

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/delete";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        deleteResultMessage = result.getString("message");
    }

    @And("^项目--输入 \"([^\"]*)\" 和 \"([^\"]*)\"$")
    public void insertProjectInfo(String name, String description) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        project = new ProjectEntity();
        project.setId(uuid());
        project.setName(name);
        project.setDescription(description);
        project.setProgressBar("0%");
        project.setProgress(ProjectConstant.NOT_START);
        project.setLockin(ProjectConstant.UNLOCK);
        project.setCreateTime(new Date());
        project.setUpdateTime(new Date());

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/insertSelective";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        createResultMessage = result.getString("message");
    }

    @And("^项目--修改 \"([^\"]*)\" 和 \"([^\"]*)\"$")
    public void updateProjectInfo(String description, String progress) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        project.setDescription(description);
        project.setProgress(progress);

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/updateSelective";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        updateResultMessage = result.getString("message");
    }

    @And("^项目--点击确定按钮$")
    public void confirmSubmit() {

    }

    @And("^项目--确认删除操作$")
    public void confirmDelete() {

    }

    @And("^项目--查看项目 \"([^\"]*)\"$")
    public void findProject(String name) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        ProjectEntity project = new ProjectEntity();
        project.setName(name);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject != null) {
                String projectName = jsonObject.getString("name");
                if (name.equals(projectName)) {
                    String id = jsonObject.getString("id");
                    project.setId(id);
                }
            }
        }

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/selectOne";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        queryResultMessage = result.getString("message");
    }

    @And("^项目--打开功能结构管理菜单$")
    public void openFStructure() throws Exception {
        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        ProjectEntity project = new ProjectEntity();

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(project);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/project/findList";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        loadListResultMessage = result.getString("message");
        jsonArray = result.getJSONArray("data");
    }

    @Then("^项目--返回新建项目执行结果 \"([^\"]*)\"$")
    public void verifyCreateProjectResult(String result) {
        Assert.assertEquals(createResultMessage, result);
    }

    @Then("^项目--返回查看项目执行结果 \"([^\"]*)\"$")
    public void verifyQueryProjectResult(String result) {
        Assert.assertEquals(queryResultMessage, result);
    }

    @Then("^项目--返回锁定项目执行结果 \"([^\"]*)\"$")
    public void verifyLockProjectResult(String result) {
        Assert.assertEquals(lockResultMessage, result);
    }

    @Then("^项目--返回修改项目执行结果 \"([^\"]*)\"$")
    public void verifyUpdateProjectResult(String result) {
        Assert.assertEquals(updateResultMessage, result);
    }

    @Then("^项目--返回删除项目执行结果 \"([^\"]*)\"$")
    public void verifyDeleteProjectResult(String result) {
        Assert.assertEquals(deleteResultMessage, result);
    }

    @Then("^项目--返回获取项目列表执行结果:$")
    public void loadProjectListResult(List<String> result) {
        Assert.assertEquals(loadListResultMessage, result.get(0));
    }

    private static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
