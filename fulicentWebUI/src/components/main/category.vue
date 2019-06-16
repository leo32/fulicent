<template>
  <el-row class="wrapper home-oper-area">
    <div class="cate-area">
      <a class="cate-item" href="javascript:void(0);" v-loading="loading" v-on:click="refreshProducts(item.id)"
        v-for="item in categoryList" :key="item.id">
        <div class="inner">
          <i class="cate-icon">{{getIcon(item.id)}}</i>
          {{item.name}}
        </div>
      </a>
    </div>
    <div class="banner-top">
      <div class="banner-area swiper-container swiper-container-horizontal">
        <div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(-3300px, 0px, 0px);"
          v-for="item in ad1Products" :key="item.id">
          <div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="3" style="width: 660px;"><a
              target="_blank" class="swiper-slide swiper-slide-duplicate" :href="['/detail/'+item.id ]"
              data-swiper-slide-index="4" style="width: 660px;">
              <img style="width: 100%;" :src="item.image">
            </a></div>
        </div>
        <div class="swiper-bottom" _hover-ignore="1">
          <div class="swiper-pager"><span class="swiper-pagination-bullet swiper-pagination-bullet-active"></span><span
              class="swiper-pagination-bullet"></span><span class="swiper-pagination-bullet"></span><span
              class="swiper-pagination-bullet"></span></div>
        </div>

      </div>
      <div class="top-right-banner" v-for="item in ad2Products" :key="item.id">
        <a :href="['/detail/'+item.id]" target="_blank" title="轮播右侧">
          <img :src="item.image" title="轮播右侧广告">
        </a>
      </div>
    </div>
    <div class="small-banner-area">
      <el-col>
        <div href="javascript:;" class="small-banner-item" v-for="item in ad3Products" :key="item.id">
          <a target="_blank" :href="['/detail/'+item.id]">
            <img :src="item.image">
            <p class="title">{{item.name}}</p>
          </a>
        </div>
      </el-col>
    </div>
  </el-row>
</template>
<script>
  import {
    getCategoryList
  } from "@/api/category";
  import datacenterBus from "@/api/datacenterBus";
  import {
    getAdProducts
  }
  from "@/api/products";
  import scroll from "@/components/common/scroll";
  export default {
    data() {
      return {
        loading: false,
        categoryList: [],
        ad1Products: [],
        ad2Products: [],
        ad3Products: []
      };
    },
    components: {
      scroll
    },
    created() {
      this.bindCategoryList();
      this.bindAdProducts();
    },
    methods: {
      bindCategoryList() {
        this.loading = true
        getCategoryList().then(response => {
          this.categoryList = response.data.categoryList;
        }).finally(r => {
          this.loading = false
        });
      },
      refreshProducts(id) {
        datacenterBus.$emit("getValue", id);
      },
      bindAdProducts() {
        this.loading = true;
        getAdProducts().then(response => {
          this.ad1Products = response.data.ad1ProductList;
          this.ad2Products = response.data.ad2ProductList;
          this.ad3Products = response.data.ad3ProductList;
        }).finally(r => {
          this.loading = false
        });
      },
      getIcon(id) {
        var icon;
        switch (id) {
          case "1":
            icon = '';
            break;
          case "2":
            icon = "";
            break;
          case "3":
            icon = "";
            break;
          case "4":
            icon = "";
            break;
          case "5":
            icon = "";
            break;
          case "6":
            icon = " ";
            break;
          case "7":
            icon = "";
            break;
          case "8":
            icon = "";
            break;
          case "9":
            icon = "";
            break;
          case "10":
            icon = "";
            break;
          case "11":
            icon = "";
            break;
          case "12":
            icon = "";
            break;
          case "13":
            icon = "";
            break;
          case "14":
            icon = "";
            break;
        }
        return icon;
      }
    }
  };

</script>
