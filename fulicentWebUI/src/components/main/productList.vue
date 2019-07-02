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
                    <span>{{item.discount}}</span>元
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
            <a :href="['/#/detail/'+item.id]" target="_blank" :title="item.name">
              <img :alt="item.name" :data-original="item.image" class="lazy" :src="item.image" style="opacity: 1;">
            </a>
          </div>
          <p class="title-area elli">
            <span class="post-free">包邮</span>
            {{item.name}}
          </p>
          <div class="raw-price-area">
            现价：¥{{item.sale}}
            <p class="sold">已领 19 张券</p>
          </div>
          <div class="info">
            <div class="price-area">
              <span class="price">
                ¥
                <em class="number-font" style="font-size: 26px;">{{item.price}}</em>
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
              <img src="/static/index/images/platform_tmall.png">天猫
            </div>
          </div>
        </li>
      </ul>
    </el-row>
    <div class="page">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page.sync="params.page" :page-sizes="[12,20,40,80,pagination && pagination.count]"
        :page-size="params.limit" layout="total, sizes, prev, pager, next, jumper"
        :total="pagination && pagination.count || 0" v-if="pagination.count>12?true:false">
      </el-pagination>
    </div>
  </el-row>
</template>
<script>
  import {
    getProducts
  } from "@/api/products";
  import datacenterBus from "@/api/datacenterBus";

  export default {
    props: ['classType'],
    data() {
      return {
        loading: false,
        pagination: undefined,
        params: {
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
        let brandId = self.$route.query.brandId
        if (brandId != undefined) {
          this.params.brand = brandId
          this.params.type = 'brand'
        }

        switch (self.classType) {
          case "top":
            this.params.type = 'top'
            self.bindProducts(this.params);
            datacenterBus.$on("getValue", function (value) {
              this.params.categoryId = value
              self.bindProducts(this.params)
            });

            break;
          case "recommend":
            this.params.type = 'recommend'
            this.params.recommend = '1'
            self.bindProducts(this.params);
            datacenterBus.$on("getValue", function (value) {
              this.params.categoryId = value
              self.bindProducts(this.params)
            });
            break;
          case "my":
            if (localStorage.length > 0) {
              if (localStorage.getItem('productIdList') != null) {
                var idList = localStorage.getItem('productIdList');
                this.params.type = 'my'
                this.params.ids = idList
                self.bindProducts(this.params)
              }
            }
            break;
          case "guess":
            break;
          case "brand":
            this.params.type = 'brand'
            self.bindProducts(this.params);
            datacenterBus.$on("getValue", function (value) {
              this.params.categoryId = value
              this.params.brand = '1'
              self.bindProducts(this.params)
            });
            break;
          default:
            self.bindProducts(this.params);
            datacenterBus.$on("getValue", function (value) {
              this.params.sort = 'CreateTime'
              this.params.order = 'desc'
              this.params.categoryId = value
              this.params.type = self.classType
              self.bindProducts(this.params)
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
