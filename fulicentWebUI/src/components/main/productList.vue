<template>
  <el-row class="wrapper home-wrapper">
    <el-row class="zk-list clearfix">
      <div class="zk-item" v-for="item in products" :key="item.id">
        <div class="img-area">
          <div class="lq">
            <a
              :href="item.links"
              target="_blank"
              rel="nofollow"
            >
              <div class="lq-t">
                <p class="lq-t-d1">领优惠券</p>
                <p class="lq-t-d2">
                  省
                  <span>30</span>元
                </p>
              </div>
              <div class="lq-b"></div>
            </a>
          </div>

          <div class="bottom-info">
            <p class="time-count" data-endtime="1560095999">
              <i class="cate-icon" style="font-size:12px"></i> 还剩&nbsp;
              <em>11</em>时
              <em>23</em>分
              <em>50</em>秒&nbsp;结束
            </p>
          </div>
          <a
            :href="item.links"
            target="_blank"
            :title="item.name"
          >
            <img
              :alt="item.name"
              :data-original="item.image"
              class="lazy"
              :src="item.image"
              style="opacity: 1;"
            >
          </a>
        </div>
        <p class="title-area elli">
          <span class="post-free">包邮</span>
          {{item.name}}
        </p>
        <div class="raw-price-area">
          现价：¥{{item.price}}
          <p class="sold">已领 19 张券</p>
        </div>
        <div class="info">
          <div class="price-area">
            <span class="price">
              ¥
              <em class="number-font" style="font-size: 26px;">{{item.discount}}</em>
              <i></i>
            </span>
          </div>
          <div class="buy-area">
            <a
              :href="item.links"
              target="_blank"
              rel="nofollow"
            >
              <span class="coupon-amount">去天猫</span>
              <span class="btn-title">火速领券</span>
            </a>
          </div>
          <div class="platform-area">
            <img src="/static/index/images/platform_tmall.png">天猫
          </div>
        </div>
      </div>
    </el-row>
    <el-row class="page"></el-row>
  </el-row>
</template>
<script>
import { getProducts } from "@/api/products";
import datacenterBus from "@/api/datacenterBus";
export default {
  data() {
    return { products: [] };
  },
  mounted() {
    let params;
    var self=this;
    datacenterBus.$on("getValue",function(id){
        params={sort:'CreateTime',order:'desc',categoryType:id};
        self.bindProducts(params);
    });
    
  },
  methods: {
    bindProducts(para) {
      getProducts(para).then(response => {
        this.products = response.data.productList;
      });
    }
  }
};
</script>