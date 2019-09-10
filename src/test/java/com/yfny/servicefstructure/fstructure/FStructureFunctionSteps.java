package com.yfny.servicefstructure.fstructure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yfny.servicefstructure.base.APIBaseTest;
import com.yfny.servicefstructure.constant.FunctionConstant;
import com.yfny.servicefstructure.entity.FunctionEntity;
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
public class FStructureFunctionSteps extends APIBaseTest {

    boolean menuPermission = false; //菜单权限
    boolean projectPermission = false;  //项目权限
    String createResultMessage = "";   //创建结果
    String updateResultMessage = "";   //修改结果
    String lockResultMessage = "";  //锁定结果
    String queryResultMessage = "";   //查看结果
    String loadListResultMessage = "";  //加载列表结果
    String deleteResultMessage = "";    //删除结果
    JSONArray jsonArray = new JSONArray();//列表数据
    FunctionEntity function = new FunctionEntity();


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Given("^功能--登录用户拥有项目权限$")
    public void projectPermission() {
//        if (userName.equals("admin")) {
//            menuPermission = true;
//        }
//        Assert.assertEquals(menuPermission, true);
    }

    @Given("^功能--用户 \"([^\"]*)\" 拥有项目权限$")
    public void projectPermission(String userName) {
        function = new FunctionEntity();
        function.setUserName(userName);
    }

    @Given("^功能--动作执行成功$")
    public void success() throws Exception {
        //Assert.assertEquals(createResult, true);
    }

    @When("^功能--右键点击新建功能按钮$")
    public void createProject() throws Exception {

    }

    @When("^功能--选择 \"([^\"]*)\" 点击锁定功能按钮$")
    public void lockProject(String name) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        function.setName(name);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject != null) {
                String functionName = jsonObject.getString("name");
                if (name.equals(functionName)) {
                    String id = jsonObject.getString("id");
                    function.setId(id);
                }
            }
        }

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/lock";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        lockResultMessage = result.getString("message");
    }

    @When("^功能--选择 \"([^\"]*)\" 点击修改功能按钮$")
    public void updateProject(String name) throws Exception {
        function.setName(name);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject != null) {
                String functionName = jsonObject.getString("name");
                if (name.equals(functionName)) {
                    String id = jsonObject.getString("id");
                    String lock = jsonObject.getString("lockin");
                    function.setId(id);
                    function.setLockin(lock);
                }
            }
        }
    }

    @When("^功能--选择 \"([^\"]*)\" 点击删除功能按钮$")
    public void deleteProject(String name) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        function.setName(name);

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/delete";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        deleteResultMessage = result.getString("message");
    }

    @And("^功能--输入 \"([^\"]*)\" 和 \"([^\"]*)\"$")
    public void insertProjectInfo(String name, String description) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        function = new FunctionEntity();
        function.setId(uuid());
        function.setProjectId("1");
        function.setName(name);
        function.setDescription(description);
        function.setProgressBar("0%");
        function.setProgress(FunctionConstant.NOT_START);
        function.setLockin(FunctionConstant.UNLOCK);
        function.setCreateTime(new Date());
        function.setUpdateTime(new Date());

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/insertSelective";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        createResultMessage = result.getString("message");
    }

    @And("^功能--修改 \"([^\"]*)\" 和 \"([^\"]*)\"$")
    public void updateProjectInfo(String description, String progress) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        function.setDescription(description);
        function.setProgress(progress);

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/updateSelective";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        updateResultMessage = result.getString("message");
    }

    @And("^功能--点击确定按钮$")
    public void confirm() {

    }

    @And("^功能--查看功能 \"([^\"]*)\"$")
    public void findProject(String name) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        FunctionEntity function = new FunctionEntity();
        function.setName(name);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject != null) {
                String functionName = jsonObject.getString("name");
                if (name.equals(functionName)) {
                    String id = jsonObject.getString("id");
                    function.setId(id);
                }
            }
        }

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/selectOne";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        queryResultMessage = result.getString("message");
    }

    @And("^功能--打开选中的可操作项目$")
    public void openProject() throws Exception {
        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        FunctionEntity function = new FunctionEntity();

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/findList";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        loadListResultMessage = result.getString("message");
        jsonArray = result.getJSONArray("data");
    }

    @Then("^功能--返回新建功能执行结果 \"([^\"]*)\"$")
    public void verifyCreateProjectResult(String result) {
        Assert.assertEquals(createResultMessage, result);
    }

    @Then("^功能--返回查看功能执行结果 \"([^\"]*)\"$")
    public void verifyQueryProjectResult(String result) {
        Assert.assertEquals(queryResultMessage, result);
    }

    @Then("^功能--返回锁定功能执行结果 \"([^\"]*)\"$")
    public void verifyLockProjectResult(String result) {
        Assert.assertEquals(lockResultMessage, result);
    }

    @Then("^功能--返回修改功能执行结果 \"([^\"]*)\"$")
    public void verifyUpdateProjectResult(String result) {
        Assert.assertEquals(updateResultMessage, result);
    }

    @Then("^功能--返回删除功能执行结果 \"([^\"]*)\"$")
    public void verifyDeleteProjectResult(String result) {
        Assert.assertEquals(deleteResultMessage, result);
    }

    @Then("^功能--返回打开项目执行结果:$")
    public void loadFunctionListResult(List<String> result) {
        Assert.assertEquals(loadListResultMessage, result.get(0));
    }

    private static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
