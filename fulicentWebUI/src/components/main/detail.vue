<template>
  <el-row class="wrapper zk-detail">
    <div class="wrapper-l zk-main">
      <p class="bread-area">
        您的位置：<a href="http://www.fulicent.com">福利省钱</a> &nbsp;&gt;&nbsp;
        <a :href="product.categoryId">{{product.categoryId}}</a>
        &nbsp;&gt;&nbsp;{{product.name}} </p>
      <div class="zk-content" _hover-ignore="1">
        <div class="img-area">
          <a :href="product.links" target="_blank" rel="nofollow" class="buy-btn">
            <img :src="product.image" :alt="product.name">
          </a>
        </div>
        <div class="info-area">
          <h1 class="title elli">
            <span>包邮</span>{{product.name}} </h1>
          <p class="endtime" id="eventTimeStr">{{dateCountDown(product.expire)}}</p>
          <input id="endTime" value="1560787199" type="hidden">

          <div class="platform" _hover-ignore="1">
            <i style="background-image: url(/static/index/images/platform_tmall.png);"></i>
            天猫
          </div>
          <div class="stat" _hover-ignore="1">
            <p class="price-area">
              <span class="ori-price">现价：¥{{product.sale}}</span>
              <span class="price"><i>券后价</i><em class="decimal">¥</em><em
                  class="int">{{product.couponPrice}}</em></span>
            </p>
            <div class="buy-area" _hover-ignore="1">
              <p class="desc">有效期内领券下单，享受立减优惠！</p>
              <a :href="product.links" target="_blank" rel="nofollow" class="buy-btn">
                <div class="line line-l"><i></i><i></i><i></i><i></i><i></i><i></i><i></i></div>
                <div class="line line-r"><i></i><i></i><i></i><i></i><i></i><i></i><i></i></div>
                领券立减<em>{{product.coupon}}</em>元
              </a>
            </div>
          </div>

          <div class="tags">
            <div class="tag-list">
              <a class="tag-item" v-for="item in tags" :key="item.index" :href="'/#/?tags='+EncodeUrl(item)">
                {{item}} </a>
            </div>
            <p class="coll"><i></i>按<em>Ctrl&nbsp;+&nbsp;D</em>加入收藏</p>
          </div>
        </div>
      </div>
    </div>
    <div class="wrapper-r" id="recentProducts">
      <div class="rel-zk-area" style="margin-top: 60px;">
        <p class="head">
          <span>最近浏览</span>
          <a href="/my" class="more-his">查看更多
          </a>
        </p>
        <div class="hot-zk-list clearfix swiper-container swiper-container-horizontal">
          <div class="swiper-wrapper">
            <div style="width: 262px;" class="swiper-slide swiper-slide-active">
              <a class="zk-img-item" v-for="item in myProducts" :key="item.id" :title="item.name"
                :href="'/detail/'+item.id" target="_blank">
                <img :alt="item.name" :data-original="item.image" class="lazy" :src="item.image" style="opacity: 1;">
                <p class="fixed-bottom">{{item.name}}</p>
              </a>
            </div>

          </div>
        </div>
      </div>
    </div>
    <div class="wrapper home-wrapper rel-zk-area el-row" ref="productDetails" style="padding-top:15px;">
      <p class="head"><span>商品详情</span></p>
      <div class="el-row">
        <ul class="zk-list clearfix">
          <div style="margin-top:5px;margin-left:5px;">
            <a :href="product.links" target="_blank" rel="nofollow" class="buy-btn">
              <img  v-for="item in smallImages" :key="item.index" :src="item" :data-original="item" style="opacity: 1;margin-bottom: 5px;">
            </a>
            </div>
        </ul>
      </div>
    </div>
  </el-row>
</template>
<script>
  import {
    getProduct,
    getMyProducts
  } from "@/api/products";
  import {
    encodeString
  } from "@/utils/common";
  import datacenterBus from "@/api/datacenterBus";
  export default {
    name: 'async',
    metaInfo() {
      return {
        title: this.pageName
      }
    },
    data() {
      return {
        product: {},
        tags: [],
        smallImages:[],
        myProducts: {},
        pageName: ''
      };
    },
    mounted() {
      var self = this;
      let id = self.$route.params.id;
      self.bindProduct(id);
      var ids = "";
      if (localStorage.length > 0) {
        if (localStorage.getItem('productIdList') != null) {
          var idList = localStorage.getItem('productIdList');
          var params = {
            skip: '0',
            limit: '4'
          };
          this.bindMyProducts(idList, params);
          if (idList.indexOf(id + ",") > -1) {
            idList = idList.replace(new RegExp(id + ",", 'g'), "");
          }
          ids = id + "," + idList;
        }
      } else {
        ids = id + ",";
        var params = {
          skip: '0',
          limit: '4'
        };
        this.bindMyProducts(id, params);
      }
      localStorage.setItem('productIdList', ids);
    },
    methods: {
      bindProduct(id) {
        getProduct(id).then(response => {
          this.product = response.data;
          if (this.product.tag != undefined) {
            this.tags = this.product.tag.split('/')
          }
          if (this.product.smallImages != undefined && this.product.smallImages != "{}") {
            var JsonString =  JSON.parse(this.product.smallImages)
            if(JsonString.string!= undefined){
              this.smallImages=JsonString.string
            }
          }else{
            this.$refs['productDetails'].style ='display:none';
          }
          if (this.product.name != undefined) {
            this.pageName = this.product.name
          }
        });
      },
      bindMyProducts(ids, params) {
        getMyProducts(ids, params).then(response => {
          this.myProducts = response.data.productList;
        });
      },
      dateCountDown(endTime) {
        if (endTime == undefined) return "优惠券已失效";
        endTime = Math.floor(new Date(endTime).getTime() / 1000) * 1;
        var now = Math.floor(new Date().getTime() / 1000) * 1;
        var timeStr = "";
        if (now > endTime) {
          timeStr = '优惠券已失效';
        } else {
          var gap = endTime - now;
          var dd = Math.floor(gap / (60 * 60 * 24));
          var hh = Math.floor((gap - dd * 60 * 60 * 24) / (60 * 60));
          var mm = Math.floor((gap - dd * 60 * 60 * 24 - hh * 60 * 60) / 60);
          var ss = gap - dd * 60 * 60 * 24 - hh * 60 * 60 - mm * 60;
          timeStr = "还剩 " + (dd > 0 ? ' ' + dd + ' 天' : '') +
            (hh > 0 ? ' ' + hh + ' 时' : '') +
            (mm > 0 ? ' ' + mm + ' 分' : '') +
            (ss >= 0 ? ' ' + ss + ' 秒' : '') + " 结束";
        }
        return timeStr;
      },
      EncodeUrl(params) {
        return encodeURIComponent(params)
      }
    }
  };

</script>
