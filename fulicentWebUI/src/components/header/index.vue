<template>
  <el-row class="header">
    <el-row class="header-bar">
      <el-col :span="24" class="header-bar">
        <p _hover-ignore="1">
          请按键盘
          <strong>CTRL + D</strong> 把Fulicent优惠券放入收藏夹，折扣信息一手掌握！
          <label id="nlraf" onclick="CloseNLRAF(true)" for="check_nlraf">
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
      <div class="wrapper">
        <a href="http://fulicent.com" class="logo-area">
          <img class="logo" alt="福利省钱网" src=" ">
        </a>
        <div class="slogan-area">
          <i class="slogan-icon cate-icon"></i>
          <p>正品好货</p>
          <i class="slogan-icon cate-icon"></i>
          <p>人工精选</p>
          <i class="slogan-icon cate-icon"></i>
          <p>内部领券</p>
          <i class="slogan-icon cate-icon"></i>
          <p>全场包邮 </p>
        </div>
        <div class="search-area">
          <el-form name="soForm" @submit.native.prevent>
            <div class="select-area">
              <div class="select-area-img"></div>
              <div class="selected " data-id="0">本站 </div>
              <div class="a" data-id="1">全网 </div>
            </div>
            <div class="input-area">
              <i>
              </i>

              <el-input type="text" v-model="keyword" title="输入关键词或淘宝链接" placeholder="输入关键词"
                @keyup.enter.native="searchEnterFun" value=""></el-input>

            </div>
            <a :href="'/#/?keyword='+EncodeUrl()" class="search-btn" target="_blank">搜索</a>
          </el-form>
        </div>
        <div id="suggest" class="suggest"></div>
      </div>
    </el-row>
    <el-row :span="24" class="tab-area-plh">
      <el-col class="tab-area">
        <div class="wrapper">
          <div class="cate-area">
            <a :class="classType?'cate-item':'cate-item jxtj active'" href="/" _hover-ignore="1">
              <span class="cate-rec">精选推荐</span>
            </a>
            <a :class="classType=='top'?'cate-item jxtj active':'cate-item'" href="/#/top" _hover-ignore="1">超级人气榜</a>
            <a :class="classType=='recommend'?'cate-item jxtj active':'cate-item'" href="/#/recommend"
              _hover-ignore="1">特卖精选</a>
            <a :class="classType=='brand'?'cate-item jxtj active':'cate-item'" href="/#/brand"
              _hover-ignore="1">品牌优惠券</a>
            <a :class="classType=='my'?'cate-item jxtj active':'cate-item'" href="/#/my" _hover-ignore="1">我的足迹</a>
          </div>
          <div style="line-height:40px;float:right;color:#FFF; right;margin-top:-40px;margin-right:20px;font-size:15px">
            <span ref="userInfo"> 
              <a style="line-height:40px;color:#FFF;margin-right:10px;" href="/#/register">注册</a>
              <a style="line-height:40px;color:#FFF;" href="/#/login">登录</a>
            </span>
            <a ref="logout" style="line-height:40px;color:#FFF;display:none;" href="javascript:void(0)" @click="logout">退出</a>
          </div>
        </div>
      </el-col>
    </el-row>
  </el-row>
</template>
<script>
  import {
    getToken,
    removeToken
  } from "@/utils/cookies"
  export default {
    data() {
      return {
        keyword: ''
      }
    },
    props: ['classType'],
    components: {},
    watch: {

    },
    computed: {},
    methods: {
      searchEnterFun() {
        window.open('/#/?keyword=' + this.EncodeUrl());
      },
      EncodeUrl() {
        return encodeURIComponent(this.keyword)
      },
      loadUserData() {
        var userInfo = getToken();
        if (userInfo != undefined) {
          this.$refs.userInfo.innerHTML = "欢迎 " + userInfo
          this.$refs.logout.style = 'line-height:40px;color:#FFF;';
        }
      },
      logout() {
        removeToken()
        window.location.reload()
      }
    },
    mounted() {
      this.loadUserData()
    }
  }

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
