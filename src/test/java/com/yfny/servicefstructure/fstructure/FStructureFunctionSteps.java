package com.yfny.servicefstructure.fstructure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yfny.servicefstructure.base.APIBaseTest;
import com.yfny.servicefstructure.constant.FunctionConstant;
import com.yfny.servicefstructure.entity.ContentEntity;
import com.yfny.servicefstructure.entity.ExampleEntity;
import com.yfny.servicefstructure.entity.FunctionEntity;
import com.yfny.servicefstructure.entity.PanelEntity;
import com.yfny.utilscommon.basemvc.common.BaseEntity;
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
    public void functionPermission() {
//        if (userName.equals("admin")) {
//            menuPermission = true;
//        }
//        Assert.assertEquals(menuPermission, true);
    }

    @Given("^功能--用户 \"([^\"]*)\" 拥有项目权限$")
    public void functionPermission(String userName) {
        function = new FunctionEntity();
        function.setUserName(userName);
    }

    @Given("^功能--动作执行成功$")
    public void success() throws Exception {
        //Assert.assertEquals(createResult, true);
    }

    @When("^功能--右键点击新建功能按钮$")
    public void createFunction() throws Exception {

    }

    @When("^功能--选择 \"([^\"]*)\" 右键点击锁定功能按钮$")
    public void lockFunction(String name) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        function.setName(name);
        this.setParam();

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/lock";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        lockResultMessage = result.getString("message");
    }

    @When("^功能--选择 \"([^\"]*)\" 右键点击修改功能按钮$")
    public void updateFunction(String name) throws Exception {
        function.setName(name);
        this.setParam();
    }

    @When("^功能--选择 \"([^\"]*)\" 右键点击删除功能按钮$")
    public void deleteFunction(String name) throws Exception {
        function.setName(name);
        function.setAction(BaseEntity.DELETE);
    }

    @When("^功能--查看功能 \"([^\"]*)\" 详细信息$")
    public void findFunctionDetail(String name) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        function.setName(name);
        function.setAction(BaseEntity.SELECT);

        this.setParam();

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/selectComplexById";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        queryResultMessage = result.getString("message");
    }

    @When("^功能--点击新建场景面板按钮$")
    public void createPanel() throws Exception {

    }

    @And("^功能--输入 \"([^\"]*)\" 和 \"([^\"]*)\"$")
    public void insertFunctionInfo(String name, String description) throws Exception {

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
        function.setAction(BaseEntity.INSERT);

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
    public void updateFunctionInfo(String description, String progress) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        function.setDescription(description);
        function.setProgress(progress);
        function.setAction(BaseEntity.UPDATE);

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
    public void confirmSubmit() {

    }

    @And("^功能--确认删除操作$")
    public void confirmDelete() throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/delete";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        deleteResultMessage = result.getString("message");
    }

    @And("^功能--查看功能 \"([^\"]*)\" 基础信息$")
    public void findFunction(String name) throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        FunctionEntity function = new FunctionEntity();
        function.setName(name);
        function.setAction(BaseEntity.SELECT);

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
    public void openFunction() throws Exception {
        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

        FunctionEntity function = new FunctionEntity();

        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/getTreeOf";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        loadListResultMessage = result.getString("message");
        JSONObject data = result.getJSONObject("data");
        jsonArray = data.getJSONArray("list");
    }

    @And("^功能--选择类型 场景类型 输入 场景名称 和 场景描述$")
    public void inputPanel(List<PanelEntity> panelList) {
        for (PanelEntity panel : panelList) {
            panel.setFunctionId(function.getId());
            panel.setAnnotation("@Test");
            panel.setCreateTime(new Date());
            panel.setUpdateTime(new Date());
            panel.setSort(0);
            panel.setAction(BaseEntity.INSERT);
        }
        function.setPanelList(panelList);
    }

    @And("^功能--填写场景执行内容$")
    public void inputContent(List<ContentEntity> contentList) {
        List<PanelEntity> panelList = function.getPanelList();
        for (ContentEntity content : contentList) {
            content.setFunctionId(function.getId());
            content.setSort(0);
            content.setCreateTime(new Date());
            content.setUpdateTime(new Date());
            content.setAction(BaseEntity.INSERT);
            for (PanelEntity panel : panelList) {
                if (content.getParam1().equals(panel.getName())) {
                    if (panel.getContentList() == null) {
                        panel.setContentList(new ArrayList<>());
                    }
                    panel.getContentList().add(content);
                }
            }
        }
    }

    @And("^功能--填写场景大纲执行示例$")
    public void inputExample(List<ExampleEntity> exampleList) {
        List<PanelEntity> panelList = function.getPanelList();
        for (ExampleEntity example : exampleList) {
            example.setFunctionId(function.getId());
            example.setSort(0);
            example.setCreateTime(new Date());
            example.setUpdateTime(new Date());
            example.setAction(BaseEntity.INSERT);
            for (PanelEntity panel : panelList) {
                List<ContentEntity> contentList = panel.getContentList();
                for (ContentEntity content : contentList) {
                    if (example.getParam1().equals(content.getContent())) {
                        if (content.getExampleList() == null) {
                            content.setExampleList(new ArrayList<>());
                        }
                        content.getExampleList().add(example);
                    }
                }
            }
        }
    }

    @And("^功能--点击保存按钮$")
    public void confirmSave() throws Exception {

        /*--------------------开始业务组装--------------------*/

        Map<String, String> paramsMap = new HashMap<>();

//        FunctionEntity function = new FunctionEntity();
//
//        List<ContentEntity> contentList = new ArrayList<>();
//        ContentEntity scenario = new ContentEntity();
//        scenario.setFunctionId("44");
//        scenario.setKeyword("Given");
//        scenario.setContent("12345");
//        scenario.setSort(0);
//        scenario.setCreateTime(new Date());
//        scenario.setUpdateTime(new Date());
//        scenario.setAction(BaseEntity.INSERT);
//        contentList.add(scenario);
//
//        List<PanelEntity> panelList = new ArrayList<>();
//        PanelEntity panel = new PanelEntity();
//        //panel.setFunctionId("44");
//        panel.setName("场景面板");
//        panel.setType("BACKGROUND");
//        panel.setSort(0);
//        panel.setCreateTime(new Date());
//        panel.setUpdateTime(new Date());
//        panel.setContentList(contentList);
//        panel.setAction(BaseEntity.INSERT);
//
//        panelList.add(panel);
//
//        function.setId("44");
//        function.setAction(BaseEntity.UPDATE);
//        function.setUserName("管理员");
//        function.setPanelList(panelList);


        //转换成ajax请求的json数据
        String content = JSONObject.toJSONString(function);

        /*--------------------业务组装结束--------------------*/

        //指定要请求的接口路径
        String url = "/function/updateSelective";

        //模拟页面请求
        JSONObject result = postRequest(url, content);
        updateResultMessage = result.getString("message");
    }

    @Then("^功能--返回新建功能执行结果 \"([^\"]*)\"$")
    public void verifyCreateResult(String result) {
        Assert.assertEquals(createResultMessage, result);
    }

    @Then("^功能--返回查看功能执行结果 \"([^\"]*)\"$")
    public void verifyQueryResult(String result) {
        Assert.assertEquals(queryResultMessage, result);
    }

    @Then("^功能--返回锁定功能执行结果 \"([^\"]*)\"$")
    public void verifyLockResult(String result) {
        Assert.assertEquals(lockResultMessage, result);
    }

    @Then("^功能--返回修改功能执行结果 \"([^\"]*)\"$")
    public void verifyUpdateResult(String result) {
        Assert.assertEquals(updateResultMessage, result);
    }

    @Then("^功能--返回删除功能执行结果 \"([^\"]*)\"$")
    public void verifyDeleteResult(String result) {
        Assert.assertEquals(deleteResultMessage, result);
    }

    @Then("^功能--返回打开项目执行结果:$")
    public void loadListResult(List<String> result) {
        Assert.assertEquals(loadListResultMessage, result.get(0));
    }

    @Then("^功能--返回保存功能执行结果 \"([^\"]*)\"$")
    public void verifySaveResult(String result) {

    }

    private static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private void setParam() {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject != null) {
                String functionName = jsonObject.getString("name");
                if (function.getName().equals(functionName)) {
                    String id = jsonObject.getString("id");
                    String lock = jsonObject.getString("lockin");
                    function.setId(id);
                    function.setLockin(lock);
                }
            }
        }
    }

}
