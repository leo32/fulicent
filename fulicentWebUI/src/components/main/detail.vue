<template>
  <el-row class="wrapper zk-detail">
    <div class="wrapper-l zk-main">
      <p class="bread-area">
        您的位置：<a href="http://www.fulicent.com">福利省钱</a> &nbsp;&gt;&nbsp;
        <a :href="product.categoryId">{{product.categoryId}}</a>
        &nbsp;&gt;&nbsp;{{product.name}} </p>
      <div class="zk-content" _hover-ignore="1">
        <div class="img-area">
          <img :src="product.image" :alt="product.name">
        </div>
        <div class="info-area">
          <h1 class="title elli">
            <span>包邮</span>{{product.name}} </h1>
          <p class="endtime" id="eventTimeStr">优惠券即将失效:&nbsp;<em>2</em>天<em>7</em>时<em>7</em>分<em>17</em>秒</p>
          <input id="endTime" value="1560787199" type="hidden">

          <div class="platform" _hover-ignore="1">
            <i style="background-image: url(/static/index/images/platform_tmall.png);"></i>
            天猫
          </div>
          <div class="stat" _hover-ignore="1">
            <p class="price-area">
              <span class="ori-price">现价：¥{{product.price}}</span>
              <span class="price"><i>券后价</i><em class="decimal">¥</em><em class="int">{{product.discount}}</em></span>
            </p>
            <div class="buy-area" _hover-ignore="1">
              <p class="desc">有效期内领券下单，享受立减优惠！</p>
              <a :href="product.links" target="_blank" rel="nofollow" class="buy-btn">
                <div class="line line-l"><i></i><i></i><i></i><i></i><i></i><i></i><i></i></div>
                <div class="line line-r"><i></i><i></i><i></i><i></i><i></i><i></i><i></i></div>
                领券立减<em>5</em>元
              </a>
            </div>
          </div>

          <div class="tags">
            <div class="tag-list">
              <a class="tag-item" href="/index/so/index/wd/%E8%B4%B4%E8%86%9C.html">
                贴膜 </a>
              <a class="tag-item" href="/index/so/index/wd/%E8%93%9D%E5%85%89.html">
                蓝光 </a>
              <a class="tag-item" href="/index/so/index/wd/%E8%8B%B9%E6%9E%9C.html">
                苹果 </a>
              <a class="tag-item" href="/index/so/index/wd/%E6%9B%B2%E9%9D%A2.html">
                曲面 </a>
              <a class="tag-item" href="/index/so/index/wd/%E7%A3%A8%E7%A0%82.html">
                磨砂 </a>
            </div>
            <p class="coll"><i></i>按<em>Ctrl&nbsp;+&nbsp;D</em>加入收藏</p>
          </div>
        </div>
      </div>
    </div>
    <div class="wrapper-r">
      <div class="rel-zk-area" style="margin-top: 60px;">
        <p class="head">
          <span>最近浏览</span>
          <a href="/index/history/index.html" class="more-his">查看更多
          </a>
        </p>
        <div class="hot-zk-list clearfix swiper-container swiper-container-horizontal">
          <div style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);" class="swiper-wrapper">
            <div style="width: 262px;" class="swiper-slide swiper-slide-active">
              <a class="zk-img-item" title="家用插座转换器一转二三四多功能转换插头usb插排插板带夜灯无线"
                href="/index/goods/index/id/589507099355.html">
                <img style="opacity: 1;"
                  src="https://gd3.alicdn.com/imgextra/i3/2266396741/O1CN01uAQpyo1zfQO0GneKh_!!2266396741.jpg"
                  class="lazy"
                  data-original="https://gd3.alicdn.com/imgextra/i3/2266396741/O1CN01uAQpyo1zfQO0GneKh_!!2266396741.jpg"
                  alt="家用插座转换器一转二三四多功能转换插头usb插排插板带夜灯无线">
                <p class="fixed-bottom">即将失效</p>
              </a>
              <a class="zk-img-item" title="iphone6钢化水凝膜苹果6s抗蓝光护眼6plus原装曲面全屏覆盖苹果7水凝8p手机磨砂贴膜前膜全包边贴膜7p保护膜"
                href="/index/goods/index/id/571900197140.html">
                <img style="opacity: 1;" src="http://img.haodanku.com/oimg_571900197140_1558109480.jpg" class="lazy"
                  data-original="http://img.haodanku.com/oimg_571900197140_1558109480.jpg"
                  alt="iphone6钢化水凝膜苹果6s抗蓝光护眼6plus原装曲面全屏覆盖苹果7水凝8p手机磨砂贴膜前膜全包边贴膜7p保护膜">
                <p class="fixed-bottom">即将失效</p>
              </a>
              <a class="zk-img-item" title="汽车后视镜防雨贴膜倒车镜防雨膜通用眩目全屏雾神器反光镜防水膜"
                href="/index/goods/index/id/584734897791.html">
                <img style="opacity: 1;"
                  src="https://img.alicdn.com/imgextra/i2/752144829/O1CN01rgr2O11lXj8HRrRQI_!!752144829.jpg"
                  class="lazy"
                  data-original="https://img.alicdn.com/imgextra/i2/752144829/O1CN01rgr2O11lXj8HRrRQI_!!752144829.jpg"
                  alt="汽车后视镜防雨贴膜倒车镜防雨膜通用眩目全屏雾神器反光镜防水膜">
                <p class="fixed-bottom">即将失效</p>
              </a>
              <a class="zk-img-item" title="泰国white芦荟胶去黑头水撕拉式猪鼻贴膜祛粉刺收缩毛孔套装男女"
                href="/index/goods/index/id/536069891909.html">
                <img style="opacity: 1;"
                  src="https://img.alicdn.com/imgextra/i4/1079320886/O1CN01pIi43A1IPpPiJbDyi_!!1079320886.jpg"
                  class="lazy"
                  data-original="https://img.alicdn.com/imgextra/i4/1079320886/O1CN01pIi43A1IPpPiJbDyi_!!1079320886.jpg"
                  alt="泰国white芦荟胶去黑头水撕拉式猪鼻贴膜祛粉刺收缩毛孔套装男女">
                <p class="fixed-bottom">即将失效</p>
              </a>
              <a class="zk-img-item" title="纯色简约抱枕靠垫汽车车内靠背办公室午睡腰靠床头腰枕客厅靠枕"
                href="/index/goods/index/id/581664804866.html">
                <img style="opacity: 1;"
                  src="https://img.alicdn.com/imgextra/i3/715238540/O1CN01HfLbO32CxMtslG3xh_!!715238540.jpg"
                  class="lazy"
                  data-original="https://img.alicdn.com/imgextra/i3/715238540/O1CN01HfLbO32CxMtslG3xh_!!715238540.jpg"
                  alt="纯色简约抱枕靠垫汽车车内靠背办公室午睡腰靠床头腰枕客厅靠枕">
                <p class="fixed-bottom">即将失效</p>
              </a>

            </div>

          </div>
        </div>
      </div>
    </div>
  </el-row>
</template>
<script>
  import {
    getProduct
  } from "@/api/products";
  import datacenterBus from "@/api/datacenterBus";
  export default {
    data() {
      return {
        product: {}
      };
    },
    mounted() {
      var self = this;
      let id = self.$route.params.id;
      self.bindProduct(id);
    },
    methods: {
      bindProduct(id) {
        getProduct(id).then(response => {
          this.product = response.data;
        });
      }
    }
  };

</script>
