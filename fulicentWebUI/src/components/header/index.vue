<template>
  <el-row class="header">
    <el-row class="header-bar">
      <el-col :span="24" class="header-bar">
        <p _hover-ignore="1">
          请按键盘
          <strong>CTRL + D</strong> 把Fulicent优惠券放入收藏夹，折扣信息一手掌握！
          <label
            id="nlraf"
            onclick="CloseNLRAF(true)"
            for="check_nlraf"
          >
            <input style="display:none;" type="checkbox" id="check_nlraf">
            <a href="javascript:void(0)" _hover-ignore="1">不再提醒</a>
          </label>
          <a id="cafp" href="javascript:void(0)" onclick="CloseNLRAF(false)"></a>
          <a id="cafp" href="javascript:void(0)" onclick="CloseNLRAF(false)">
            <span class="closet">
              <em>x</em>关闭
            </span>
          </a>
        </p>
      </el-col>
    </el-row>
    <el-row class="header-top">
      <el-col :span="24">
        <el-row class="wrapper">
          <el-col :span="4">
            <a href="http://www.fulicent.com" class="logo-area">
              <img
                class="logo"
                alt="Fulicent"
                src=""
              >
            </a>
          </el-col>
          <el-col :span="2" class="slogan-area">正品好货</el-col>
          <el-col :span="2" class="slogan-area">人工精选</el-col>
          <el-col :span="2" class="slogan-area">内部领券</el-col>
          <el-col :span="2" class="slogan-area">全场包邮</el-col>
          <el-col :span="6" class="select-area">
            <form name="soForm" action="/index/so/index.html" method="get" id="soform">
              <el-col :span="2" class="select-area selected">本站</el-col>
              <el-col :span="10" class="input-area">
                <input
                  type="text"
                  id="keyword"
                  autocomplete="off"
                  onblur="this.value==''?this.value=this.title:null"
                  onfocus="this.value==this.title?this.value='':null"
                  title="输入关键词或淘宝链接"
                  placeholder="输入关键词"
                  value
                  class="search-input"
                  name="wd"
                >
               
              </el-col>
               <a href="javascript:;" class="search-btn">搜索</a>
            </form>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-row :span="24" class="tab-area-plh">
      <el-col class="tab-area">
        <div class="wrapper">
          <div class="cate-area">
            <a :class="classType?'cate-item':'cate-item jxtj active'" href="/" _hover-ignore="1">
              <span class="cate-rec">精选推荐</span>
            </a>
            <a :class="classType=='top'?'cate-item jxtj active':'cate-item'" href="/#/top/" _hover-ignore="1">超级人气榜</a>
            <a :class="classType=='recommend'?'cate-item jxtj active':'cate-item'" href="/#/recommend" _hover-ignore="1">特卖精选</a>
            <a :class="classType=='brand'?'cate-item jxtj active':'cate-item'" href="/#/brand"  _hover-ignore="1">品牌优惠券</a>
            <a :class="classType=='my'?'cate-item jxtj active':'cate-item'" href="/#/my"  _hover-ignore="1">我的足迹</a>
          </div>
          <div
            style="line-height:40px;float:right;color:#FFF; right;margin-top:-40px;margin-right:20px;font-size:15px"
          >
            <a
              style="line-height:40px;color:#FFF;margin-right:10px;"
              href="/index/user/register.html"
            >注册</a>
            <a style="line-height:40px;color:#FFF;" href="/index/user/login.html">登录</a>
          </div>
        </div>
      </el-col>
    </el-row>
  </el-row>
</template>
<script>
export default {
  data() {
    return {};
  },
  props:['classType'],
  methods: {},
  components: {},
  watch: {},
  computed: {},
  created() {},
  mounted() {}
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
}

.header .tab-area-plh {
  position: relative;
  height: 40px;
}
.header-bar {
  position: relative;
  height: 36px;
  background-color: #f4f4f4;
  color: #444;
  font-size: 13px;
  line-height: 36px;
  text-align: center;
  position: relative;
  margin: 0 auto;
}
</style>