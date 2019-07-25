<template>
  <el-row class="wrapper home-wrapper rel-zk-area">
    <p class="head"><span>{{getTitleName(classType)}}</span></p>
    <el-row v-loading="loading">
      <ul class="zk-list clearfix">
        <li class="zk-item" v-for="item in items" :key="item.id">
          <div class="img-area">
            <div class="lq">
              <a :href="item.links" target="_blank" rel="nofollow">
                <div class="lq-t">
                  <p class="lq-t-d1">领优惠券</p>
                  <p class="lq-t-d2">
                    省
                    <span>{{item.coupon}}</span>元
                  </p>
                </div>
                <div class="lq-b"></div>
              </a>
            </div>

            <div class="bottom-info">
              <p class="time-count" data-endtime="1560095999">
                <i class="cate-icon" style="font-size:12px"></i> {{dateCountDown(item.expire)}}
              </p>
            </div>
            <a :href="'/#/detail/'+item.id" target="_blank" :title="item.name">
              <img :alt="item.name" :data-original="item.image" class="lazy" :src="item.image" style="opacity: 1;">
            </a>
          </div>
          <p class="title-area elli">
            <span class="post-free">包邮</span>
            {{item.name}}
          </p>
          <div class="raw-price-area">
            现价：¥{{item.sale}}
            <p class="sold">已领 {{item.couponRemainCount}} 张券</p>
          </div>
          <div class="info">
            <div class="price-area">
              <span class="price">
                ¥
                <em class="number-font" style="font-size: 26px;">{{item.couponPrice}}</em>
                <i></i>
              </span>
            </div>
            <div class="buy-area">
              <a :href="item.links" target="_blank" rel="nofollow">
                <span class="coupon-amount">去天猫</span>
                <span class="btn-title">火速领券</span>
              </a>
            </div>
            <div class="platform-area">
              <img src="/static/public/images/platform_tmall.png">天猫
            </div>
          </div>
        </li>
      </ul>
    </el-row>
    <div class="page">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page.sync="params.page" :page-sizes="[12,20,40,80,pagination && pagination.count]"
        :page-size="params.limit" layout="total, prev, pager, next, jumper"
        :total="pagination && pagination.count || 0" v-if="pagination.count>12?true:false">
      </el-pagination>
    </div>
  </el-row>
</template>
<script>
  import {
    getProducts,searchProducts
  } from "@/api/products";
  import datacenterBus from "@/api/datacenterBus";

  export default {
    props: ['classType'],
    data() {
      return {
        loading: false,
        pagination: {
          "skip": 0,
          "limit": 0,
          "count": 0
        },
        params: {
          keyword:'',
          tags: '',
          sort: '',
          order: '',
          type: '',
          brand: '',
          recommend: '',
          ids: '',
          limit: 12,
          skip: 0,
          page: 1
        },
        items: []
      };
    },
    mounted() {
      this.loadData()
    },
    methods: {
      loadData() {
        var self = this;
        let brandId = ""
        let tags = ""
        let keyword=""
        if (self.$route.query != undefined && self.$route.query != null) {
          brandId = self.$route.query.brandId
          tags = self.$route.query.tags
          keyword = self.$route.query.keyword
          if (brandId != undefined) {
            self.params.brand = brandId
            self.params.type = 'brand'
          }
          if (tags != undefined) {
            self.params.tags = tags
          }
          if (keyword != undefined) {
            self.params.keyword = keyword
            self.classType="search"
          }          
        }
        switch (self.classType) {
          case "top":
            self.params.type = 'top'
            self.bindProducts(self.params);
            datacenterBus.$on("getValue", function (value) {
              self.params.skip = 0
              self.params.categoryId = value
              self.bindProducts(self.params)
            });

            break;
          case "recommend":
            self.params.type = 'recommend'
            self.params.recommend = '1'
            self.bindProducts(self.params);
            datacenterBus.$on("getValue", function (value) {
              self.params.skip = 0
              self.params.categoryId = value
              self.bindProducts(self.params)
            });
            break;
          case "my":
            if (localStorage.length > 0) {
              if (localStorage.getItem('productIdList') != null) {
                var idList = localStorage.getItem('productIdList');
                self.params.skip = 0
                self.params.type = 'my'
                self.params.ids = idList
                self.bindProducts(self.params)
              }
            }
            break;
          case "guess":
            break;
          case "brand":
            self.params.type = 'brand'
            self.bindProducts(self.params);
            datacenterBus.$on("getValue", function (value) {
              self.params.skip = 0
              self.params.categoryId = value
              self.params.brand = '1'
              self.bindProducts(self.params)
            });
            break;
          case "search":
            self.params.type = 'search'
            self.bindSearchProducts(self.params);
            datacenterBus.$on("getValue", function (value) {
              self.params.skip = 0
              self.params.keyword = value
              self.bindSearchProducts(self.params)
            });
            break;
          default:
            self.bindProducts(self.params);
            datacenterBus.$on("getValue", function (value) {
              self.params.skip = 0
              self.params.sort = 'CreateTime'
              self.params.order = 'desc'
              self.params.categoryId = value
              self.params.type = self.classType
              self.bindProducts(self.params)
            });
            break;
        }
      },
      handleSizeChange(val) {
        this.params.limit = val
        this.loadData()
      },
      handleCurrentChange(val) {
        this.params.page = val
        this.params.skip = (val - 1) * this.params.limit
        this.loadData()
      },
      bindProducts(para) {
        this.loading = true
        getProducts(para).then(response => {
          this.items = response.data.productList
          this.pagination = response.pagination
        }).finally(r => {
          this.loading = false
        });
      },
      bindSearchProducts(para){
        this.loading = true
        searchProducts(para).then(response => {
          this.items = response.data.productList
          this.pagination = response.pagination
        }).finally(r => {
          this.loading = false
        });
      },
      dateCountDown(endTime) {
        if (endTime == undefined) return "优惠券已失效";
        endTime = Math.floor(new Date(endTime).setHours(24) / 1000) * 1;
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
      getTitleName(title) {
        var name;
        switch (title) {
          case "top":
            name = "超级人气榜";
            break;
          case "recommend":
            name = "特卖精选";
            break;
          case "brand":
            name = "品牌优惠券";
            break;
          case "search":
            name = "搜索结果";  
          case "guess":
            name = "猜你喜欢";
            break;
          case "my":
            name = "我的足迹";
            break;
          default:
            name = "精选推荐";
            break;
        }
        return name;
      }
    }
  };

</script>
